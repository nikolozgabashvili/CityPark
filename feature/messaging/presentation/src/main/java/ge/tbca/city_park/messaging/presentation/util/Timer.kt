package ge.tbca.city_park.messaging.presentation.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

object Timer {
    fun timeAndEmit(): Flow<Duration> {
        return flow {
            var lastEmitTime = System.currentTimeMillis()
            while (true) {
                delay(200)
                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - lastEmitTime
                emit(elapsedTime.milliseconds)
                lastEmitTime = currentTime

            }
        }
    }
}