package ge.tbca.city_park.parking.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.parking.presentation.icon.MapMarkerBackground

@Composable
fun MapMarker(
    modifier: Modifier = Modifier,
    markerSize: Dp = Dimen.size75,
    contentSize: Dp = Dimen.size40,
    padding: Dp = Dimen.size16,
    icon: @Composable () -> Unit
) {
    Box {
        Icon(
            imageVector = MapMarkerBackground,
            contentDescription = "",
            modifier = modifier
                .size(markerSize),
            tint = Color.Unspecified
        )
        Box(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(top = padding)
                .size(contentSize),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
    }
}

@Composable
@AppPreview
private fun MapMarkerPrev() {
    AppTheme {
        MapMarker(
            icon = {
                Text(text = "10", color = AppColors.primary)
            }
        )
    }
}