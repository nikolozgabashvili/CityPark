package ge.tbca.city_park.fines.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.fines.domain.model.PayFineRequest
import ge.tbca.city_park.fines.domain.repository.FinesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PayFineUseCase @Inject constructor(private val finesRepository: FinesRepository) {

    operator fun invoke(payFineRequest: PayFineRequest): Flow<Resource<Unit, ApiError>> {
        return finesRepository.payFine(payFineRequest)
    }
}