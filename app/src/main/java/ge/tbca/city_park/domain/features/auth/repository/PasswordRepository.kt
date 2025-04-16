package ge.tbca.city_park.domain.features.auth.repository

import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.domain.core.util.Resource
import kotlinx.coroutines.flow.Flow


interface PasswordRepository {
    fun recoverPassword(email: String): Flow<Resource<Unit, NetworkError>>
    fun changePassword(
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<Unit, NetworkError>>
}