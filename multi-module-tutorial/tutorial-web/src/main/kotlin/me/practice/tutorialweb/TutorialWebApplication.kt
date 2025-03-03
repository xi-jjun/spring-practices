package me.practice.tutorialweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TutorialWebApplication

fun main(args: Array<String>) {
	runApplication<TutorialWebApplication>(*args)
}
