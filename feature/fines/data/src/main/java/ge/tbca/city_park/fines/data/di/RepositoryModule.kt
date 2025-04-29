package ge.tbca.city_park.fines.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.fines.data.repository.FineRepositoryIml
import ge.tbca.city_park.fines.domain.repository.FinesRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFinesRepository(
        finesRepositoryImpl: FineRepositoryIml
    ): FinesRepository



}