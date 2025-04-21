package ge.tbca.city_park.auth.data.repository

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import ge.tbca.city_park.auth.data.helper.AuthActionType
import ge.tbca.city_park.auth.data.helper.AuthHelper
import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.auth.domain.repository.PasswordRepository
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class PasswordRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val authHelper: AuthHelper
) : PasswordRepository {
    override fun recoverPassword(email: String): Flow<Resource<Unit, AuthError>> {
        return authHelper.safeCall {
            firebaseAuth.sendPasswordResetEmail(email).await()
        }
    }

    override fun changePassword(
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<Unit, AuthError>> {
        return authHelper.safeCall(actionType = AuthActionType.CHANGE_PASSWORD) {

            val user = firebaseAuth.currentUser
            val credentials = EmailAuthProvider.getCredential(user?.email!!,oldPassword)
            user.reauthenticate(credentials).await()
            user.updatePassword(newPassword).await()

        }
    }

}