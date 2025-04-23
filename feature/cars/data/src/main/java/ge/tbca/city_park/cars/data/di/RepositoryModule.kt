package ge.tbca.city_park.cars.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.cars.data.repository.FakeCarsRepositoryImpl
import ge.tbca.city_park.cars.domain.repository.CarsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCarsRepository(
        fakeCarsRepositoryImpl: FakeCarsRepositoryImpl
    ): CarsRepository
}