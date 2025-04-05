package ge.tbca.city_park.presentation.features.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                .padding(top = Dimen.size32)
                .fillMaxWidth(),
            text = "",
            onClick = { onEvent(RegisterEvent.GoogleLoginButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size32))

        Divider(text = stringResource(R.string.or))

        Spacer(modifier = Modifier.height(Dimen.size32))

        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.email,
            hint = stringResource(R.string.email),
            onTextChanged = { onEvent(RegisterEvent.EmailChanged(it)) },
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.password,
            hint = stringResource(R.string.password),
            isTextVisible = state.isPasswordVisible,
            trailingIcon = if (state.isPasswordVisible) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
            onTextChanged = { onEvent(RegisterEvent.PasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(RegisterEvent.PasswordVisibilityChanged) }
        )

        // TODO - export to components
        AnimatedVisibility(visible = state.showPasswordValidations) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(Dimen.size8))

                Text(
                    style = AppTypography.labelSmall,
                    color = AppColors.secondary,
                    text = stringResource(R.string.password_criterias)
                )

                Spacer(modifier = Modifier.height(Dimen.size8))

                Column {  }
            }
        }

        Spacer(modifier = Modifier.height(Dimen.size16))

        AuthTextField(
            modifier = Modifier.fillMaxWidth(),
            text = state.repeatPassword,
            hint = stringResource(R.string.repeat_password),
            isTextVisible = state.isRepeatPasswordVisible,
            trailingIcon = if (state.isRepeatPasswordVisible) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
            onTextChanged = { onEvent(RegisterEvent.RepeatPasswordChanged(it)) },
            onToggleTextVisibility = { onEvent(RegisterEvent.RepeatPasswordVisibilityChanged) }
        )

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
                isLoading = false
            ),
            onEvent = {},
            scrollState = rememberScrollState()
        )
    }
}