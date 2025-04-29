package ge.tbca.city_park.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.core.data.connectivity_manager.ConnectivityObserverImpl
import ge.tbca.city_park.core.domain.connectivity_manager.ConnectivityObserver


@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityManagerModule {

    @Binds
    abstract fun bindConnectivityManager(
        connectivityObserverImpl: ConnectivityObserverImpl
    ): ConnectivityObserver
}