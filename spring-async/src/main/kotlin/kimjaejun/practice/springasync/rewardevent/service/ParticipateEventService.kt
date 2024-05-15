package kimjaejun.practice.springasync.rewardevent.service

import kimjaejun.practice.springasync.rewardevent.api.dto.ParticipateRequest
import kimjaejun.practice.springasync.rewardevent.api.dto.ParticipateResponse
import kimjaejun.practice.springasync.rewardevent.domain.Participate
import kimjaejun.practice.springasync.rewardevent.repository.ParticipateRepository
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class ParticipateEventService(
	private val participateRepository: ParticipateRepository,
) {
	private val log = KotlinLogging.logger {}

	fun participateSync(request: ParticipateRequest): ParticipateResponse {
		val participate = Participate.create(request)
		validate(participate)

		val result = requestForValidateParticipation(participate)
		return if (result) {
			participateRepository.save(participate)
			ParticipateResponse("참여에 성공했습니다", participate.eventType.eventReward())
		} else {
			ParticipateResponse("참여 가능한 상태가 아닙니다", -1)
		}
	}

	@Async
	fun participateAsync(request: ParticipateRequest): ParticipateResponse {
		log.info { "Service: participateAsync START" }
		val participate = Participate.create(request)
		validate(participate)

		// 응답시간이 상대적으로 긴 외부 API 요청은 별도 스레드로 요청
		val asyncTask = CompletableFuture.supplyAsync {
			log.info { "Task1(supplyAsync) : 외부 API 요청 START" }
			val result = requestForValidateParticipation(participate)
			log.info { "Task1(supplyAsync) : 외부 API 요청 END" }
			result // 결과를 반환해줘야 thenApply에서 확인 가능
		}

		log.info { "Task2 : 작업 START" }
		Thread.sleep(2000)
		log.info { "Task2 : 작업 END" }

		var resultValue = false
		// 아까 비동기로 처리한 로직의 데이터가 필요한 시점
		asyncTask.thenApply { result ->
			// supplyAsync 코드가 내부적으로 처리가 끝나면 실행된다.
			log.info { "thenApply: need Task1 data" }
			// 해당 데이터를 받을 때까지 블록킹 되지 않고, 넘어가게 된다.
			resultValue = result
			// request something...
			// requestOtherApiByUsing(resultValue)
		}
		// resultValue 라는 데이터가 아래에서 필요한데 받을 때 까지 기다릴 수는 없는가?
		// => 가능. 그러나 그럴려면 비동기적으로 처리하는 이유가 없음. get(), join() 등으로 처리 가능.
		// 	  따라서 결과 또한 비동기적으로 처리하는 thenApply 같은 메서드를 사용하는게 추천됨. 그게 아니라면 굳이 비동기적으로 처리할 이유가 있을까?

		return if (resultValue) {
			participateRepository.save(participate)
			log.info { "Service: participateAsync END" }
			ParticipateResponse("참여에 성공했습니다", participate.eventType.eventReward())
		} else {
			log.info { "Service: participateAsync END" }
			ParticipateResponse("참여 가능한 상태가 아닙니다", -1)
		}
	}

	private fun validate(participate: Participate) {
		// 종료된 이벤트라면 굳이 외부 API 호출하여 확인하기 전에 예외처리해버린다는 의미의 로직
		if (participate.eventType.isFinished()) {
			throw IllegalArgumentException("이미 종료된 이벤트 입니다")
		}
	}

	/**
	 * 외부 API에 요청하여, 현재 참여정보로 참여가 가능한지 여부를 확인 후 반환한다.
	 *
	 * @param participate 참여정보
	 * @return 참여가능할 경우 true, 그 외의 경우 false
	 */
	private fun requestForValidateParticipation(participate: Participate): Boolean {
		try {
			// 대충 외부 API 요청이 5s 정도 오래걸린다는 가정
			Thread.sleep(5000)
			return true
		} catch (e: Exception) {
			e.printStackTrace()
		}
		return false
	}
}
