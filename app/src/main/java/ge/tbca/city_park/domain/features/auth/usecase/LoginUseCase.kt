package ge.tbca.city_park.domain.features.auth.usecase

import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.domain.core.util.Resource
import ge.tbca.city_park.domain.features.auth.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<Unit, NetworkError>> {
        return authRepository.logIn(
            email = email,
            password = password
        )
    }
}