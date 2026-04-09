package com.marcosanz.app.util.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@Suppress("ComposableNaming")
abstract class BaseViewModel<Action : Any, Event : Any, State : Any>(
    protected val initialState: State
) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    protected val _event = MutableSharedFlow<Event>(extraBufferCapacity = 1)
    val event = _event.asSharedFlow()

    fun onAction(action: Action) {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.tag("ON_ACTION").i("Action: $action")
            handleAction(action)
        }
    }

    internal abstract suspend fun handleAction(action: Action) //NOSONAR ¯\_(ツ)_/¯

    protected suspend fun sendEvent(event: Event) {  //NOSONAR ¯\_(ツ)_/¯
        _event.emit(event)
    }

    protected fun trySendEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    @Composable
    fun collectUiState() = uiState.collectAsStateWithLifecycle()

    @Composable
    fun collectEvent(block: suspend (Event) -> Unit) {
        LaunchedEffect(Unit) {
            event.collect {
                block(it)
            }
        }
    }
}