package ge.tbca.city_park.payment.data.repository

import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.domain.error.CreditCardError
import ge.tbca.city_park.payment.domain.model.CreditCard
import ge.tbca.city_park.payment.domain.repository.CreditCardRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeCreditCardRepository @Inject constructor() : CreditCardRepository {
    override fun addCreditCard(creditCard: CreditCard): Flow<Resource<Unit, CreditCardError>> =
        flow {
            emit(Resource.Loading)
            delay(2000L)
            emit(Resource.Success(Unit))
        }

    override fun getAllCreditCards(): Flow<Resource<List<CreditCard>, CreditCardError>> = flow {
        emit(Resource.Loading)
        delay(2000L)
        emit(Resource.Success(emptyList()))
    }

    override fun getCreditCardById(id: String): Flow<Resource<CreditCard, CreditCardError>> = flow {
        emit(Resource.Loading)
        delay(2000L)
        emit(
            Resource.Success(
                CreditCard(
                    id = "",
                    cardNumber = "",
                    expireDate = "",
                    cvv = "123",
                    cardHolderName = "name"
                )
            )
        )
    }

    override fun deleteCreditCardById(id: String): Flow<Resource<Unit, CreditCardError>> = flow {
        emit(Resource.Loading)
        delay(2000L)
        emit(Resource.Success(Unit))
    }
}