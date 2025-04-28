package ge.tbca.city_park.reservation.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.reservation.domain.model.ReservationRequest
import ge.tbca.city_park.reservation.domain.repository.ReservationsRepository
import ge.tbca.city_park.user.domain.usecase.FetchUserInfoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateReservationUseCase @Inject constructor(
    private val reservationsRepository: ReservationsRepository,
    private val getUserInfoUseCase: FetchUserInfoUseCase
) {

    operator fun invoke(reservation: ReservationRequest): Flow<Resource<Unit, ApiError>> = flow {
        emit(Resource.Loading)

        val userInfoResult = getUserInfoUseCase().filter { it !is Resource.Loading }.first()

        when (userInfoResult) {
            is Resource.Error -> {
                emit(Resource.Error(userInfoResult.error))
            }

            is Resource.Success -> {
                val userInfo = userInfoResult.data

                if (userInfo.parkingBalance == 0.0) {
                    emit(Resource.Error(ApiError.INSUFFICIENT_BALANCE))
                    return@flow
                }

                reservationsRepository.createReservation(reservation).collect { result ->
                    if (result !is Resource.Loading) {
                        emit(result)
                    }
                }
            }

            // this should never happen since user info result is filtered not to include loading
            else -> {
                emit(Resource.Error(ApiError.UNKNOWN_ERROR))
            }
        }
    }
}