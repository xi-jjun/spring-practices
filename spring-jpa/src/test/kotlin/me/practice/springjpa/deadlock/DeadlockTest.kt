package me.practice.springjpa.deadlock

import io.kotest.assertions.eq.eq
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.Test
import io.kotest.extensions.spring.SpringExtension
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.test.expect

@Transactional
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeadlockTest @Autowired constructor(
    val testEntityManager: EntityManager,
    val userRequestRepository: UserRequestRepository,
) : DescribeSpec({
    extensions(SpringExtension)
    isolationMode = IsolationMode.InstancePerLeaf
    val partitioningService: PartitioningService = PartitioningService(testEntityManager)

    describe("Deadlock 발생 테스트") {
        context("Detached Entity를 save하려고 할 때") {
            it("hello") {
                // ko routine 으로 실행필요
            }

            it("SELECT 쿼리와 UPDATE 쿼리 사이에 파티셔닝 추가 작업이 실행되면 DEADLOCK이 발생한다.") {
                // given : 이미 생성되어 있는 객체 존재
                val original = UserRequest.create("{ 'param1': 'hello' }")
                val saved = userRequestRepository.save(original)
                testEntityManager.flush()
                testEntityManager.clear()

                // when 1 : 비지니스 로직 시작. 정보 조회를 위해서 UserRequestRepository.findByXXX 로 조회 했다고 가정
                val findUserRequest = userRequestRepository.findById(saved.userRequestId!!.id)
                // detach 상태로 변경 (서비스 메서드에 @Transactional 선언 안됐다는 가정
                testEntityManager.detach(findUserRequest)
                testEntityManager.clear()

                // when 2 : 이후 로직에서 detach 상태의 엔티티를 수정 후 repository.save 동작
                findUserRequest!!.status = UserRequest.COMPLETED // 수정

                // when 3 : repository.save 동작
                // 1) 해당 데이터가 isNew 인지 판단 (JpaRepository 내부 로직)
                //    - PK가 존재할 경우, 직접 DB에 SELECT Query 를 날려서 확인한다. 따라서 SELECT 쿼리 발생
                testEntityManager.createQuery("""
                    SELECT u
                    FROM UserRequest u
                    WHERE u.userRequestId.id = ${saved.userRequestId!!.id}
                """.trimIndent()).firstResult

                // when 4 : 근데 여기서 파티셔닝 테이블 작업 실행
                val today = LocalDate.now().plusDays(1)
                val partitionName = "p_${today.format(DateTimeFormatter.BASIC_ISO_DATE)}"
                val partitionEnd = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                // 하지만 위 find 동작에서 이미 Read lock 이 점유된 상태여서, Metadata Lock 이 필요한 파티셔닝 작업은 대기하게 됨
//                userRequestRepository.executeAddPartitioningTable(partitionName, partitionEnd)
                partitioningService.addPartitionTable(partitionName, "'$partitionEnd'")

                // then : repository.save 동작 이어서 진행
                // 2) 존재하기 때문에 Update Query 발생 --> Deadlock 발생
                testEntityManager.merge(findUserRequest)

                val result = userRequestRepository.findById(saved.userRequestId!!.id)
                eq(result!!.status, UserRequest.COMPLETED)
            }
        }
    }
})
