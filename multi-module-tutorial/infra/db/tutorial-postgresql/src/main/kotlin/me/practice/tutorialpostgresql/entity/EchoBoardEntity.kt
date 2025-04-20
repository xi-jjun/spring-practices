package me.practice.tutorialpostgresql.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.practice.tutorialdomain.domain.EchoBoard
import java.time.LocalDateTime

@Table(name = "echo_board")
@Entity
class EchoBoardEntity(
    var name: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
    var createdDt: LocalDateTime = LocalDateTime.now()
    var updatedDt: LocalDateTime? = null

    fun toModel() = EchoBoard(
        id = id,
        name = name,
        createdDt = createdDt
    )

    companion object {
        fun from(model: EchoBoard) = with(model) {
            EchoBoardEntity(
                name = model.name
            )
        }
    }
}
