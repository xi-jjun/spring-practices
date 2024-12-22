package me.practice.springjpa.deadlock

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserRequestService(
    private val entityManager: EntityManager
) {
    @Transactional(readOnly = true)
    fun selectQuery(id: Long, sleepSecond: Long) {
        val result = entityManager.createQuery(
            """
            SELECT u
            FROM UserRequest u
            WHERE u.userRequestId.id = $id
        """.trimIndent()
        ).firstResult

        Thread.sleep(sleepSecond * 1000)
    }

    fun updateQuery(userRequest: UserRequest) {
        entityManager.merge(userRequest)
    }
}
