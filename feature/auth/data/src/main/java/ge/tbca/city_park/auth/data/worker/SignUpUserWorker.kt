package ge.tbca.city_park.auth.data.worker

import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.firebase.auth.FirebaseAuth
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ge.tbca.city_park.auth.data.R
import ge.tbca.city_park.auth.data.helper.AuthActionType
import ge.tbca.city_park.auth.data.helper.AuthHelper
import ge.tbca.city_park.auth.data.model.AddUserRequest
import ge.tbca.city_park.auth.data.service.UserService
import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.tasks.await

@HiltWorker
class SignUpUserWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val authHelper: AuthHelper,
    private val firebaseAuth: FirebaseAuth,
    private val apiHelper: ApiHelper,
    private val userApiService: UserService
) : CoroutineWorker(appContext, workerParams) {


    override suspend fun doWork(): Result {

        setForeground(createForegroundInfo())
        val email = inputData.getString(EMAIL) ?: return Result.failure()
        val password = inputData.getString(PASSWORD) ?: return Result.failure()
        val response = authHelper.safeCallNoLoading(actionType = AuthActionType.REGISTER) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        }

        return if (response is Resource.Success) {

            val uploadResponse = apiHelper.safeCallNoLoading {
                userApiService.setupUser(AddUserRequest(email))
            }

            return if (uploadResponse is Resource.Success) {
                Result.success()
            } else if (runAttemptCount < MAX_RETRY_COUNT) {
                Result.retry()
            } else {
                firebaseAuth.currentUser?.delete()
                Result.failure(workDataOf(ERROR_MESSAGE to AuthError.UNKNOWN))
            }
        } else {
            if (response is Resource.Error) Result.failure(workDataOf(ERROR_MESSAGE to response.error.name))
            else Result.failure(workDataOf(ERROR_MESSAGE to AuthError.UNKNOWN))
        }
    }

    private fun createForegroundInfo(): ForegroundInfo {


        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("title")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setTicker("title")
            .setOngoing(true)
            .build()

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ForegroundInfo(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            ForegroundInfo(1, notification)
        }
    }


    companion object {
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val ERROR_MESSAGE = "error_message"
        private const val MAX_RETRY_COUNT = 3
        const val CHANNEL_ID = "upload_user_channel"
    }
}