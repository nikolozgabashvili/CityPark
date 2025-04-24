package ge.tbca.city_park.core.data.model

import kotlinx.serialization.Serializable
import retrofit2.Response

@Serializable
data class  BaseDto<T>(
    val data: T? = null,
    val errorCode: String? = null,
    val errorMessage: String? = null,
)

typealias BaseResponse<T> = Response<BaseDto<T>>
