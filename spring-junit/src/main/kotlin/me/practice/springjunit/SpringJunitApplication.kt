package me.practice.springjunit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringJunitApplication

fun main(args: Array<String>) {
    runApplication<SpringJunitApplication>(*args)
}
