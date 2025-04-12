package me.practice.tutorialdomain

import java.time.LocalDateTime

/**
 * 유저가 게시판에 작성한 포스팅
 */
data class EchoPosting(
    var id: Long,
    var title: String,
    var subtitle: String,
    var content: String,
    var echoBoard: EchoBoard,
    var echoUser: EchoUser,
    var createdDt: LocalDateTime,
    var updatedDt: LocalDateTime?,
)
