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
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.design_system.components.button.ButtonSize
import ge.tbca.city_park.presentation.core.design_system.components.button.PrimaryButton
import ge.tbca.city_park.presentation.core.design_system.components.button.TertiaryButton
import ge.tbca.city_park.presentation.core.design_system.components.divider.Divider
import ge.tbca.city_park.presentation.core.design_system.components.text_field.PasswordTextField
import ge.tbca.city_park.presentation.core.design_system.components.text_field.TextInputField
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.AppTypography
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.theme.TextStyles
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

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
            style = TextStyles.headlineLarge.copy(fontWeight = FontWeight(700))//todo have separate file for font weight
        )

        Spacer(modifier = Modifier.height(Dimen.size8))

        Text(
            text = stringResource(R.string.log_into_the_system),
            color = AppColors.secondary,
            style = TextStyles.bodyMedium
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        // todo google login button
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            onClick = { onEvent(LoginEvent.GoogleLoginButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        Divider(text = stringResource(R.string.or))

        Spacer(modifier = Modifier.height(Dimen.size32))

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            label = stringResource(R.string.email),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
            startIcon = Icons.Rounded.Email,
            onTextChanged = { onEvent(LoginEvent.EmailChanged(it)) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            startIcon = Icons.Rounded.Lock,
            label = stringResource(R.string.password),
            isPasswordVisible = state.isPasswordVisible,
            imeAction = ImeAction.Done,
            onTextChanged = { onEvent(LoginEvent.PasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(LoginEvent.PasswordVisibilityChanged) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        Text(
            modifier = Modifier.clickable(
                onClick = { onEvent(LoginEvent.ForgotPasswordClicked) },
                enabled = !state.isLoading,
                interactionSource = null,
                indication = null
            ),
            text = stringResource(R.string.forgot_password),
            style = AppTypography.bodyMedium,
            color = AppColors.primary
        )
        Spacer(modifier = Modifier.height(Dimen.size16))

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            buttonSize = ButtonSize.MEDIUM,
            text = stringResource(R.string.login),
            onClick = { onEvent(LoginEvent.LoginButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        TertiaryButton(
            modifier = Modifier.fillMaxWidth(),
            buttonSize = ButtonSize.MEDIUM,
            text = stringResource(R.string.dont_have_an_account),
            onClick = {
                onEvent(LoginEvent.RegisterHereClicked)
            }
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