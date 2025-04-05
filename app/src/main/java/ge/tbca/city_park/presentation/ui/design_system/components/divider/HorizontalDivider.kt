package ge.tbca.city_park.presentation.ui.design_system.components.divider

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ge.tbca.city_park.presentation.ui.theme.Dimen
import ge.tbca.city_park.presentation.ui.theme.PrimaryTextColor
import ge.tbca.city_park.presentation.ui.theme.SecondaryIconColor
import ge.tbca.city_park.presentation.ui.theme.White
import ge.tbca.city_park.presentation.ui.util.AppPreview

@Composable
fun Divider(
    modifier: Modifier = Modifier,
    text: String? = null,
    textColor: Color = White,
    dividerColor: Color = SecondaryIconColor,
) {
    Row(
        modifier = modifier
            .padding(start= Dimen.paddingStart, end = Dimen.paddingEnd)
            .fillMaxWidth()
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            color = dividerColor
        )
        text?.let {
            Text(
                modifier = Modifier
                    .padding(start = Dimen.paddingStart, end = Dimen.paddingEnd),
                text = it,
                color = textColor
            )

            HorizontalDivider(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                color = dividerColor
            )
        }
    }
}

@Composable
@AppPreview
fun DividerPreview() {
    Divider(
        text = "Or",
        textColor = PrimaryTextColor,
        dividerColor = SecondaryIconColor
    )
}