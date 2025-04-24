package ge.tbca.city_park.cars.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.cars.data.service.CarApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesCarApiService(
        retrofit: Retrofit
    ): CarApiService {
        return retrofit.create(CarApiService::class.java)
    }

}