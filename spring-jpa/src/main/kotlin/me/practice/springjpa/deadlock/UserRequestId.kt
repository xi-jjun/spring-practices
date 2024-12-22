package me.practice.springjpa.deadlock

import jakarta.persistence.Embeddable
import java.io.Serializable
import java.time.LocalDateTime

// https://www.baeldung.com/jpa-composite-primary-keys
@Embeddable
data class UserRequestId(
    var id: Long,
    var createdAt: LocalDateTime = LocalDateTime.now()
) : Serializable
