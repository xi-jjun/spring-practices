package me.practice.tutorialpostgresql.repository

import me.practice.tutorialpostgresql.TestJpaConfiguration
import me.practice.tutorialpostgresql.entity.EchoBoardEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

// 테스트 코드에 ApplicationContext 를 어떻게 구성하고 load 할지 알려주는 meta 정보를 명시할 수 있도록 해주는 어노테이션
// @see https://docs.spring.io/spring-framework/reference/testing/annotations/integration-spring/annotation-contextconfiguration.html
@ContextConfiguration(classes = [TestJpaConfiguration::class])
// test/resources/application-db.yml 에 정의된 PostgreSQL DB 사용하도록 설정
// 참고) 명시하지 않으면 In memory DB 등을 사용하려고 함.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("db") // profile은 postfix 인 'db' 명시. https://stackoverflow.com/questions/38711871/load-different-application-yml-in-springboot-test
@DataJpaTest // SpringBootApplication 이 없기도 하고, infra level 에서의 테스트만 진행할거라 DataJpaTest만 사용
class EchoBoardJpaRepositoryTest @Autowired constructor(
    private val echoBoardJpaRepository: EchoBoardJpaRepository,
) {
    @Test
    fun `테스트 1`() {
        val insertData = EchoBoardEntity("테스트 게시판")
        echoBoardJpaRepository.save(insertData)
        val data = echoBoardJpaRepository.findById(1L)

        assertThat(data.get().name).isEqualTo("테스트 게시판")
    }
}
