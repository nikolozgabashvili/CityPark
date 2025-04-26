package ge.tbca.city_park.core.data.helper

import ge.tbca.city_park.core.data.model.BaseDto
import ge.tbca.city_park.core.data.model.BaseResponse
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException


class ApiHelper(
){

    inline fun <reified T> safeCall(noinline call: suspend () -> BaseResponse<T>): Flow<Resource<T, ApiError>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCallNoLoading(call = call))
        }

    }

    suspend inline fun <reified T> safeCallNoLoading(noinline call: suspend () -> BaseResponse<T>): Resource<T, ApiError> {
        return withContext(Dispatchers.IO) {
            try {

                val response = call()
                if (response.isSuccessful) {
                    Resource.Success(response.body()?.data!!)
                } else {
                    if (response.code() == 401) {
                        Resource.Error(ApiError.UNAUTHORIZED)
                    } else {
                        val errorBodyString = response.errorBody()?.string()
                        if (errorBodyString.isNullOrEmpty()) {
                            Resource.Error(ApiError.UNKNOWN_ERROR)
                        } else {
                            val errorResponse = Json.decodeFromString<BaseDto<T>>(errorBodyString)
                            Resource.Error(ApiError.valueOf(errorResponse.errorCode?:ApiError.UNKNOWN_ERROR.name))

                        }
                    }

                }
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e

                } else if (e is UnknownHostException) {
                    Resource.Error(ApiError.NETWORK_ERROR)

                } else {
                    e.printStackTrace()
                    Resource.Error(ApiError.UNKNOWN_ERROR)
                }

            }

        }
    }

}