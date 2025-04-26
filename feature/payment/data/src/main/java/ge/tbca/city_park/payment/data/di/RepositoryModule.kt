package ge.tbca.city_park.payment.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.payment.data.repository.CreditCardRepositoryImpl
import ge.tbca.city_park.payment.data.repository.TransactionRepositoryImpl
import ge.tbca.city_park.payment.domain.repository.CreditCardRepository
import ge.tbca.city_park.payment.domain.repository.TransactionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCreditCardRepository(
        creditCardRepositoryImpl: CreditCardRepositoryImpl
    ): CreditCardRepository

    @Singleton
    @Binds
    abstract fun bindTransactionRepository(
        transactionRepositoryImpl: TransactionRepositoryImpl
    ): TransactionRepository
}