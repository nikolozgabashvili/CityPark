package com.example.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SignalWifiConnectedNoInternet4
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.designsystem.components.image.IconWithBackground
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.core.designsystem.R

@Composable
fun NoNetworkScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimen.size20),
            shape = RoundedCornerShape(Dimen.roundedCornerMediumSize),
            shadowElevation = Dimen.sizeSmall,
        ) {
            Column(
                modifier = Modifier.padding(vertical = Dimen.size20),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                IconWithBackground(icon = Icons.Rounded.SignalWifiConnectedNoInternet4)
                Spacer(modifier = Modifier.height(Dimen.size20))
                Text(text = stringResource(R.string.no_network_connection))
            }

        }

    }


}


@AppPreview
@Composable
private fun NoNetworkScreenPrev() {
    AppTheme {
        NoNetworkScreen()
    }
}