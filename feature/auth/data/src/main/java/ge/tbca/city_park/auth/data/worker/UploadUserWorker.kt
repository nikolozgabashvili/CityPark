package ge.tbca.city_park.auth.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ge.tbca.city_park.auth.data.model.AddUserRequest
import ge.tbca.city_park.auth.data.service.UserService
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.Resource

@HiltWorker
class UploadUserWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val apiHelper: ApiHelper,
    private val userApiService: UserService
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        val email = inputData.getString(EMAIL) ?: return Result.failure()
        val response = apiHelper.safeCallNoLoading {
            userApiService.setupUser(AddUserRequest(email))
        }

        return if (response is Resource.Success) {
            Result.success()
        } else if (runAttemptCount < MAX_RETRY_COUNT) {
            Result.retry()
        } else if (response is Resource.Error) {
            Result.failure()
        } else {
            Result.failure()
        }
    }


    companion object {
        const val EMAIL = "email"
        private const val MAX_RETRY_COUNT = 3
    }
}