@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.cars.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.TertiaryButton
import com.example.core.designsystem.components.divider.Divider
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.Dimen
import ge.tbca.city_park.cars.presentation.R
import ge.tbca.city_park.cars.presentation.component.car_item.CarItem
import ge.tbca.city_park.cars.presentation.model.CarUi

@Composable
fun CarsBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onCarSelected: (Int) -> Unit,
    onAddCar: () -> Unit,
    cars: List<CarUi>,
    enabled: Boolean = true,
    sheetState: SheetState,
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimen.size12),
            contentPadding = PaddingValues(horizontal = Dimen.size20)
        ) {
            items(cars, key = { it.id }) { car ->
                CarItem(
                    containerColor = AppColors.background,
                    car = car,
                    onClick = {
                        onCarSelected(car.id)
                    }
                )
            }
            if (cars.isNotEmpty()) {
                item {
                    Divider(
                        text = stringResource(R.string.or)
                    )
                }
            }
            item {
                TertiaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    buttonSize = ButtonSize.LARGE,
                    text = stringResource(R.string.add_cars),
                    enabled = enabled,
                    onClick = onAddCar
                )
            }
            item {
                Spacer(modifier=Modifier.height(Dimen.size16))
            }
        }


    }
}