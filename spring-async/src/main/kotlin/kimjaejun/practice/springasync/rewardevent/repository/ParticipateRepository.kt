package kimjaejun.practice.springasync.rewardevent.repository

import kimjaejun.practice.springasync.rewardevent.domain.Participate
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class ParticipateRepository {
	companion object {
		var entityPkIdSeq: Long = 0L
	}
	private val participates = ConcurrentHashMap<Long, Participate>()

	fun save(participate: Participate): Long {
		synchronized(this) {
			entityPkIdSeq++
			participate.id = entityPkIdSeq
		}
		participates[participate.id] = participate

		return participate.id
	}
}
