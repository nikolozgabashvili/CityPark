package ge.tbca.city_park.core.data.di

import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.core.data.BuildConfig
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.data.interceptor.NetworkInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun providesNetworkInterceptor(
        firebaseAuth: FirebaseAuth
    ): NetworkInterceptor {
        return NetworkInterceptor(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        httpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().apply {
            addInterceptor(networkInterceptor)
            if (BuildConfig.DEBUG)
                addInterceptor(loggingInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun providesApiHelper(): ApiHelper {
        return ApiHelper()
    }

    @Provides
    @Singleton
    fun providesJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }
}