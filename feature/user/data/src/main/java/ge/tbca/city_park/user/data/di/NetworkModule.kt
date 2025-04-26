package ge.tbca.city_park.user.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.user.data.service.UserApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideUserApiService(
        retrofit: Retrofit
    ): UserApiService {
        // Implementation of UserApiService
        return retrofit.create(UserApiService::class.java)
    }
}