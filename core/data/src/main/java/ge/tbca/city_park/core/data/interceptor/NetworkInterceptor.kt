package ge.tbca.city_park.core.data.interceptor

import com.google.firebase.auth.FirebaseAuth
import ge.tbca.city_park.core.data.util.UserId
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Invocation

class NetworkInterceptor(
    private val firebaseAuth: FirebaseAuth
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val invocation = request.tag(Invocation::class.java)
        val annotations = invocation?.method()?.annotations
        val modifiedRequest = request.newBuilder().apply {
            annotations?.firstOrNull { it.annotationClass == UserId::class }?.let {
                val userId = firebaseAuth.currentUser?.uid ?: return Response.Builder()
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .code(401)
                    .message("Unauthorized")
                    .body("Unauthorized".toResponseBody(null))
                    .build()

                addHeader(USER_ID_HEADER, userId)
            }
        }.build()

        return chain.proceed(modifiedRequest)


    }

    companion object {
        private const val USER_ID_HEADER = "userId"
    }
}