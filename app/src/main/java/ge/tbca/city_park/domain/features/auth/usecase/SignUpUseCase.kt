package ge.tbca.city_park.domain.features.auth.usecase

import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.domain.core.util.Resource
import ge.tbca.city_park.domain.features.auth.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<Unit, NetworkError>> {
        return signUpRepository.register(
            email = email,
            password = password
        )
    }
}