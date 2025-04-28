package ge.tbca.city_park.auth.presentation.screen.recover_password

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.text_field.TextInputField
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.auth.presentation.R

@Composable
fun RecoverPasswordScreenRoot(
    navigateBack: () -> Unit,
    onShowSnackBar: (String) -> Unit,
    viewModel: RecoveryPasswordViewModel = hiltViewModel(),
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            RecoverPasswordEffect.NavigateBack -> navigateBack()
            RecoverPasswordEffect.Success -> {
                onShowSnackBar(context.getString(R.string.password_recovery_email_sent))
                navigateBack()

            }

            is RecoverPasswordEffect.Error -> {
                val error = effect.error.getString(context)
                onShowSnackBar(error)
            }
        }

    }

    RecoverPasswordScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun RecoverPasswordScreen(
    state: RecoveryPasswordState,
    scrollState: ScrollState,
    onEvent: (RecoveryPasswordEvent) -> Unit,
) {

    val emailError = if (state.showEmailError) stringResource(R.string.enter_valid_email) else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState, enabled = !state.isLoading)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = stringResource(R.string.password_recovery),
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            onStartIconClick = { onEvent(RecoveryPasswordEvent.BackButtonClicked) },
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        Text(
            text = stringResource(R.string.enter_password_recovery_email),
            color = AppColors.secondary,
            style = TextStyles.bodyMedium
        )

        Spacer(modifier = Modifier.height(Dimen.size8))

        TextInputField(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            value = state.email,
            errorText = emailError,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            startIcon = Icons.Rounded.Email,
            label = stringResource(R.string.email),
            onTextChanged = { onEvent(RecoveryPasswordEvent.EmailChanged(it)) },
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            buttonSize = ButtonSize.LARGE,
            text = stringResource(R.string.submit),
            onClick = {
                onEvent(RecoveryPasswordEvent.SubmitButtonClicked)
            }
        )
    }
}

@Composable
@AppPreview
private fun RecoverPasswordScreenPreview() {
    AppTheme {
        RecoverPasswordScreen(
            state = RecoveryPasswordState(
                email = "",
                isLoading = false
            ),
            onEvent = {},
            scrollState = rememberScrollState()
        )
    }
}
