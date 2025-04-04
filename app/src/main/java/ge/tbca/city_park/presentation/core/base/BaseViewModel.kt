package ge.tbca.city_park.presentation.core.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<STATE, EFFECT, EVENT>(
    initialState: STATE
) : ViewModel() {

    var state by mutableStateOf(initialState)
        private set


    private val _effect = Channel<EFFECT>()
    val effect = _effect.receiveAsFlow()

    protected fun updateState(reducer: STATE.() -> STATE) {
        state = reducer(state)
    }

    protected suspend fun sendSideEffect(sideEffect: EFFECT) {
        _effect.send(sideEffect)
    }

    abstract fun onEvent(event: EVENT)
}