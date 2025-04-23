package ge.tbca.city_park.payment.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.payment.data.repository.FakeCreditCardRepository
import ge.tbca.city_park.payment.domain.repository.CreditCardRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsCreditCardRepository(
        fakeCreditCardRepository: FakeCreditCardRepository
    ): CreditCardRepository
}