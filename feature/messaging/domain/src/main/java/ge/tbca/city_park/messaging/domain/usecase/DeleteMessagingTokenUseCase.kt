package ge.tbca.city_park.messaging.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.messaging.domain.repository.MessagingTokenRepository
import javax.inject.Inject

class DeleteMessagingTokenUseCase @Inject constructor(private val messagingTokenRepository: MessagingTokenRepository) {

    suspend operator fun invoke(): Resource<Unit, ApiError> {
        return messagingTokenRepository.deleteMessagingToken()
    }
}