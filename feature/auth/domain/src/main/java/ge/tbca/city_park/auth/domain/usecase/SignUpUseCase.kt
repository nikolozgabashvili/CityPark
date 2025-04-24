package ge.tbca.city_park.auth.domain.usecase

import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.auth.domain.repository.SignUpRepository
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
) {
    operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<Unit, AuthError>> {
        return signUpRepository.register(
            email = email,
            password = password
        )
    }
}