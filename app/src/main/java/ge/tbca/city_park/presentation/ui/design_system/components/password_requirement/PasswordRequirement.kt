package ge.tbca.city_park.presentation.ui.design_system.components.password_requirement

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
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.model.PasswordValidationState
import ge.tbca.city_park.presentation.ui.design_system.util.AppPreview
import ge.tbca.city_park.presentation.ui.theme.AppColors
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.AppTypography
import ge.tbca.city_park.presentation.ui.theme.Dimen

@Composable
fun PasswordRequirement(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    state: PasswordValidationState
) {
    AnimatedVisibility(visible = isVisible) {
        Column(modifier = modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(Dimen.size8))

            Text(
                style = AppTypography.labelSmall,
                color = AppColors.secondary,
                text = stringResource(R.string.password_criterias)
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