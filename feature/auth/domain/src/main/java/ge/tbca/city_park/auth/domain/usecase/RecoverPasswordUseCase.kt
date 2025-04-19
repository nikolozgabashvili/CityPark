package ge.tbca.city_park.auth.domain.usecase

import ge.tbca.city_park.auth.domain.repository.PasswordRepository
import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecoverPasswordUseCase @Inject constructor(
    private val passwordRepository: PasswordRepository
) {
    operator fun invoke(email: String): Flow<Resource<Unit, AuthError>> {
        return passwordRepository.recoverPassword(email = email)
    }
}