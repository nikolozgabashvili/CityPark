package ge.tbca.city_park.reservation.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.theme.additionalColors
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.reservation.presentation.R
import ge.tbca.city_park.reservation.presentation.model.ReservationUi
import java.util.Locale

@Composable
fun ReservationItem(
    modifier: Modifier = Modifier,
    reservation: ReservationUi
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimen.roundedCornerMediumSize))
            .background(AppColors.surface)
    ) {
        Column(
            modifier = Modifier.padding(Dimen.size16),
            verticalArrangement = Arrangement.spacedBy(Dimen.size8)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.car, reservation.carNumber),
                    style = TextStyles.bodyMedium,
                    color = AppColors.primary
                )

                Text(
                    text = if (reservation.active) stringResource(R.string.active) else stringResource(
                        R.string.ended
                    ),
                    style = TextStyles.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (reservation.active) AppColors.additionalColors.success else AppColors.primary
                )
            }

            Text(
                text = stringResource(R.string.zone, reservation.zoneCode),
                style = TextStyles.bodyMedium,
                color = AppColors.primary
            )

            Text(
                text = stringResource(R.string.start_time, reservation.createdAt),
                style = TextStyles.bodyMedium,
                color = AppColors.primary
            )

            reservation.endedAt?.let {
                Text(
                    text = stringResource(R.string.end_time, it),
                    style = TextStyles.bodyMedium,
                    color = AppColors.primary
                )
            }

            reservation.cost?.let {
                if (!reservation.active)
                    Text(
                        text = stringResource(R.string.cost, String.format(Locale.US, "%.2f", it)),
                        style = TextStyles.bodyMedium,
                        color = AppColors.primary
                    )
            }
        }
    }
}

@AppPreview
@Composable
fun ReservationItemPreviewActive() {
    AppTheme {
        ReservationItem(
            reservation = ReservationUi(
                id = 1,
                parkingSpotId = 1,
                zoneCode = "AA123",
                carNumber = "AA001BB",
                createdAt = "13:19, 25.04.25",
                active = true
            )
        )
    }
}

@AppPreview
@Composable
fun ReservationItemPreviewNotActive() {
    AppTheme {
        ReservationItem(
            reservation = ReservationUi(
                id = 2,
                parkingSpotId = 1,
                zoneCode = "AA123",
                carNumber = "AA001BB",
                createdAt = "25.04.25, 13:19",
                active = false,
                endedAt = "14:19, 25.04.25",
                cost = 1000.0
            )
        )
    }
}