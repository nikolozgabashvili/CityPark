package ge.tbca.city_park.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.core.designsystem.components.button.text_button.NegativeButton
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.home.presentation.R
import ge.tbca.city_park.home.presentation.model.ReservationUi

@Composable
fun ActiveReservationCard(
    onFinishRequest: () -> Unit,
    modifier: Modifier = Modifier,
    enabled:Boolean = true,
    reservation: ReservationUi,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimen.roundedCornerMediumSize),
    ) {
        Column(
            modifier = Modifier.padding(Dimen.size12),
            verticalArrangement = Arrangement.spacedBy(Dimen.size8)
        ) {
            Text(
                text = stringResource(R.string.ongoing_parking),
                style = TextStyles.titleMedium,
                color = AppColors.primary,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.car_with_number, reservation.carNumber),
                style = TextStyles.bodyLarge,
                color = AppColors.primary
            )

            Text(
                text = stringResource(R.string.zone_with_code, reservation.zoneCode),
                style = TextStyles.bodyLarge,
                color = AppColors.primary
            )

            Text(
                text = stringResource(R.string.start_time, reservation.startTime),
                style = TextStyles.bodyLarge,
                color = AppColors.primary
            )


            NegativeButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.finish),
                enabled = enabled,
                endIcon = Icons.Rounded.Close,
                onClick = onFinishRequest,
            )


        }

    }

}

@AppPreview
@Composable
private fun ActiveReservationCardPrev() {
    AppTheme {
        ActiveReservationCard(
            reservation = ReservationUi(
                id = 1,
                carNumber = "ABC123",
                zoneCode = "XYZ",
                startTime = "2025-10-01 12:00",
            ),
            onFinishRequest = {}
        )
    }
}