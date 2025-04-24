package ge.tbca.city_park.home.presentation.component.car_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.cars.presentation.model.CarUi

@Composable
fun CarItem(
    car: CarUi,
    modifier: Modifier = Modifier,
    onClick: (CarUi) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimen.size8)
            .clip(RoundedCornerShape(Dimen.roundedCornerMediumSize))
            .background(AppColors.surface)
            .clickable { onClick(car) }
    ) {
        Column(modifier = Modifier.padding(Dimen.appPadding)) {
            car.carName?.let {
                Text(
                    text = it,
                    style = TextStyles.bodyLarge,
                    color = AppColors.primary
                )
            }

            Spacer(modifier = Modifier.height(Dimen.sizeSmall))

            Text(
                text = car.plateNumber,
                style = if (car.carName != null) TextStyles.bodyLarge else TextStyles.titleLarge,
                color = AppColors.primary
            )
        }
    }
}

@AppPreview
@Composable
fun CarItemPreview() {
    AppTheme {
        Column {
            CarItem(
                car = CarUi(
                    id = 1,
                    carName = "Tesla",
                    plateNumber = "AA123BB"
                ),
                onClick = {}
            )
        }
    }
}

@AppPreview
@Composable
fun CarItemPreviewWithoutName() {
    AppTheme {
        Column {
            CarItem(
                car = CarUi(
                    id = 1,
                    plateNumber = "AA123BB"
                ),
                onClick = {}
            )
        }
    }
}