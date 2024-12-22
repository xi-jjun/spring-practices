package me.practice.springjpa.deadlock

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRequestRepository : JpaRepository<UserRequest, UserRequestId> {
    @Query("""
        SELECT u
        FROM UserRequest u
        WHERE u.userRequestId.id = :id
    """)
    fun findById(id: Long): UserRequest?

    @Query("""
        ALTER TABLE user_request
        ADD PARTITION (PARTITION :partitionName VALUES LESS THAN (:partitionEnd));
    """, nativeQuery = true)
    fun executeAddPartitioningTable(partitionName: String, partitionEnd: String)
}
