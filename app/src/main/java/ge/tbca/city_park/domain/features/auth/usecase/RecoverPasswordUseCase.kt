package ge.tbca.city_park.domain.features.auth.usecase

import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.domain.core.util.Resource
import ge.tbca.city_park.domain.features.auth.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecoverPasswordUseCase @Inject constructor(
    private val passwordRepository: PasswordRepository
) {
    operator fun invoke(email: String): Flow<Resource<Unit, NetworkError>> {
        return passwordRepository.recoverPassword(email = email)
    }
}