package me.practice.tutorialpostgresql.repository

import me.practice.tutorialdomain.domain.EchoBoard
import me.practice.tutorialdomain.repository.IEchoBoardRepository
import me.practice.tutorialpostgresql.entity.EchoBoardEntity
import org.springframework.stereotype.Repository

@Repository
class EchoBoardRepositoryImpl(
    private val echoBoardJpaRepository: EchoBoardJpaRepository
) : IEchoBoardRepository {
    override fun save(echoBoard: EchoBoard): EchoBoard {
        return echoBoardJpaRepository.save(EchoBoardEntity.from(echoBoard)).toModel()
    }

    override fun saveAll(echoBoards: List<EchoBoard>): List<EchoBoard> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): EchoBoard {
        return echoBoardJpaRepository.findById(id)
            .orElseThrow()
            .toModel()
    }

    override fun existsByName(name: String): Boolean {
        return echoBoardJpaRepository.existsByName(name)
    }
}
