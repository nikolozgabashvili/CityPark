package ge.tbca.city_park.presentation.features.login

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun LoginScreenRoot(viewModel: LoginViewModel = hiltViewModel()) {

    val scrollState = rememberScrollState()

    LoginScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent,
    )

    CollectSideEffect(flow = viewModel.effect) { effect ->

    }
}

@Composable
private fun LoginScreen(
    state: LoginState,
    scrollState: ScrollState,
    onEvent: (LoginEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(Dimen.appPadding)
    ) {
        Text(
            text = stringResource(R.string.hello),
            color = AppColors.primary,
            style = AppTypography.headlineLarge
        )

        Spacer(modifier = Modifier.height(Dimen.size8))

        Text(
            text = stringResource(R.string.log_into_the_system),
            color = AppColors.primary,
            style = AppTypography.bodyMedium
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        // google login button
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            onClick = { onEvent(LoginEvent.GoogleLoginButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        Divider(text = stringResource(R.string.or))

        Spacer(modifier = Modifier.height(Dimen.size32))

        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.email,
            hint = stringResource(R.string.email_or_phone_number),
            onTextChanged = { onEvent(LoginEvent.EmailChanged(it)) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.password,
            hint = stringResource(R.string.password),
            isTextVisible = state.isPasswordVisible,
            trailingIcon = if (state.isPasswordVisible) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
            onTextChanged = { onEvent(LoginEvent.PasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(LoginEvent.PasswordVisibilityChanged) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        Text(
            modifier = Modifier.clickable { onEvent(LoginEvent.ForgotPasswordClicked) },
            text = stringResource(R.string.forgot_password),
            style = AppTypography.bodyMedium,
            color = AppColors.primary
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
            onClick = { onEvent(LoginEvent.LoginButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        Text(
            modifier = Modifier.clickable { onEvent(LoginEvent.RegisterHereClicked) },
            text = stringResource(R.string.dont_have_an_account),
            style = AppTypography.bodyMedium,
            color = AppColors.primary
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