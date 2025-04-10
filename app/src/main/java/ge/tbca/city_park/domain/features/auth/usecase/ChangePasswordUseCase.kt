package ge.tbca.city_park.domain.features.auth.usecase

import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.domain.core.util.Resource
import ge.tbca.city_park.domain.features.auth.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val passwordRepository: PasswordRepository
) {
    operator fun invoke(
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<Unit, NetworkError>> {
        return passwordRepository.changePassword(
            oldPassword = oldPassword,
            newPassword = newPassword
        )
    }
}