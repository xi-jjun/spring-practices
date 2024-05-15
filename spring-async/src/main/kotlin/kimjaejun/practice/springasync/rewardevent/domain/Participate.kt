package kimjaejun.practice.springasync.rewardevent.domain

import kimjaejun.practice.springasync.rewardevent.api.dto.ParticipateRequest
import java.time.LocalDateTime

// Entity 라고 가정
class Participate(
	var uid: String,
	var eventType: EventType
) {
	var id: Long = 0L
	val createdAt: LocalDateTime = LocalDateTime.now()

	companion object {
		fun create(request: ParticipateRequest): Participate = Participate(
			uid = request.uid,
			eventType = EventType.eventIdToEventType(request.eventTypeId)
		)
	}
}
