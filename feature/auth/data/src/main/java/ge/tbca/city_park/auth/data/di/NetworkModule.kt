package ge.tbca.city_park.auth.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.auth.data.helper.AuthHelper
import ge.tbca.city_park.auth.data.service.UserService
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesAuthHelper(): AuthHelper {
        return AuthHelper()
    }

    @Singleton
    @Provides
    fun providesUserService(
        retrofit: Retrofit
    ): UserService {
        return retrofit.create(UserService::class.java)
    }
}