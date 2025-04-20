package me.practice.tutorialweb.controller

import me.practice.tutorialdomain.service.EchoBoardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/echo-boards")
@RestController
class EchoBoardController(
    private val echoBoardService: EchoBoardService
) {
    @GetMapping("/{id}")
    fun detailEchoBoard(@PathVariable id: Long): ResponseEntity<String> {
        val data = echoBoardService.findBoard(id)
        return ResponseEntity.ok("id=${data.id}, name=${data.name}, created_dt=${data.createdDt}")
    }
}
