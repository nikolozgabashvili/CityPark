package ge.tbca.city_park.user.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun isUserAuthenticated(): Boolean
    fun getAuthState(): Flow<Boolean>
}