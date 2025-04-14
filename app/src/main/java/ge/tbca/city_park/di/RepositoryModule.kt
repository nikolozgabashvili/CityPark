package ge.tbca.city_park.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.data.auth.repository.AuthRepositoryImpl
import ge.tbca.city_park.data.auth.repository.PasswordRepositoryImpl
import ge.tbca.city_park.data.auth.repository.SignUpRepositoryImpl
import ge.tbca.city_park.domain.features.auth.repository.AuthRepository
import ge.tbca.city_park.domain.features.auth.repository.PasswordRepository
import ge.tbca.city_park.domain.features.auth.repository.SignUpRepository
import ge.tbca.city_park.data.repository.DataStoreManagerImpl
import ge.tbca.city_park.data.repository.ThemePreferenceRepositoryImpl
import ge.tbca.city_park.domain.repository.DataStoreManager
import ge.tbca.city_park.domain.repository.ThemePreferenceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Singleton
    abstract fun bindDatastoreManager(
        dataStoreManagerImpl: DataStoreManagerImpl
    ): DataStoreManager

    @Binds
    abstract fun bindSignUpRepository(impl: SignUpRepositoryImpl): SignUpRepository

    @Singleton
    @Binds
    abstract fun bindsPasswordRepository(impl: PasswordRepositoryImpl): PasswordRepository

    abstract fun bindThemePreferenceRepository(
        themePreferenceRepositoryImpl: ThemePreferenceRepositoryImpl
    ): ThemePreferenceRepository
}