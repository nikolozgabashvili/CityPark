package ge.tbca.city_park.presentation.ui.design_system.components.password_requirement

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.model.PasswordValidationState
import ge.tbca.city_park.presentation.ui.design_system.util.AppPreview
import ge.tbca.city_park.presentation.ui.theme.AppColors
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.AppTypography
import ge.tbca.city_park.presentation.ui.theme.Dimen

@Composable
fun PasswordRequirementItem(
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
                Row {
                    Image(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        painter = painterResource(if (state.hasMinLength) R.drawable.ic_check else R.drawable.ic_error),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(Dimen.size12))
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = AppTypography.labelSmall,
                        color = AppColors.secondary,
                        text = stringResource(R.string.min_8_symbols)
                    )
                }

                Row {
                    Image(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        painter = painterResource(if (state.hasUpperCase) R.drawable.ic_check else R.drawable.ic_error),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(Dimen.size12))
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = AppTypography.labelSmall,
                        color = AppColors.secondary,
                        text = stringResource(R.string.uppercase_latin_letters)
                    )
                }

                Row {
                    Image(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        painter = painterResource(if (state.hasLowerCase) R.drawable.ic_check else R.drawable.ic_error),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(Dimen.size12))
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = AppTypography.labelSmall,
                        color = AppColors.secondary,
                        text = stringResource(R.string.lowercase_latin_letters)
                    )
                }

                Row {
                    Image(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        painter = painterResource(if (state.hasDigit) R.drawable.ic_check else R.drawable.ic_error),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(Dimen.size12))
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = AppTypography.labelSmall,
                        color = AppColors.secondary,
                        text = stringResource(R.string.numbers_0_to_9)
                    )
                }

                Row {
                    Image(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        painter = painterResource(if (state.hasSpecialChar) R.drawable.ic_check else R.drawable.ic_error),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(Dimen.size12))
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = AppTypography.labelSmall,
                        color = AppColors.secondary,
                        text = stringResource(R.string.special_symbols)
                    )
                }
            }
        }
    }
}

@Composable
@AppPreview
private fun PasswordRequirementItemPreview() {
    AppTheme {
        PasswordRequirementItem(
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