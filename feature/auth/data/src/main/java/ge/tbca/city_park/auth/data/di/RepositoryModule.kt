package ge.tbca.city_park.auth.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.auth.data.repository.LoginRepositoryImpl
import ge.tbca.city_park.auth.data.repository.PasswordRepositoryImpl
import ge.tbca.city_park.auth.data.repository.SignUpRepositoryImpl
import ge.tbca.city_park.auth.domain.repository.LoginRepository
import ge.tbca.city_park.auth.domain.repository.PasswordRepository
import ge.tbca.city_park.auth.domain.repository.SignUpRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindSignUpRepository(impl: SignUpRepositoryImpl): SignUpRepository

    @Singleton
    @Binds
    abstract fun bindsPasswordRepository(impl: PasswordRepositoryImpl): PasswordRepository

    @Singleton
    @Binds
    abstract fun bindsAuthRepository(impl: LoginRepositoryImpl): LoginRepository
}