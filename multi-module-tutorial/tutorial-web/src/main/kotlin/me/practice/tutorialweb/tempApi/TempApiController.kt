package me.practice.tutorialweb.tempApi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TempApiController {
    @GetMapping("/hello-world")
    fun helloWorldApi(): String {
        return "hello world!"
    }
}
