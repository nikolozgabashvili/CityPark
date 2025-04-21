package ge.tbca.city_park.core.domain.util


sealed interface Resource<out D, out E : ResourceError> {
    data class Success<out D>(val data: D) : Resource<D, Nothing>
    data class Error<out E : ResourceError>(val error: E) : Resource<Nothing, E>
    data object Loading : Resource<Nothing, Nothing>
}


fun Resource<*, *>.isLoading() = this is Resource.Loading