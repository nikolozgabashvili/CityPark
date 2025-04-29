package ge.tbca.city_park.fines.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles

@Composable
fun FineDetailItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    value: String,
    valueColor: Color = AppColors.primary
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AppColors.primary
        )

        Spacer(modifier = Modifier.width(Dimen.size16))

        Column {
            Text(
                text = label,
                style = TextStyles.bodyMedium,
                color = AppColors.secondary
            )

            Spacer(modifier = Modifier.height(Dimen.sizeSmall))

            Text(
                text = value,
                style = TextStyles.bodyLarge,
                color = valueColor
            )
        }
    }
}