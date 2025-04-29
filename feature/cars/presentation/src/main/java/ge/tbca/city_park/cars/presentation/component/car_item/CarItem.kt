package ge.tbca.city_park.cars.presentation.component.car_item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.cars.presentation.model.CarUi

@Composable
fun CarItem(
    modifier: Modifier = Modifier,
    car: CarUi,
    enabled: Boolean = true,
    containerColor : Color = AppColors.surface,
    onClick: (() -> Unit)? = null,
    hasDeleteIcon: Boolean = false,
    onDeleteClick: (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(Dimen.size1, AppColors.secondary), shape = RoundedCornerShape(
                    Dimen.roundedCornerMediumSize
                )
            ),
        shape = RoundedCornerShape(Dimen.roundedCornerMediumSize),
        onClick = { onClick?.invoke() },
        color = containerColor,
        enabled = enabled && onClick != null
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier.padding(start = Dimen.appPadding),
                imageVector = Icons.Rounded.DirectionsCar,
                contentDescription = null,
                tint = AppColors.primary
            )

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
            Spacer(modifier = Modifier.weight(1f))

            if(hasDeleteIcon) {
                Icon(
                    modifier = Modifier
                        .padding(end = Dimen.appPadding)
                        .clickable(onClick = { onDeleteClick?.invoke() }),
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = AppColors.error
                )
            }
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
                )
            )
        }
    }
}

@AppPreview
@Composable
fun CarItemPreviewWithDeleteIcon() {
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