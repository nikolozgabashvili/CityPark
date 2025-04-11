package ge.tbca.city_park.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.data.repository.DataStoreRepositoryImpl
import ge.tbca.city_park.data.repository.ThemePreferenceRepositoryImpl
import ge.tbca.city_park.domain.repository.DataStoreRepository
import ge.tbca.city_park.domain.repository.ThemePreferenceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDatastoreRepository(
        datastoreRepositoryImpl: DataStoreRepositoryImpl
    ): DataStoreRepository

    @Binds
    @Singleton
    abstract fun bindThemePreferenceRepository(
        themePreferenceRepositoryImpl: ThemePreferenceRepositoryImpl
    ): ThemePreferenceRepository
}