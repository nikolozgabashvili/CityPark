package ge.tbca.city_park.presentation.features.auth.screen.change_password

sealed interface ChangePasswordEvent {
    data object BackButtonClicked : ChangePasswordEvent
    data object ChangePasswordButtonClicked : ChangePasswordEvent
    data class OldPasswordChanged(val oldPassword: String) : ChangePasswordEvent
    data class NewPasswordChanged(val newPassword: String) : ChangePasswordEvent
    data class RepeatNewPasswordChanged(val repeatNewPassword: String) : ChangePasswordEvent
    data object OldPasswordVisibilityChanged : ChangePasswordEvent
    data object NewPasswordVisibilityChanged : ChangePasswordEvent
    data object RepeatNewPasswordVisibilityChanged : ChangePasswordEvent
}