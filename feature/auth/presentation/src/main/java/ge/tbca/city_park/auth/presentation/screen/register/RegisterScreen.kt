package ge.tbca.city_park.auth.presentation.screen.register

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.icon_button.SecondaryIconButton
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.divider.Divider
import ge.tbca.city_park.auth.presentation.component.password_requirement.PasswordRequirement
import com.example.core.designsystem.components.text_field.PasswordTextField
import com.example.core.designsystem.components.text_field.TextInputField
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.GoogleIcon
import com.example.core.designsystem.util.AppPreview
import ge.tbca.citi_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.auth.domain.model.PasswordValidationState
import ge.tbca.city_park.auth.presentation.R

@Composable
fun RegisterScreenRoot(
    onShowSnackBar:  (String) -> Unit,
    navigateBack: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->

        when (effect) {
            is RegisterEffect.NavigateBack -> navigateBack()
            is RegisterEffect.Error -> onShowSnackBar(effect.error.getString(context))
        }

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
            .verticalScroll(scrollState, enabled = !state.isLoading)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.register),
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            onStartIconClick = { onEvent(RegisterEvent.BackButtonClicked) },
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        SecondaryIconButton(
            enabled = !state.isLoading,
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
            enabled = !state.isLoading,
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
            enabled = !state.isLoading,
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
            enabled = !state.isLoading,
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
            loading = state.isLoading,
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