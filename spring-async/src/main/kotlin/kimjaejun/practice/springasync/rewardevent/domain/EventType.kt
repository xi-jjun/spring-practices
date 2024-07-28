package kimjaejun.practice.springasync.rewardevent.domain

enum class EventType(
	private val inProgress: Boolean,
	private val reward: Int
) {
	REWARD_100_EVENT(true, 100),
	REWARD_500_EVENT(true, 500),
	REWARD_1000_EVENT(false, 1000);

	fun isOngoing(): Boolean {
		return inProgress
	}

	fun eventReward(): Int {
		return reward
	}

	fun isFinished(): Boolean {
		return !isOngoing()
	}

	companion object {
		fun eventIdToEventType(eventId: Int): EventType {
			return when (eventId) {
				1 -> REWARD_100_EVENT
				2 -> REWARD_500_EVENT
				3 -> REWARD_1000_EVENT
				else -> {
					throw IllegalArgumentException("잘못된 요청입니다.")
				}
			}
		}
	}
}
