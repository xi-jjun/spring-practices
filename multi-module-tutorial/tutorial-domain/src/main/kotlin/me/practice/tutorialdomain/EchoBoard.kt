package me.practice.tutorialdomain

import java.time.LocalDateTime

/**
 * 유저가 게시글을 작성할 게시판
 */
data class EchoBoard(
    var id: Long,
    var name: String,
    var createdDt: LocalDateTime,
)
