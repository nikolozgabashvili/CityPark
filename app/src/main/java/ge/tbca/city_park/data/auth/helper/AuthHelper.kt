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

/**
 * A helper class for handling safe API calls with proper error handling.
 */
class AuthHelper {
    /**
     * Executes a suspendable function safely and emits its result as a Flow.
     *
     * @param T The type of the result returned by the suspendable function.
     * @param call A suspendable function to be executed.
     * @return A Flow emitting the result of the function call wrapped in a Resource object.
     * Emits [Resource.Loading] while the operation is in progress.
     * Emits [Resource.Success] with the result if the operation succeeds.
     * Emits [Resource.Error] with a [NetworkError] if an exception occurs.
     */
    fun <T> safeCall(
        actionType: AuthActionType = AuthActionType.OTHER,
        call: suspend () -> T
    ): Flow<Resource<T, NetworkError>> {
        return flow {
            try {
                emit(Resource.Loading)
                val result = call()
                emit(Resource.Success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                val error = when (e) {
                    is CancellationException -> {
                        throw e
                    }

                    is FirebaseAuthInvalidCredentialsException -> {
                        when (actionType) {
                            AuthActionType.REGISTER -> NetworkError.INVALID_EMAIL_FORMAT
                            AuthActionType.CHANGE_PASSWORD -> NetworkError.WRONG_OLD_PASSWORD
                            else -> NetworkError.INVALID_CREDENTIALS
                        }
                    }

                    is FirebaseNetworkException -> {
                        NetworkError.NETWORK_ERROR
                    }

                    is FirebaseAuthUserCollisionException -> {
                        NetworkError.USER_COLLISION
                    }


                    else -> {
                        NetworkError.UNKNOWN
                    }
                }

                emit(Resource.Error(error))
            }

        }.flowOn(Dispatchers.IO)

    }
}