package ge.tbca.city_park.user.presentation.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.components.button.text_button.SecondaryButton
import com.example.core.designsystem.components.dialog.BaseAlertDialog
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.user.presentation.R
import java.util.Locale

@Composable
fun ProfileScreenRoot(
    onShowSnackBar: (String) -> Unit,
    navigateBack: () -> Unit,
    navigateToChangePassword: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            is ProfileEffect.Error -> {
                val error = effect.error.getString(context)
                onShowSnackBar(error)
            }

            is ProfileEffect.NavigateBack -> navigateBack()

            is ProfileEffect.NavigateToChangePassword -> navigateToChangePassword()
        }
    }

    ProfileScreen(
        state = viewModel.state,
        scrollState = scrollState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ProfileScreen(
    state: ProfileState,
    scrollState: ScrollState,
    onEvent: (ProfileEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState, enabled = !state.isLoading)
            .padding(Dimen.appPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopNavigationBar(
            title = stringResource(R.string.profile),
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            onStartIconClick = { onEvent(ProfileEvent.BackButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        state.userEmail?.let {
            Text(
                text = it,
                style = TextStyles.titleLarge,
                color = AppColors.primary
            )
        }

        Spacer(modifier = Modifier.height(Dimen.size16))

        state.userBalance?.let {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Dimen.roundedCornerMediumSize)
            ) {
                Column(
                    modifier = Modifier.padding(Dimen.appPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        style = TextStyles.labelMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = AppColors.primary,
                        text = stringResource(R.string.balance)
                    )
                    Text(
                        style = TextStyles.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = AppColors.primary,
                        text = stringResource(
                            R.string.balance_formatted_gel,
                            String.format(locale = Locale.US, "%.2f", it)
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        SecondaryButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            loading = state.isLoading,
            buttonSize = ButtonSize.LARGE,
            text = stringResource(R.string.change_password),
            onClick = { onEvent(ProfileEvent.ChangePasswordButtonClicked) }
        )

        Spacer(modifier = Modifier.height(Dimen.size16))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            loading = state.isLoading,
            buttonSize = ButtonSize.LARGE,
            text = stringResource(R.string.sign_out),
            onClick = { onEvent(ProfileEvent.SignOutButtonClicked) }
        )

        if(state.showActiveReservationDialog) {
            BaseAlertDialog(
                onDismiss = { onEvent(ProfileEvent.DismissActiveReservationDialog) },
                onPositiveButtonClick = { onEvent(ProfileEvent.FinishParking) },
                onNegativeButtonClick = { onEvent(ProfileEvent.DismissActiveReservationDialog) },
                positiveButtonText = stringResource(R.string.yes),
                negativeButtonText = stringResource(R.string.no),
                title = stringResource(R.string.sign_out),
                message = stringResource(R.string.sign_out_dialog),
            )
        }
    }
}

@AppPreview
@Composable
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen(
            state = ProfileState(userEmail = "eve.holt@reqres.in", userBalance = 100.0),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun ProfileScreenPreviewLoading() {
    AppTheme {
        ProfileScreen(
            state = ProfileState(
                userEmail = "eve.holt@reqres.in",
                userBalance = 100.0,
                isLoading = true
            ),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}

@AppPreview
@Composable
private fun ProfileScreenPreviewNoState() {
    AppTheme {
        ProfileScreen(
            state = ProfileState(),
            scrollState = rememberScrollState(),
            onEvent = {}
        )
    }
}

