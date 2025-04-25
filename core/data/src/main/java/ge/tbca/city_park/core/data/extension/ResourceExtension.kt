package ge.tbca.city_park.core.data.extension

import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.ResourceError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <D, E : ResourceError, R> Resource<D, E>.map(transform: (D) -> R): Resource<R, E> {
    return when (this) {
        is Resource.Success -> Resource.Success(transform(data))
        is Resource.Error -> Resource.Error(error)
        Resource.Loading -> Resource.Loading
    }
}

fun <D, E : ResourceError> Resource<D, E>.toEmptyResource(): Resource<Unit, E> {
    return when (this) {
        is Resource.Success -> Resource.Success(Unit)
        is Resource.Error -> Resource.Error(error)
        Resource.Loading -> Resource.Loading
    }
}

fun <D, E : ResourceError, R> Flow<Resource<D, E>>.mapResource(transform: (D) -> R): Flow<Resource<R, E>> {
    return this.map { it.map(transform) }
}

fun <D, E : ResourceError> Flow<Resource<D, E>>.toEmptyResource(): Flow<Resource<Unit, E>> {
    return this.map { it.toEmptyResource() }
}

