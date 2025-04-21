package ge.tbca.city_park.auth.domain.usecase

import ge.tbca.city_park.auth.domain.repository.PasswordRepository
import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val passwordRepository: PasswordRepository
) {
    operator fun invoke(
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<Unit, AuthError>> {
        return passwordRepository.changePassword(
            oldPassword = oldPassword,
            newPassword = newPassword
        )
    }
}