package ge.tbca.city_park.auth.domain.usecase

import ge.tbca.city_park.auth.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val authRepository: LoginRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return authRepository.getAuthState()
    }
}