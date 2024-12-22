package me.practice.springjpa.deadlock

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Id

// MySQL Partitioning
// https://velog.io/@juhyeon1114/MySQL-Partitioning%EC%9C%BC%EB%A1%9C-%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%B5%9C%EC%A0%81%ED%99%94%ED%95%98%EA%B8%B0
@Entity
class UserRequest(
    @Id
    @EmbeddedId
    var userRequestId: UserRequestId?,
    var params: String? = null,
    var status: Int = REQUESTED
) {
    companion object {
        const val REQUESTED = 1
        const val PROCESSING = 2
        const val COMPLETED = 3
        private var idSeq = 0L

        fun create(
            params: String?,
            status: Int = REQUESTED
        ): UserRequest = UserRequest(
            userRequestId = UserRequestId(++idSeq),
            params = params,
            status = status
        )
    }
}
