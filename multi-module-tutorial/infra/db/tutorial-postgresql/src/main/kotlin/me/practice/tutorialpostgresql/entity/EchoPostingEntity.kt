package me.practice.tutorialpostgresql.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "echo_posting")
@Entity
class EchoPostingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var title: String,
    var subtitle: String?,
    var content: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "echo_board_id")
    var echoBoard: EchoBoardEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "echo_user_id")
    var echoUser: EchoUserEntity?,
    var createdDt: LocalDateTime = LocalDateTime.now()
) {
    var updatedDt: LocalDateTime? = null
}
