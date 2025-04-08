package ge.tbca.city_park.presentation.features.forgot_password.screen

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.design_system.components.button.base.ButtonSize
import ge.tbca.city_park.presentation.core.design_system.components.button.text_button.PrimaryButton
import ge.tbca.city_park.presentation.core.design_system.components.text_field.TextInputField
import ge.tbca.city_park.presentation.core.design_system.components.top_navigation_bar.TopNavigationBar
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTheme
import ge.tbca.city_park.presentation.core.design_system.theme.AppTypography
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen
import ge.tbca.city_park.presentation.core.design_system.util.AppPreview
import ge.tbca.city_park.presentation.core.util.CollectSideEffect

@Composable
fun ForgotPasswordScreenRoot(viewModel: ForgotPasswordViewModel = hiltViewModel()) {

    val scrollState = rememberScrollState()

    CollectSideEffect(flow = viewModel.effect) { effect ->

    }

    ForgotPasswordScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ForgotPasswordScreen(
    state: ForgotPasswordState,
    scrollState: ScrollState,
    onEvent: (ForgotPasswordEvent) -> Unit,
) {

    val emailError = if (state.showEmailError) stringResource(R.string.enter_valid_email) else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.password_recovery),
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            onStartIconClick = { onEvent(ForgotPasswordEvent.BackButtonClicked) },
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        Text(
            text = stringResource(R.string.enter_password_recovery_email),
            color = AppColors.primary,
            style = AppTypography.bodyMedium
        )

        Spacer(modifier = Modifier.height(Dimen.size8))

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email,
            errorText = emailError,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            startIcon = Icons.Rounded.Email,
            label = stringResource(R.string.email),
            onTextChanged = { onEvent(ForgotPasswordEvent.EmailChanged(it)) },
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            buttonSize = ButtonSize.LARGE,
            text = stringResource(R.string.submit),
            onClick = {
                onEvent(ForgotPasswordEvent.SubmitButtonClicked)
            }
        )
    }
}

@Composable
@AppPreview
private fun ForgotPasswordScreenPreview() {
    AppTheme {
        ForgotPasswordScreen(
            state = ForgotPasswordState(
                email = "",
                isLoading = false
            ),
            onEvent = {},
            scrollState = rememberScrollState()
        )
    }
}
