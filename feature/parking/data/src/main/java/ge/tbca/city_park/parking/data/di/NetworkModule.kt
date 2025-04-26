package ge.tbca.city_park.parking.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.parking.data.service.ParkingApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideParkingApiService(retrofit: Retrofit): ParkingApiService {
        return retrofit.create(ParkingApiService::class.java)
    }
}