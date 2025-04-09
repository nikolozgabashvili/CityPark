package ge.tbca.city_park.domain.features.auth.repository

import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.domain.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {
    suspend fun register(email: String, password: String): Flow<Resource<Unit, NetworkError>>
}