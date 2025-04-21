package ge.tbca.city_park.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import ge.tbca.city_park.auth.data.helper.AuthActionType
import ge.tbca.city_park.auth.data.helper.AuthHelper
import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.auth.domain.repository.SignUpRepository
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val authHelper: AuthHelper
) : SignUpRepository {

    override fun register(
        email: String,
        password: String
    ): Flow<Resource<Unit, AuthError>> {
        return authHelper.safeCall(AuthActionType.REGISTER) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        }
    }
}