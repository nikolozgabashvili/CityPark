package ge.tbca.city_park.messaging.data.repository

import com.google.firebase.messaging.FirebaseMessaging
import ge.tbca.city_park.core.data.extension.toEmptyResource
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.messaging.data.apiService.TokenApiService
import ge.tbca.city_park.messaging.data.model.UpdateTokenRequest
import ge.tbca.city_park.messaging.domain.repository.MessagingTokenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MessagingTokenRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val tokenApiService: TokenApiService,
    private val firebaseMessaging: FirebaseMessaging
) : MessagingTokenRepository {

    override suspend fun updateMessagingToken(token: String?): Resource<Unit, ApiError> {
        return withContext(Dispatchers.IO) {
            apiHelper.safeCallNoLoading {
                tokenApiService.updateMessagingToken(UpdateTokenRequest(token))
            }.toEmptyResource()
        }
    }

    override suspend fun getAndUpdateMessagingToken(): Resource<Unit, ApiError> {
        return withContext(Dispatchers.IO) {
            try {
                val token = firebaseMessaging.token.await()
                updateMessagingToken(token)
            } catch (e: Exception) {
                Resource.Error(ApiError.UNKNOWN_ERROR)

            }
        }
    }


}