package me.practice.tutorialdomain.repository

import me.practice.tutorialdomain.domain.EchoBoard

interface IEchoBoardRepository {
    fun save(echoBoard: EchoBoard): EchoBoard
    fun saveAll(echoBoards: List<EchoBoard>): List<EchoBoard>
    fun findById(id: Long): EchoBoard
    fun existsByName(name: String): Boolean
}
