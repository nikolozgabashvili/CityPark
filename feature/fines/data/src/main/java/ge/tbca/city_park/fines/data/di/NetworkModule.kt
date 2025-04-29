package ge.tbca.city_park.fines.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.fines.data.service.FineApiService
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFineApiService(
        retrofit: Retrofit
    ): FineApiService {
        return retrofit.create(FineApiService::class.java)
    }
}