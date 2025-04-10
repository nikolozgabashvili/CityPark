package ge.tbca.city_park.presentation.features.register.screen

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
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.design_system.components.button.base.ButtonSize
import ge.tbca.city_park.presentation.core.design_system.components.button.icon_button.TertiaryIconButton
import ge.tbca.city_park.presentation.core.design_system.components.button.text_button.PrimaryButton
import ge.tbca.city_park.presentation.core.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.core.design_system.components.list.password_requirement.PasswordRequirement
import ge.tbca.city_park.presentation.core.design_system.components.text_field.PasswordTextField
import ge.tbca.city_park.presentation.core.design_system.components.text_field.TextInputField
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.theme.GoogleIcon
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.model.PasswordValidationState
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun RegisterScreenRoot(viewModel: RegisterViewModel = hiltViewModel()) {

    val scrollState = rememberScrollState()

    CollectSideEffect(flow = viewModel.effect) { effect ->

    }

    RegisterScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun RegisterScreen(
    state: RegisterState,
    scrollState: ScrollState,
    onEvent: (RegisterEvent) -> Unit,
) {

    val emailError = if (state.showEmailError) stringResource(R.string.enter_valid_email) else null
    val passwordError =
        if (state.showPasswordError) stringResource(R.string.enter_valid_password) else null
    val repeatPasswordError =
        if (state.showRepeatPasswordError) stringResource(R.string.repeat_password_error) else null
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.register),
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            onStartIconClick = { onEvent(RegisterEvent.BackButtonClicked) },
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        // google login button
        TertiaryIconButton(
            modifier = Modifier.fillMaxWidth(),
            icon = GoogleIcon,
            onClick = { onEvent(RegisterEvent.GoogleButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        Divider(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.or)
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            errorText = emailError,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            startIcon = Icons.Rounded.Email,
            label = stringResource(R.string.email),
            onTextChanged = { onEvent(RegisterEvent.EmailChanged(it)) },
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            errorText = passwordError,
            label = stringResource(R.string.password),
            startIcon = Icons.Rounded.Lock,
            imeAction = ImeAction.Next,
            isPasswordVisible = state.isPasswordVisible,
            onTextChanged = { onEvent(RegisterEvent.PasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(RegisterEvent.PasswordVisibilityChanged) }
        )

        PasswordRequirement(
            isVisible = state.showPasswordRequirements,
            state = state.passwordValidationState
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.repeatPassword,
            imeAction = ImeAction.Done,
            errorText = repeatPasswordError,
            startIcon = Icons.Rounded.Lock,
            label = stringResource(R.string.repeat_password),
            isPasswordVisible = state.isRepeatPasswordVisible,
            onTextChanged = { onEvent(RegisterEvent.RepeatPasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(RegisterEvent.RepeatPasswordVisibilityChanged) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            buttonSize = ButtonSize.LARGE,
            text = stringResource(R.string.create_an_account),
            onClick = {
                onEvent(RegisterEvent.RegisterButtonClicked)
            }
        )
    }
}

@Composable
@AppPreview
private fun RegisterScreenPreview() {
    AppTheme {
        RegisterScreen(
            state = RegisterState(
                email = "",
                password = "password",
                isPasswordVisible = false,
                isLoading = false,
                passwordValidationState = PasswordValidationState(
                    hasMinLength = true,
                    hasUpperCase = true,
                    hasLowerCase = false,
                    hasDigit = false,
                    hasSpecialChar = false
                )
            ),
            onEvent = {},
            scrollState = rememberScrollState()
        )
    }
}

@Composable
@AppPreview
private fun RegisterScreenPreviewWithValidInputs() {
    AppTheme {
        RegisterScreen(
            state = RegisterState(
                email = "",
                password = "password",
                isPasswordVisible = false,
                isLoading = false,
                passwordValidationState = PasswordValidationState(
                    hasMinLength = true,
                    hasUpperCase = true,
                    hasLowerCase = true,
                    hasDigit = true,
                    hasSpecialChar = true
                )
            ),
            onEvent = {},
            scrollState = rememberScrollState()
        )
    }
}