@file:OptIn(ExperimentalMaterial3Api::class)

package ge.tbca.city_park.parking.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.core.designsystem.components.action_card.ActionCard
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import com.google.android.gms.maps.model.LatLng
import ge.tbca.city_park.parking.presentation.R
import ge.tbca.city_park.cars.presentation.component.CarsBottomSheet
import ge.tbca.city_park.cars.presentation.component.car_item.CarItem
import ge.tbca.city_park.parking.presentation.model.ParkingSpot
import ge.tbca.city_park.parking.presentation.screen.MapState


@Composable
fun MapBottomSheetContent(
    modifier: Modifier = Modifier,
    state: MapState,
    onStartParking: () -> Unit,
    onShowCarBottomSheet: () -> Unit,
    dismissCarBottomSheet: () -> Unit,
    onCarSelected: (Int) -> Unit,
    onAddCar: () -> Unit,
) {

    state.selectedParkingSpot?.let { parkingSpot ->

        Column(modifier = modifier) {

            ParkingSpotDetailItem(
                modifier = Modifier.fillMaxWidth(),
                parkingSpot = parkingSpot
            )
            Spacer(modifier = Modifier.height(Dimen.size16))
            state.selectedCar?.let { car ->
                Text(
                    text = stringResource(R.string.selected_car),
                    fontWeight = FontWeight.Bold,
                    style = TextStyles.titleMedium
                )
                Spacer(modifier = Modifier.height(Dimen.size8))
                CarItem(
                    car = car,
                    containerColor = AppColors.background,
                    onClick = onShowCarBottomSheet
                )

            } ?: run {
                ActionCard(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.select_car),
                    onclick = onShowCarBottomSheet
                )
            }

            Spacer(modifier = Modifier.height(Dimen.size32))


            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = state.canStartReservation,
                buttonSize = ButtonSize.LARGE,
                text = stringResource(R.string.start_parking),
                onClick = onStartParking
            )

            Spacer(modifier = Modifier.height(Dimen.size8))
        }

        if (state.showCarBottomSheet) {
            CarsBottomSheet(
                onDismissRequest = dismissCarBottomSheet,
                onCarSelected = {
                    onCarSelected(it)
                },
                onAddCar = onAddCar,
                cars = state.cars,
                enabled = !state.carsLoading,
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            )
        }


    }

}

@Composable
fun ParkingSpotDetailItem(
    modifier: Modifier = Modifier,
    parkingSpot: ParkingSpot,
) {
    CompositionLocalProvider(
        LocalTextStyle provides TextStyles.titleMedium,
        LocalContentColor provides AppColors.primary
    ) {

        Column(
            modifier = modifier
                .background(
                    color = AppColors.background,
                    RoundedCornerShape(Dimen.roundedCornerMediumSize)
                )
                .padding(Dimen.size16),
            verticalArrangement = Arrangement.spacedBy(Dimen.size8)
        ) {
            Text(
                text = stringResource(R.string.zone_with_value, parkingSpot.zoneCode),
                fontWeight = FontWeight.Bold,
                style = TextStyles.titleLarge
            )
            Text(
                text = stringResource(R.string.city_with_value, parkingSpot.city),
            )
            Text(
                text = stringResource(R.string.district_with_value, parkingSpot.district),
            )
            Text(
                text = stringResource(R.string.address_with_value, parkingSpot.address),
            )
            Text(
                text = stringResource(R.string.parking_spot_amount, parkingSpot.parkingSpotAmount),
            )
            Text(
                text = stringResource(
                    R.string.available_parking_spot_amount,
                    parkingSpot.availableSpots
                ),
            )

        }
    }

}


@AppPreview
@Composable
private fun MapBottomSheetPreview() {
    AppTheme {

        MapBottomSheetContent(
            state = MapState(
                parkingSpots = listOf(testParkingSpot),
                selectedParkingSpotId = testParkingSpot.id,
            ),
            onStartParking = {},
            onShowCarBottomSheet = {},
            dismissCarBottomSheet = {},
            onCarSelected = {},
            onAddCar = {},
        )

    }
}

@AppPreview
@Composable
private fun ParkingSpotDetailPreview() {
    AppTheme {
        ParkingSpotDetailItem(
            modifier = Modifier.fillMaxWidth(),
            parkingSpot = testParkingSpot
        )

    }
}

private val testParkingSpot = ParkingSpot(
    id = 1,
    city = "თბილისი",
    district = "ვაკე",
    address = "გლდანის ქუჩა 1",
    location = LatLng(41.7151, 44.8271),
    availableSpots = 5,
    parkingSpotAmount = 10,
    zoneCode = "AA123",
    name = "გლდანის ბაღი",
    country = "საქართველო",
)