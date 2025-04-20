package me.practice.tutorialpostgresql.repository

import me.practice.tutorialpostgresql.entity.EchoBoardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EchoBoardJpaRepository : JpaRepository<EchoBoardEntity, Long> {
    fun existsByName(name: String): Boolean
}
