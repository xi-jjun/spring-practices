package kimjaejun.practice.springasync.rewardevent.api

import kimjaejun.practice.springasync.rewardevent.api.dto.ParticipateRequest
import kimjaejun.practice.springasync.rewardevent.api.dto.ParticipateResponse
import kimjaejun.practice.springasync.rewardevent.service.ParticipateEventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ParticipateController(
	private val participateEventService: ParticipateEventService
) {
	@PostMapping("/api/participates")
	fun eventParticipate(@RequestBody participateRequest: ParticipateRequest): ResponseEntity<ParticipateResponse> {
		val response = participateEventService.participateSync(participateRequest)
		return ResponseEntity(response, HttpStatus.OK)
	}
}
