package ge.tbca.city_park.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import ge.tbca.city_park.auth.data.helper.AuthHelper
import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.auth.domain.repository.LoginRepository
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val authHelper: AuthHelper
) : LoginRepository {
    override fun logIn(
        email: String,
        password: String
    ): Flow<Resource<Unit, AuthError>> {
        return authHelper.safeCall {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }
    }

    override fun signOut(): Flow<Resource<Unit,AuthError>> {
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