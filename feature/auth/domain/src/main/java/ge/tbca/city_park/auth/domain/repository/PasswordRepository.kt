package ge.tbca.city_park.auth.domain.repository


import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface PasswordRepository {
    fun recoverPassword(email: String): Flow<Resource<Unit, AuthError>>
    fun changePassword(
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<Unit, AuthError>>
}