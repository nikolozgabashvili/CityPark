package ge.tbca.city_park.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.data.repository.DataStoreManagerImpl
import ge.tbca.city_park.data.repository.ThemePreferenceRepositoryImpl
import ge.tbca.city_park.domain.repository.DataStoreManager
import ge.tbca.city_park.domain.repository.ThemePreferenceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDatastoreManager(
        dataStoreManagerImpl: DataStoreManagerImpl
    ): DataStoreManager

    @Binds
    @Singleton
    abstract fun bindThemePreferenceRepository(
        themePreferenceRepositoryImpl: ThemePreferenceRepositoryImpl
    ): ThemePreferenceRepository
}