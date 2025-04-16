package ge.tbca.city_park.data.auth.repository

import com.google.firebase.auth.FirebaseAuth
import ge.tbca.city_park.data.auth.helper.AuthActionType
import ge.tbca.city_park.data.auth.helper.AuthHelper
import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.domain.core.util.Resource
import ge.tbca.city_park.domain.features.auth.repository.SignUpRepository
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
    ): Flow<Resource<Unit, NetworkError>> {
        return authHelper.safeCall(AuthActionType.REGISTER) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        }
    }
}