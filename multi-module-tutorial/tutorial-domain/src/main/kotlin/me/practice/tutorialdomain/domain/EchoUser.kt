package me.practice.tutorialdomain.domain

import java.time.LocalDateTime

/**
 * Echo 게시판 유저
 */
data class EchoUser(
    var id: Long,
    var nickname: String,
    var age: Int,
    var createdDt: LocalDateTime,
    var updatedDt: LocalDateTime?,
)
