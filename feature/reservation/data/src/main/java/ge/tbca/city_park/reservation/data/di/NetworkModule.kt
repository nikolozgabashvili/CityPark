package ge.tbca.city_park.reservation.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.reservation.data.service.ReservationApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesReservationApiService(
        retrofit: Retrofit
    ): ReservationApiService {
        return retrofit.create(ReservationApiService::class.java)
    }
}