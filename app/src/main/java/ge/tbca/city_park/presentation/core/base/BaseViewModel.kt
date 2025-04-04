package ge.tbca.city_park.presentation.core.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<STATE, UI_STATE, SIDE_EFFECT, EVENT>(
    initialState: STATE,
    initialUiState: UI_STATE
) : ViewModel() {

    var state by mutableStateOf(initialState)
        private set

    var uiState by mutableStateOf(initialUiState)
        private set

    private val _sideEffects = Channel<SIDE_EFFECT>()
    val sideEffects = _sideEffects.receiveAsFlow()

    protected fun updateState(reducer: STATE.() -> STATE) {
        state = reducer(state)
    }

    protected fun updateUiState(reducer: UI_STATE.() -> UI_STATE) {
        uiState = reducer(uiState)
    }

    protected suspend fun sendSideEffect(sideEffect: SIDE_EFFECT) {
        _sideEffects.send(sideEffect)
    }

    abstract fun onEvent(event: EVENT)
}