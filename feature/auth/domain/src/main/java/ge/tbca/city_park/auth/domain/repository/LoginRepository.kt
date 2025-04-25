package ge.tbca.city_park.auth.domain.repository


import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun logIn(email: String, password: String): Flow<Resource<Unit, AuthError>>
    fun signOut(): Flow<Resource<Unit, AuthError>>

}