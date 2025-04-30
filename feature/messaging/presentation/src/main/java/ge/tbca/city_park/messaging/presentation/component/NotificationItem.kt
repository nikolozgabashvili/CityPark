package ge.tbca.city_park.messaging.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.messaging.presentation.R
import ge.tbca.city_park.messaging.presentation.model.NotificationUi

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    notification: NotificationUi,
    containerColor: Color = AppColors.surface,
    hasDeleteIcon: Boolean = false,
    onDeleteClick: (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimen.roundedCornerMediumSize),
        color = containerColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimen.size16, end = Dimen.size16),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = null,
                tint = AppColors.primary
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(Dimen.appPadding)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.date_with_created_at, notification.createdAt),
                    style = TextStyles.bodyMedium,
                    color = AppColors.primary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(Dimen.size12))

                Text(
                    text = stringResource(R.string.title_with_title, notification.title),
                    style = TextStyles.bodyMedium,
                    color = AppColors.primary
                )

                Spacer(modifier = Modifier.height(Dimen.size8))

                Text(
                    text = stringResource(R.string.notification_with_message, notification.message),
                    style = TextStyles.bodyMedium,
                    color = AppColors.primary
                )
            }

            if (hasDeleteIcon) {
                Icon(
                    modifier = Modifier
                        .clickable(onClick = { onDeleteClick?.invoke() }, indication = null, interactionSource = null),
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = AppColors.error
                )
            }
        }
    }
}

@AppPreview
@Composable
private fun NotificationItemPreview() {
    AppTheme {
        NotificationItem(
            notification = NotificationUi(
                id = 1,
                userId = 123,
                title = "Parking",
                message = "Started parking on car AA001BB in zone SA002",
                createdAt = "19:22, 28.04.25"
            ),
            hasDeleteIcon = true,
            onDeleteClick = {}
        )
    }
}