package ge.tba.city_park.reservation.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.cars.presentation.model.CarUi

@Composable
fun CarDropDownItem(
    modifier: Modifier = Modifier,
    carUi: CarUi,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .padding(vertical = Dimen.sizeSmall)
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = null,
                onClick = { onClick(carUi.id) }),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        carUi.carName?.let {
            Text(
                text = it,
                style = TextStyles.bodyLarge,
                color = AppColors.onBackground
            )
            Spacer(modifier = Modifier.height(Dimen.size8))
        }

        Text(
            text = carUi.plateNumber,
            style = TextStyles.bodyLarge,
            color = AppColors.onBackground
        )
    }
}

@AppPreview
@Composable
private fun CarDropDownItemPreview() {
    AppTheme {
        CarDropDownItem(
            carUi = CarUi(
                id = 1,
                carName = "Tesla",
                plateNumber = "AA123BB"
            ),
            onClick = {}
        )
    }
}

@AppPreview
@Composable
private fun CarDropDownItemPreviewNoName() {
    AppTheme {
        CarDropDownItem(
            carUi = CarUi(
                id = 2,
                plateNumber = "AA123BB"
            ),
            onClick = {}
        )
    }
}