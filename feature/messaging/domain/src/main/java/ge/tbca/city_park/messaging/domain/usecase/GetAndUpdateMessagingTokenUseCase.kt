package ge.tbca.city_park.messaging.domain.usecase

import ge.tbca.city_park.messaging.domain.repository.MessagingTokenRepository
import javax.inject.Inject

class GetAndUpdateMessagingTokenUseCase @Inject constructor(
    private val messagingTokenRepository: MessagingTokenRepository
) {
    suspend operator fun invoke() {
        messagingTokenRepository.getAndUpdateMessagingToken()
    }
}