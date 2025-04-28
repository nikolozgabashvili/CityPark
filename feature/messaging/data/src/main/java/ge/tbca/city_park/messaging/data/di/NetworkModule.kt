package ge.tbca.city_park.messaging.data.di

import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.messaging.data.service.TokenApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideTokenApiService(
        retrofit: Retrofit
    ): TokenApiService {
        return retrofit.create(TokenApiService::class.java)
    }


    @Provides
    @Singleton
    fun providesFirebaseMessaging(): FirebaseMessaging {
        return FirebaseMessaging.getInstance()
    }
}