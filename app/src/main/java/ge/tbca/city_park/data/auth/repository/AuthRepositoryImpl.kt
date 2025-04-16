package ge.tbca.city_park.data.auth.repository

import com.google.firebase.auth.FirebaseAuth
import ge.tbca.city_park.data.auth.helper.AuthHelper
import ge.tbca.city_park.domain.core.util.NetworkError
import ge.tbca.city_park.domain.core.util.Resource
import ge.tbca.city_park.domain.features.auth.repository.AuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val authHelper: AuthHelper
) : AuthRepository {
    override fun logIn(
        email: String,
        password: String
    ): Flow<Resource<Unit, NetworkError>> {
        return authHelper.safeCall {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }
    }

    override fun signOut(): Flow<Resource<Unit, NetworkError>> {
        return authHelper.safeCall {
            firebaseAuth.signOut()
        }
    }

    override fun getAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            (auth.currentUser == null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }
}