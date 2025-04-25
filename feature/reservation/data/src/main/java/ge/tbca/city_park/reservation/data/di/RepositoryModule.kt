package ge.tbca.city_park.reservation.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.tbca.city_park.reservation.data.repository.ReservationsRepositoryImpl
import ge.tbca.city_park.reservation.domain.repository.ReservationsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindReservationRepository(
        reservationRepositoryImpl: ReservationsRepositoryImpl
    ): ReservationsRepository
}