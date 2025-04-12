package ge.tbca.city_park.presentation.core.design_system.components.password_requirement

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.core.design_system.theme.AppColors
import ge.tbca.city_park.presentation.core.design_system.theme.AppTypography
import ge.tbca.city_park.presentation.core.design_system.theme.Dimen

@Composable
fun PasswordRequirementItem(
    modifier: Modifier = Modifier,
    text: String,
    isValid: Boolean
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier.align(Alignment.CenterVertically),
            painter = painterResource(if (isValid) R.drawable.ic_check else R.drawable.ic_error),
            contentDescription = null
        )
        Spacer(Modifier.width(Dimen.size12))
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            style = AppTypography.labelSmall,
            color = AppColors.secondary,
            text = text
        )
    }
}