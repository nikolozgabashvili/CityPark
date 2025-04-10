package ge.tbca.city_park.presentation.features.change_password.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.design_system.components.button.base.ButtonSize
import ge.tbca.city_park.presentation.core.design_system.components.button.text_button.PrimaryButton
import ge.tbca.city_park.presentation.core.design_system.components.list.password_requirement.PasswordRequirement
import ge.tbca.city_park.presentation.core.design_system.components.text_field.PasswordTextField
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.model.PasswordValidationState
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun ChangePasswordScreenRoot(viewModel: ChangePasswordViewModel = hiltViewModel()) {

    val scrollState = rememberScrollState()

    CollectSideEffect(flow = viewModel.effect) { effect ->

    }

    ChangePasswordScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ChangePasswordScreen(
    state: ChangePasswordState,
    scrollState: ScrollState,
    onEvent: (ChangePasswordEvent) -> Unit,
) {
    val oldPasswordError =
        if (state.showOldPasswordError) stringResource(R.string.old_password_is_incorrect) else null
    val newPasswordError =
        if (state.showNewPasswordError) stringResource(R.string.enter_valid_password) else null
    val repeatPasswordError =
        if (state.showRepeatPasswordError) stringResource(R.string.repeat_password_error) else null
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState, enabled = !state.isLoading)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.change_password),
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            onStartIconClick = { onEvent(ChangePasswordEvent.BackButtonClicked) },
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            value = state.oldPassword,
            errorText = oldPasswordError,
            label = stringResource(R.string.old_password),
            startIcon = Icons.Rounded.Lock,
            imeAction = ImeAction.Next,
            isPasswordVisible = state.isOldPasswordVisible,
            onTextChanged = { onEvent(ChangePasswordEvent.OldPasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(ChangePasswordEvent.OldPasswordVisibilityChanged) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            value = state.newPassword,
            errorText = newPasswordError,
            label = stringResource(R.string.new_password),
            startIcon = Icons.Rounded.Lock,
            imeAction = ImeAction.Next,
            isPasswordVisible = state.isNewPasswordVisible,
            onTextChanged = { onEvent(ChangePasswordEvent.NewPasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(ChangePasswordEvent.NewPasswordVisibilityChanged) }
        )

        PasswordRequirement(
            isVisible = state.showPasswordRequirements,
            state = state.passwordValidationState
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            value = state.repeatNewPassword,
            imeAction = ImeAction.Done,
            errorText = repeatPasswordError,
            startIcon = Icons.Rounded.Lock,
            label = stringResource(R.string.repeat_new_password),
            isPasswordVisible = state.isRepeatNewPasswordVisible,
            onTextChanged = { onEvent(ChangePasswordEvent.RepeatNewPasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(ChangePasswordEvent.RepeatNewPasswordVisibilityChanged) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            buttonSize = ButtonSize.LARGE,
            text = stringResource(R.string.change_password),
            onClick = {
                onEvent(ChangePasswordEvent.ChangePasswordButtonClicked)
            }
        )
    }
}

@Composable
@AppPreview
private fun ChangePasswordScreenPreview() {
    AppTheme {
        ChangePasswordScreen(
            state = ChangePasswordState(
                oldPassword = "",
                newPassword = "password",
                repeatNewPassword = "password",
                passwordValidationState = PasswordValidationState(
                    hasMinLength = true,
                    hasUpperCase = true,
                    hasLowerCase = false,
                    hasDigit = false,
                    hasSpecialChar = false
                )
            ),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}