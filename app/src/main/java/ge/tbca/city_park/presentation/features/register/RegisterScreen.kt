package ge.tbca.city_park.presentation.features.register

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.model.PasswordValidationState
import ge.tbca.city_park.presentation.ui.design_system.components.button.PrimaryButton
import ge.tbca.city_park.presentation.ui.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.ui.design_system.components.password_requirement.PasswordRequirement
import ge.tbca.city_park.presentation.ui.design_system.components.text_field.PasswordTextField
import ge.tbca.city_park.presentation.ui.design_system.components.text_field.TextInputField
import ge.tbca.city_park.presentation.ui.design_system.util.AppPreview
import ge.tbca.city_park.presentation.ui.theme.AppColors
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.AppTypography
import ge.tbca.city_park.presentation.ui.theme.Dimen
import ge.tbca.city_park.presentation.util.CollectSideEffect

@Composable
fun RegisterScreenRoot(viewModel: RegisterViewModel = hiltViewModel()) {

    val scrollState = rememberScrollState()

    RegisterScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )

    CollectSideEffect(flow = viewModel.effect) { effect ->

    }
}

@Composable
private fun RegisterScreen(
    state: RegisterState,
    scrollState: ScrollState,
    onEvent: (RegisterEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(Dimen.appPadding)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { onEvent(RegisterEvent.BackButtonClicked) }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = AppColors.primary
                )
            }

            Text(
                modifier = Modifier.align(alignment = Alignment.Center),
                text = stringResource(R.string.register),
                color = AppColors.primary,
                style = AppTypography.bodyMedium
            )
        }

        // google login button
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimen.size32),
            text = "",
            onClick = { onEvent(RegisterEvent.GoogleLoginButtonClicked) }
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
            startIcon = Icons.Rounded.Lock,
            label = stringResource(R.string.repeat_password),
            isPasswordVisible = state.isRepeatPasswordVisible,
            onTextChanged = { onEvent(RegisterEvent.RepeatPasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(RegisterEvent.RepeatPasswordVisibilityChanged) }
        )

        Spacer(modifier=Modifier.height(Dimen.size16))

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.create_an_account),
            onClick = { onEvent(RegisterEvent.RegisterButtonClicked) }
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