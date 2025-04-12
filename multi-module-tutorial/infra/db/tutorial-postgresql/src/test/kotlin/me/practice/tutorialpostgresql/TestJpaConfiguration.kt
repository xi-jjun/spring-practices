package me.practice.tutorialpostgresql

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
// 원래는 SpringBootApplication 하위의 패키지 + 클래스들을 모두 scan 하여 spring context 에 load.
// 그러나 infra 모듈에는 SpringBootApplication 이 없기에, 직접 Entity 클래스가 있는 곳을 명시하여 scan 할 수 있도록 함.
// @see https://www.baeldung.com/spring-entityscan-vs-componentscan
@EntityScan("me.practice.tutorialpostgresql.entity")
// repository 또한 스캔
@EnableJpaRepositories("me.practice.tutorialpostgresql.repository")
class TestJpaConfiguration
