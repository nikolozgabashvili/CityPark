package ge.tba.city_park.reservation.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.designsystem.components.divider.Divider
import com.example.core.designsystem.components.dropdown.BaseDropDownMenu
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tba.city_park.reservation.presentation.R
import ge.tbca.city_park.cars.presentation.model.CarUi

@Composable
fun CarDropDownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    items: List<CarUi>,
    onDismiss: () -> Unit,
    onCardClick: (Int) -> Unit,
    onAdditionalItemClick: () -> Unit
) {
    BaseDropDownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismiss = onDismiss
    ) {
        items.forEachIndexed { index, item ->
            CarDropDownItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimen.size20),
                carUi = item,
                onClick = onCardClick
            )

            if (index != items.lastIndex) {
                Divider(modifier = Modifier.padding(vertical = Dimen.size8))
            } else {
                Divider(text = stringResource(R.string.or))
                Spacer(modifier = Modifier.height(Dimen.size8))
            }
        }

        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = onAdditionalItemClick
                )
                .padding(horizontal = Dimen.size20)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically


        ) {
            Icon(imageVector = Icons.Rounded.DirectionsCar, contentDescription = null)
            Spacer(modifier = Modifier.width(Dimen.size12))
            Text(text = stringResource(R.string.add_car))
        }
    }
}

@AppPreview
@Composable
private fun CarDropDownMenuPreview() {
    AppTheme {
        CarDropDownMenu(
            modifier = Modifier,
            expanded = true,
            items = listOf(
                CarUi(
                    id = 1,
                    carName = "Tesla",
                    plateNumber = "AA123BB"
                ),
                CarUi(
                    id = 2,
                    plateNumber = "AA123BB"
                )
            ),
            onDismiss = {},
            onCardClick = {},
            onAdditionalItemClick = {}
        )
    }
}