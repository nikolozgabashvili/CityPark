package ge.tbca.city_park.user.domain.usecase

import ge.tbca.city_park.user.domain.repository.UserRepository
import javax.inject.Inject

class IsUserAuthenticatedUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Boolean {
        return userRepository.isUserAuthenticated()
    }
}