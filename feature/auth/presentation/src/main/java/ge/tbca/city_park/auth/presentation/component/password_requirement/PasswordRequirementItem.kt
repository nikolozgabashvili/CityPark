package ge.tbca.city_park.auth.presentation.component.password_requirement

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.CheckMarkIcon
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.ErrorIcon
import com.example.core.designsystem.theme.TextStyles

@Composable
fun PasswordRequirementItem(
    modifier: Modifier = Modifier,
    text: String,
    isValid: Boolean
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier.align(Alignment.CenterVertically),
            imageVector = if (isValid) CheckMarkIcon else ErrorIcon,
            contentDescription = null
        )
        Spacer(Modifier.width(Dimen.size12))
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            style = TextStyles.labelSmall,
            color = AppColors.secondary,
            text = text
        )
    }
}