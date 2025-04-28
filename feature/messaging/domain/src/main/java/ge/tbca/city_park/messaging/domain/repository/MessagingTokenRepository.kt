package ge.tbca.city_park.messaging.domain.repository

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource

interface MessagingTokenRepository {
    suspend fun updateMessagingToken(token: String?): Resource<Unit,ApiError>
    suspend fun getAndUpdateMessagingToken(): Resource<Unit, ApiError>
    suspend fun deleteMessagingToken(): Resource<Unit, ApiError>
}