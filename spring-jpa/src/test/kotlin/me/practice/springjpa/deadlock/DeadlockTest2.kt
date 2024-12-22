package me.practice.springjpa.deadlock

import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class DeadlockTest2 @Autowired constructor(
    private val testEntityManager: EntityManager,
    private val userRequestRepository: UserRequestRepository,
) {
    private val partitioningService = PartitioningService(testEntityManager)

    @Transactional
    @Test
    fun `sibal`() {
        val today = LocalDate.now().plusDays(2)
        val partitionName = "p_${today.format(DateTimeFormatter.BASIC_ISO_DATE)}"
        val partitionEnd = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        // 하지만 위 find 동작에서 이미 Read lock 이 점유된 상태여서, Metadata Lock 이 필요한 파티셔닝 작업은 대기하게 됨
//                userRequestRepository.executeAddPartitioningTable(partitionName, partitionEnd)
        partitioningService.addPartitionTable(partitionName, "'$partitionEnd'")
    }
}
