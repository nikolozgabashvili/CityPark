package ge.tbca.city_park.data.auth.repository

import com.google.firebase.auth.FirebaseAuth
import ge.tbca.city_park.data.auth.helper.AuthHelper
import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.domain.core.util.Resource
import ge.tbca.city_park.domain.features.auth.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class PasswordRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val authHelper: AuthHelper
) : PasswordRepository {
    override fun recoverPassword(email: String): Flow<Resource<Unit, NetworkError>> {
        return authHelper.safeCall {
            firebaseAuth.sendPasswordResetEmail(email).await()
        }
    }

    override fun changePassword(
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<Unit, NetworkError>> {
        return authHelper.safeCall {
            firebaseAuth.currentUser?.updatePassword(newPassword)
        }
    }

}