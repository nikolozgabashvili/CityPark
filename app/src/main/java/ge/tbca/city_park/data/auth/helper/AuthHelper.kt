package ge.tbca.city_park.data.auth.helper


import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.domain.core.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.cancellation.CancellationException

class AuthHelper {
    fun <T> safeCall(
        isRegistering: Boolean = false,
        call: suspend () -> T
    ): Flow<Resource<T, NetworkError>> {
        return flow {
            try {
                emit(Resource.Loading)
                val result = call()
                emit(Resource.Success(result))
            } catch (e: Exception) {

                val error = when (e) {
                    is CancellationException -> {
                        throw e
                    }

                    is FirebaseAuthInvalidCredentialsException -> {
                        if (isRegistering) NetworkError.INVALID_EMAIL_FORMAT
                        else NetworkError.INVALID_CREDENTIALS
                    }

                    is FirebaseNetworkException -> {
                        NetworkError.NETWORK_ERROR
                    }

                    is FirebaseAuthUserCollisionException -> {
                        NetworkError.USER_COLLISION
                    }


                    else -> {
                        NetworkError.USER_COLLISION
                    }
                }

                emit(Resource.Error(error))
            }

        }.flowOn(Dispatchers.IO)

    }
}