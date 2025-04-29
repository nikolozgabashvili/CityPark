package ge.tbca.city_park.fines.presentation.screen.fine_detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core.designsystem.components.top_navigation_bar.TopNavigationBar
import com.example.core.designsystem.theme.Dimen

@Composable
private fun FineDetailScreen(
    state: FineDetailState,
    scrollState: ScrollState,
    onEvent: (FineDetailEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(Dimen.appPadding)
    ) {
        TopNavigationBar(
            title = "ჯარიმის დეტალები",
            startIcon = Icons.AutoMirrored.Rounded.ArrowBack,
            onStartIconClick = { onEvent(FineDetailEvent.BackButtonClicked) },
        )
    }
}