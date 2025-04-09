package ge.tbca.city_park.domain.features.auth.usecase

import ge.tbca.city_park.domain.features.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return authRepository.getAuthState()
    }
}