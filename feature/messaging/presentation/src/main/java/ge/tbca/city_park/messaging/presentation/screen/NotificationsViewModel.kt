package ge.tbca.city_park.messaging.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.messaging.domain.usecase.DeleteAllNotificationsUseCase
import ge.tbca.city_park.messaging.domain.usecase.DeleteNotificationByIdUseCase
import ge.tbca.city_park.messaging.domain.usecase.GetAllNotificationsUseCase
import ge.tbca.city_park.messaging.presentation.mapper.toPresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase,
    private val deleteAllNotificationsUseCase: DeleteAllNotificationsUseCase,
    private val deleteNotificationByIdUseCase: DeleteNotificationByIdUseCase
) :
    BaseViewModel<NotificationsState, NotificationsEffect, NotificationsEvent>(NotificationsState()) {

    init {
        fetchAllNotifications()
    }

    override fun onEvent(event: NotificationsEvent) {
        when (event) {
            is NotificationsEvent.BackButtonClicked -> navigateBack()
            is NotificationsEvent.DeleteAllNotifications -> deleteAllNotifications()
            is NotificationsEvent.Refresh -> fetchAllNotifications()
            is NotificationsEvent.DeleteNotificationClicked -> deleteNotificationById(event.id)
            is NotificationsEvent.DismissDeleteAllNotificationsDialog -> dismissDeleteDialog()
            is NotificationsEvent.DeleteAllNotificationsClicked -> showDeleteDialog()
        }
    }

    private fun fetchAllNotifications() {
        viewModelScope.launch {
            getAllNotificationsUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading(), error = null) }
                when (resource) {
                    is Resource.Success -> {
                        val notifications = resource.data.toPresenter()
                        updateState {
                            copy(
                                notificationsList = notifications,
                                noNotifications = notifications.isEmpty()
                            )
                        }
                    }

                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        sendSideEffect(NotificationsEffect.Error(error))
                        if (state.notificationsList.isEmpty()) {
                            updateState { copy(error = error) }
                        }
                    }

                    is Resource.Loading -> Unit
                }
            }
        }
    }

    private fun deleteAllNotifications() {
        viewModelScope.launch {
            deleteAllNotificationsUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }
                when (resource) {
                    is Resource.Success -> {
                        dismissDeleteDialog()
                        fetchAllNotifications()
                    }

                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        sendSideEffect(NotificationsEffect.Error(error))
                    }

                    is Resource.Loading -> Unit
                }
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(NotificationsEffect.NavigateBack)
        }
    }

    private fun deleteNotificationById(id: Int) {
        viewModelScope.launch {
            deleteNotificationByIdUseCase(id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        fetchAllNotifications()
                    }

                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        sendSideEffect(NotificationsEffect.Error(error))
                    }

                    is Resource.Loading -> Unit
                }
            }
        }
    }

    private fun dismissDeleteDialog() {
        updateState { copy(showDeleteAllNotificationsDialog = false) }
    }

    private fun showDeleteDialog() {
        updateState { copy(showDeleteAllNotificationsDialog = true) }
    }
}