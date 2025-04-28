package ge.tbca.city_park.parking.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterItem

@Composable
fun <T : ClusterItem?> CircleClusterContent(cluster: Cluster<T>) {
    val size = when {
        cluster.size > 50 -> "50+"
        cluster.size > 20 -> "20+"
        cluster.size > 10 -> "10+"
        else -> cluster.size.toString()
    }
    Surface(
        modifier = Modifier.size(Dimen.size48),
        color = AppColors.background,
        shape = CircleShape,
        border = BorderStroke(width = Dimen.size1, color = AppColors.primary.copy(alpha = 0.5f))
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = size,
                style = TextStyles.labelMedium,
                color = AppColors.primary
            )
        }
    }
}