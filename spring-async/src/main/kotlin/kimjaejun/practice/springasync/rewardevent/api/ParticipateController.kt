package kimjaejun.practice.springasync.rewardevent.api

import kimjaejun.practice.springasync.rewardevent.api.dto.ParticipateRequest
import kimjaejun.practice.springasync.rewardevent.api.dto.ParticipateResponse
import kimjaejun.practice.springasync.rewardevent.service.ParticipateEventService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable

@RestController
class ParticipateController(
	private val participateEventService: ParticipateEventService
) {
	private val log = KotlinLogging.logger { }

	@PostMapping("/api/participates-sync")
	fun eventParticipateSync(@RequestBody participateRequest: ParticipateRequest): ResponseEntity<ParticipateResponse> {
		log.info { "Controller: eventParticipateSync START ${Thread.currentThread().name}" }
		val response = participateEventService.participateSync(participateRequest)
		log.info { "Controller: eventParticipateSync END ${Thread.currentThread().name}" }
		return ResponseEntity(response, HttpStatus.OK)
	}

	// return 타입이 Callable 가 아닌 단순 객체라면, 결국 요청받은 servlet 스레드가 응답까지 처리함.
	@PostMapping("/api/participates-async-bio")
	fun eventParticipateAsyncBio(@RequestBody participateRequest: ParticipateRequest): ResponseEntity<ParticipateResponse> {
		log.info { "Controller: eventParticipateAsyncBio START ${Thread.currentThread().name}" }
		val response = participateEventService.participateAsync(participateRequest)
		log.info { "Controller: eventParticipateAsyncBio END ${Thread.currentThread().name}" }
		return ResponseEntity(response, HttpStatus.OK)
	}

	// return 타입이 Callable 여서 요청/응답 시 스레드가 다름.
	// 이로써 오래걸리는 요청을 기다린 후 해당 데이터를 최종적으로 클라이언트에게 응답을 해줘야 할 경우,
	// Callable 로 반환하도록 하면 요청 스레드는 블록킹된 상태가 아니기에 다른 요청을 처리할 수 있게 되는 것
	// 참고) 현재 participateAsync 메서드 내부에서 실행하는 비동기 로직(Task1)의 데이터가 아닌,
	// 		Task2의 데이터가 필요하다는 가정. Task1은 thenApply 시 알아서 로직을 수행한다.
	@PostMapping("/api/participates-async-nio")
	fun eventParticipateAsyncNio(@RequestBody participateRequest: ParticipateRequest): Callable<ResponseEntity<ParticipateResponse>> {
		log.info { "Controller: eventParticipateAsyncNio START ${Thread.currentThread().name}" }
		return Callable {
			// 오래 걸리는 요청이고, 응답 시 반드시 필요한 데이터라 가정.
			val response = participateEventService.participateAsync(participateRequest)
			log.info { "Controller: eventParticipateAsyncNio END ${Thread.currentThread().name}" }
			ResponseEntity(response, HttpStatus.OK)
		}
	}

	@GetMapping("/api/async")
	fun apiAsync(): Callable<ResponseEntity<ParticipateResponse>> {
		log.info { "Controller: apiAsync START ${Thread.currentThread().name}" }

		return Callable {
			log.info { "Callable: START ${Thread.currentThread().name}" }
			Thread.sleep(3000)
			ResponseEntity(ParticipateResponse("Good!", 100), HttpStatus.OK)
		}
	}
}
