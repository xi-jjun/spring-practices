package me.practice.tutorialweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

// 최초에 패키지 구성을 바보같이 해놔서, 실행모듈 입장에서 패키지 구조를 통해 자동으로 스캔이 불가능.
// 따라서 직접 스캔할 패키지명을 임시로 명시하여 실행되도록 함
@ComponentScan("me.practice") // 가장 상위 패키지부터 전부 포함
@EntityScan("me.practice") // entity 스캔
@SpringBootApplication
class TutorialWebApplication

fun main(args: Array<String>) {
	runApplication<TutorialWebApplication>(*args)
}
