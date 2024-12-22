package me.practice.springjpa.deadlock

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class PartitioningService(
    private val entityManager: EntityManager
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun addPartitionTable(partitionName: String, partitionEnd: String) {
        val sql = """
            ALTER TABLE user_request
            REORGANIZE PARTITION p_max INTO (
                PARTITION $partitionName VALUES LESS THAN ($partitionEnd),
                PARTITION p_max VALUES LESS THAN (MAXVALUE) 
            );
        """.trimIndent()
        entityManager.createNativeQuery(sql).executeUpdate()
    }
}
