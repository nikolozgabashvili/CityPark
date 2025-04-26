package ge.tbca.city_park.auth.data.helper

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
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
     * Emits [Resource.Error] with a [AuthError] if an exception occurs.
     */
    fun <T> safeCall(
        actionType: AuthActionType = AuthActionType.OTHER, call: suspend () -> T
    ): Flow<Resource<T, AuthError>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCallNoLoading(actionType = actionType, call = call))

        }
    }

    /**
     * Executes a suspendable function safely and emits its result as a Flow.
     *
     * @param T The type of the result returned by the suspendable function.
     * @param call A suspendable function to be executed.
     * @return A Flow emitting the result of the function call wrapped in a Resource object.
     * Emits [Resource.Success] with the result if the operation succeeds.
     * Emits [Resource.Error] with a [AuthError] if an exception occurs.
     */
    suspend fun <T> safeCallNoLoading(
        actionType: AuthActionType = AuthActionType.OTHER, call: suspend () -> T
    ): Resource<T, AuthError> {
        return withContext(Dispatchers.IO) {
            try {
                val result = call()
                Resource.Success(result)
            } catch (e: Exception) {
                e.printStackTrace()
                val error = when (e) {
                    is CancellationException -> {
                        throw e
                    }

                    is FirebaseAuthInvalidCredentialsException -> {
                        when (actionType) {
                            AuthActionType.REGISTER -> AuthError.INVALID_EMAIL_FORMAT
                            AuthActionType.CHANGE_PASSWORD -> AuthError.WRONG_OLD_PASSWORD
                            else -> AuthError.INVALID_CREDENTIALS
                        }
                    }

                    is FirebaseNetworkException -> {
                        AuthError.NETWORK_ERROR
                    }

                    is FirebaseAuthUserCollisionException -> {
                        AuthError.USER_COLLISION
                    }


                    else -> {
                        AuthError.UNKNOWN
                    }
                }

                Resource.Error(error)
            }

        }
    }
}