package kimjaejun.practice.springasync.rewardevent.service

import kimjaejun.practice.springasync.rewardevent.api.dto.ParticipateRequest
import kimjaejun.practice.springasync.rewardevent.api.dto.ParticipateResponse
import kimjaejun.practice.springasync.rewardevent.domain.Participate
import kimjaejun.practice.springasync.rewardevent.repository.ParticipateRepository
import org.springframework.stereotype.Service

@Service
class ParticipateEventService(
	private val participateRepository: ParticipateRepository
) {
	fun participateSync(request: ParticipateRequest): ParticipateResponse {
		val participate = Participate.create(request)

		// 종료된 이벤트라면 굳이 외부 API 호출하여 확인하기 전에 예외처리해버린다는 의미의 로직
		if (participate.eventType.isFinished()) {
			throw IllegalArgumentException("이미 종료된 이벤트 입니다")
		}

		val result = requestForValidateParticipation(participate)
		return if (result) {
			participateRepository.save(participate)
			ParticipateResponse("참여에 성공했습니다", participate.eventType.eventReward())
		} else {
			ParticipateResponse("참여 가능한 상태가 아닙니다", -1)
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
			// 대충 외부 API 요청이 1.5s 정도 오래걸린다는 가정
			Thread.sleep(1500)
			return true
		} catch (e: Exception) {
			e.printStackTrace()
		}
		return false
	}
}
