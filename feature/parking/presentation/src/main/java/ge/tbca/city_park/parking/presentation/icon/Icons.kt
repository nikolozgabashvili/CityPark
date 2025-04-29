package ge.tbca.city_park.parking.presentation.icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.core.designsystem.util.isDeviceDarkTheme
import ge.tbca.city_park.parking.presentation.R

val MapMarkerBackground: ImageVector
    @Composable
    get() = ImageVector.vectorResource(if (isDeviceDarkTheme) R.drawable.bg_marker_dark else R.drawable.bg_marker_white)