package ge.tbca.city_park.messaging.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.messaging.data.repository.MessagingTokenRepositoryImpl
import ge.tbca.city_park.messaging.data.repository.NotificationsRepositoryImpl
import ge.tbca.city_park.messaging.domain.repository.MessagingTokenRepository
import ge.tbca.city_park.messaging.domain.repository.NotificationsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMessagingTokenRepository(
        impl: MessagingTokenRepositoryImpl
    ): MessagingTokenRepository

    @Binds
    @Singleton
    abstract fun bindNotificationsRepository(
        notificationsRepositoryImpl: NotificationsRepositoryImpl
    ): NotificationsRepository
}