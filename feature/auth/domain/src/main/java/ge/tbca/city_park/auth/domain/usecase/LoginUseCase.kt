package ge.tbca.city_park.auth.domain.usecase

import ge.tbca.city_park.auth.domain.repository.LoginRepository
import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: LoginRepository
) {
    operator fun invoke(email: String, password: String): Flow<Resource<Unit, AuthError>> {
        return authRepository.logIn(
            email = email,
            password = password
        )
    }
}