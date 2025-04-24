package ge.tbca.city_park.core.data.helper

import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException


class ApiHelper {

    fun <T> safeCall(call: suspend () -> BaseResponse<T>): Flow<Resource<T, ApiError>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCallNoLoading(call = call))
        }

    }

    suspend fun <T> safeCallNoLoading(call: suspend () -> BaseResponse<T>): Resource<T, ApiError> {
        return withContext(Dispatchers.IO) {
            try {

                val response = call()
                if (response.isSuccessful) {
                    Resource.Success(response.body()?.data!!)
                } else {
                    if (response.code() == 401) {
                        Resource.Error(ApiError.UNAUTHORIZED)
                    } else {
                        response.body()?.errorCode?.let {
                            Resource.Error(ApiError.valueOf(it))
                        } ?: run {

                            Resource.Error(ApiError.UNKNOWN_ERROR)
                        }

                    }

                }
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                } else {
                    e.printStackTrace()
                    Resource.Error(ApiError.UNKNOWN_ERROR)
                }

            }

        }
    }
}