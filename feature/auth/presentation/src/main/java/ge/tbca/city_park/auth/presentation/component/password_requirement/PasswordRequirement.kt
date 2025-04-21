package ge.tbca.city_park.auth.presentation.component.password_requirement

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.auth.domain.model.PasswordValidationState
import ge.tbca.city_park.auth.presentation.R

@Composable
fun PasswordRequirement(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    state: PasswordValidationState
) {
    AnimatedVisibility(
        modifier = modifier.fillMaxWidth(),
        visible = isVisible
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(Dimen.size8))
            Text(
                style = TextStyles.labelSmall,
                color = AppColors.secondary,
                text = stringResource(R.string.password_criteria)
            )

            Spacer(modifier = Modifier.height(Dimen.size8))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Dimen.size6)
            ) {
                PasswordRequirementItem(
                    text = stringResource(R.string.min_8_symbols),
                    isValid = state.hasMinLength
                )

                PasswordRequirementItem(
                    text = stringResource(R.string.uppercase_latin_letters),
                    isValid = state.hasUpperCase
                )

                PasswordRequirementItem(
                    text = stringResource(R.string.lowercase_latin_letters),
                    isValid = state.hasLowerCase
                )

                PasswordRequirementItem(
                    text = stringResource(R.string.numbers_0_to_9),
                    isValid = state.hasDigit
                )

                PasswordRequirementItem(
                    text = stringResource(R.string.special_symbols),
                    isValid = state.hasSpecialChar
                )
            }
        }
    }
}

@Composable
@AppPreview
private fun PasswordRequirementItemPreview() {
    AppTheme {
        PasswordRequirement(
            isVisible = true,
            state = PasswordValidationState(
                hasMinLength = true,
                hasUpperCase = true,
                hasLowerCase = false,
                hasDigit = false,
                hasSpecialChar = false
            )
        )
    }
}