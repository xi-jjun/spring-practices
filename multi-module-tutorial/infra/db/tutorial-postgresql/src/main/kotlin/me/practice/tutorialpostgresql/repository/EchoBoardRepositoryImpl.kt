package me.practice.tutorialpostgresql.repository

import me.practice.tutorialdomain.domain.EchoBoard
import me.practice.tutorialdomain.repository.IEchoBoardRepository
import me.practice.tutorialpostgresql.entity.EchoBoardEntity
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository

// 최초에 패키지 구성을 바보같이 해놔서, 실행모듈 입장에서 패키지 구조를 통해 자동으로 스캔이 불가능.
// 따라서 직접 스캔할 패키지명을 임시로 명시하여 실행되도록 함
@EnableJpaRepositories("me.practice.tutorialpostgresql.repository")
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
