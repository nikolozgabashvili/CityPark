package ge.tbca.city_park.parking.presentation.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.theme.additionalColors
import com.example.core.designsystem.util.AppPreview
import com.google.android.gms.maps.model.LatLng
import ge.tbca.city_park.parking.presentation.model.ParkingSpot
import kotlin.math.min

@Composable
fun ParkingMarker(
    modifier: Modifier = Modifier,
    parkingSpots: ParkingSpot,
    backgroundColor: Color = AppColors.background,
    availableColor: Color = AppColors.additionalColors.success,
    occupiedColor: Color = AppColors.error,
    strokeWidth: Float = 9f
) {

    val totalSpots = parkingSpots.parkingSpotAmount
    val availableCount = parkingSpots.availableSpots
    val occupiedCount = totalSpots - availableCount

    MapMarker(
        markerSize = Dimen.size50,
        contentSize = Dimen.size32,
        padding = Dimen.size6
    ) {
        val animatedAvailableProgress = animateFloatAsState(
            targetValue = if (totalSpots > 0) (availableCount / totalSpots.toFloat()) * 360f else 0f,
            label = ""
        ).value

        val animatedOccupiedProgress = animateFloatAsState(
            targetValue = if (totalSpots > 0) (occupiedCount / totalSpots.toFloat()) * 360f else 0f,
            label = ""
        ).value

        Box(
            modifier = modifier.size(Dimen.size48),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height
                val radius = (min(width, height) / 2f) - (strokeWidth / 2f)

                drawCircle(
                    color = backgroundColor,
                    radius = radius,
                    center = center,
                    style = Stroke(width = strokeWidth)
                )


                drawArc(
                    color = availableColor,
                    startAngle = -90f,
                    sweepAngle = animatedAvailableProgress,
                    useCenter = false,
                    topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                    size = Size(width - strokeWidth, height - strokeWidth),
                    style = Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round
                    )
                )


                drawArc(
                    color = occupiedColor,
                    startAngle = -90f + animatedAvailableProgress,
                    sweepAngle = animatedOccupiedProgress,
                    useCenter = false,
                    topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
                    size = Size(width - strokeWidth, height - strokeWidth),
                    style = Stroke(
                        width = strokeWidth,
                        cap = StrokeCap.Round
                    )
                )
            }

            Text(
                text = availableCount.toString(),
                color = AppColors.primary,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = Dimen.size6),
                maxLines = 1,
                style = TextStyles.labelMedium
            )
        }
    }
}

@Composable
@AppPreview
private fun ChargerMapMarkerPrev() {
    AppTheme {
        ParkingMarker(
            parkingSpots = ParkingSpot(
                id = 1,
                name = "name",
                address = "address",
                location = LatLng(0.0, 0.0),
                country = "country",
                city = "city",
                district = "district",
                parkingSpotAmount = 10,
                availableSpots = 7,
                zoneCode = "zoneCode",
            )
        )
    }
}