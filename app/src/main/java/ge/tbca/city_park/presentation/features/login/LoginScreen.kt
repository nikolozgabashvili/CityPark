package ge.tbca.city_park.presentation.features.login

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.ui.design_system.components.button.PrimaryButton
import ge.tbca.city_park.presentation.ui.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.ui.design_system.components.text_input.AuthTextField
import ge.tbca.city_park.presentation.ui.theme.AppColors
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.AppTypography
import ge.tbca.city_park.presentation.ui.theme.Dimen
import ge.tbca.city_park.presentation.ui.util.AppPreview
import ge.tbca.city_park.presentation.util.CollectSideEffect

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    LoginScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        scrollState = scrollState
    )

    CollectSideEffect(flow = viewModel.effect) { effect ->

    }
}

@Composable
private fun LoginScreen(
    onEvent: (LoginEvent) -> Unit,
    state: LoginState,
    scrollState: ScrollState
) {
    Column(
        modifier = Modifier
            .padding(top = Dimen.size24, start = Dimen.appPadding, end = Dimen.appPadding)
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.hello),
            color = AppColors.primary,
            style = AppTypography.headlineLarge
        )

        Text(
            text = stringResource(R.string.log_into_the_system),
            color = AppColors.primary,
            style = AppTypography.bodyMedium,
            modifier = Modifier.padding(top = Dimen.size8)
        )

        // google login button
        PrimaryButton(
            text = "",
            onClick = { onEvent(LoginEvent.LoginButtonClicked) },
            modifier = Modifier
                .padding(top = Dimen.size32)
                .fillMaxWidth()
        )

        Divider(
            text = stringResource(R.string.or),
            modifier = Modifier.padding(top = Dimen.size32)
        )

        AuthTextField(
            text = state.email,
            hint = stringResource(R.string.email_or_phone_number),
            onTextChanged = { onEvent(LoginEvent.EmailChanged(it)) },
            modifier = Modifier
                .padding(top = Dimen.size32)
                .fillMaxWidth()
        )

        AuthTextField(
            text = state.password,
            hint = stringResource(R.string.password),
            isTextVisible = state.isPasswordVisible,
            trailingIcon = if (state.isPasswordVisible) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
            onTextChanged = { onEvent(LoginEvent.PasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(LoginEvent.PasswordVisibilityChanged) },
            modifier = Modifier
                .padding(top = Dimen.size16)
                .fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.forgot_password),
            style = AppTypography.bodyMedium,
            color = AppColors.primary,
            modifier = Modifier
                .padding(top = Dimen.size16)
                .clickable { onEvent(LoginEvent.ForgotPasswordClicked) }
        )

        PrimaryButton(
            text = stringResource(R.string.login),
            onClick = { onEvent(LoginEvent.LoginButtonClicked) },
            modifier = Modifier
                .padding(top = Dimen.size230)
                .fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.dont_have_an_account),
            style = AppTypography.bodyMedium,
            color = AppColors.primary,
            modifier = Modifier
                .padding(top = Dimen.size16)
                .clickable { onEvent(LoginEvent.RegisterHereClicked) }
        )
    }
}

@Composable
@AppPreview
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(
            state = LoginState(
                email = "",
                password = "",
                isPasswordVisible = false,
                isLoading = false
            ),
            onEvent = {},
            scrollState = rememberScrollState()
        )
    }
}