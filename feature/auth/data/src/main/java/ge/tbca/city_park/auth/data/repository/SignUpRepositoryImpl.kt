package ge.tbca.city_park.auth.data.repository

import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import ge.tbca.city_park.auth.data.worker.SignUpUserWorker
import ge.tbca.city_park.auth.domain.error.AuthError
import ge.tbca.city_park.auth.domain.repository.SignUpRepository
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val workManager: WorkManager
) : SignUpRepository {

    override fun register(
        email: String,
        password: String
    ): Flow<Resource<Unit, AuthError>> {

        return flow {
            emit(Resource.Loading)
            val registerRequest = OneTimeWorkRequest.Builder(SignUpUserWorker::class.java)
                .setInputData(
                    workDataOf(
                        SignUpUserWorker.EMAIL to email,
                        SignUpUserWorker.PASSWORD to password
                    )
                )
                .build()

            workManager.beginUniqueWork(
                SIGN_UP_USER_WORKER, ExistingWorkPolicy.KEEP, registerRequest
            ).enqueue()

            val registerRequestFlow = workManager.getWorkInfoByIdFlow(registerRequest.id)
            registerRequestFlow.collect { registerWorkInfo ->

                when (registerWorkInfo?.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        emit(Resource.Success(Unit))
                    }

                    WorkInfo.State.FAILED -> {
                        val errorMessage =
                            registerWorkInfo.outputData.getString(SignUpUserWorker.ERROR_MESSAGE)
                        errorMessage?.let {
                            emit(Resource.Error(AuthError.valueOf(errorMessage)))
                        } ?: run {
                            emit(Resource.Error(AuthError.UNKNOWN))
                        }
                    }

                    WorkInfo.State.CANCELLED -> {
                        emit(Resource.Error(AuthError.UNKNOWN))
                    }

                    else -> Unit
                }
            }

        }
    }

    companion object {
        const val SIGN_UP_USER_WORKER = "sign_up_user_worker"
    }


}