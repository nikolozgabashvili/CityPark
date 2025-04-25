package ge.tbca.city_park.payment.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.payment.data.service.CreditCardService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCreditCardService(retrofit: Retrofit): CreditCardService {
        return retrofit.create(CreditCardService::class.java)
    }
}