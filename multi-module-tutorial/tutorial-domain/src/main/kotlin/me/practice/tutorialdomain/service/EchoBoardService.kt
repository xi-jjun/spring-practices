package me.practice.tutorialdomain.service

import me.practice.tutorialdomain.domain.EchoBoard
import me.practice.tutorialdomain.repository.IEchoBoardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional // sprint-tx
@Service // spring-context 의존성으로 사용 가능
class EchoBoardService(
    private val echoBoardRepository: IEchoBoardRepository
) {
    @Transactional(readOnly = true)
    fun createBoard(name: String): Boolean {
        if (echoBoardRepository.existsByName(name)) {
            throw IllegalArgumentException("duplicated echo board name: $name")
        }

        val board = EchoBoard(id = null, name = name)
        echoBoardRepository.save(board)

        return true
    }

    fun findBoard(id: Long): EchoBoard {
        if (id <= 0L) {
            throw IllegalArgumentException("invalid echo board id value: $id")
        }

        return echoBoardRepository.findById(id)
    }
}
