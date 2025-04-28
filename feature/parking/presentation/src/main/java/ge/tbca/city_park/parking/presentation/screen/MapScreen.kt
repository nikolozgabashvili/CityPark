@file:OptIn(MapsComposeExperimentalApi::class, ExperimentalMaterial3Api::class)

package ge.tbca.city_park.parking.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.components.dialog.BaseAlertDialog
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import com.example.core.designsystem.util.isDeviceDarkTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState
import ge.tba.city_park.parking.presentation.R
import ge.tbca.city_park.core.ui.util.CollectSideEffect
import ge.tbca.city_park.parking.presentation.component.CircleClusterContent
import ge.tbca.city_park.parking.presentation.component.MapBottomSheetContent
import ge.tbca.city_park.parking.presentation.component.ParkingMarker
import ge.tbca.city_park.parking.presentation.constant.MapConstants
import ge.tbca.city_park.parking.presentation.location.LocationHelper.rememberLocationState
import ge.tbca.city_park.parking.presentation.location.LocationPermissionManager

@Composable
fun MapScreenRoot(
    onShowSnackBar: (String) -> Unit,
    navigateToAddBalance: () -> Unit,
    navigateToAddCar: () -> Unit,
    viewModel: MapViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val successReservationText = stringResource(R.string.parking_started)


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            MapConstants.TBILISI_LOCATION,
            MapConstants.DEFAULT_ZOOM_LEVEL
        )
    }

    CollectSideEffect(flow = viewModel.effect) { effect ->
        when (effect) {
            is MapEffect.Error -> {
                val error = effect.error.getString(context)
                onShowSnackBar(error)

            }

            is MapEffect.NavigateToAddBalance -> navigateToAddBalance()
            is MapEffect.NavigateToAddCar -> navigateToAddCar()
            is MapEffect.ReservationCreated -> {
                onShowSnackBar(successReservationText)
            }

            is MapEffect.ZoomToLocation -> {
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLngZoom(
                        effect.location,
                        MapConstants.CLUSTER_ZOOM_LEVEL
                    ),
                    durationMs = MapConstants.ZOOM_ANIMATION_DURATION
                )
            }
        }
    }

    LocationPermissionManager(
        onDismissRequest = {viewModel.onEvent(MapEvent.DismissPermissionDialog)},
        showPermissionDialog = viewModel.state.canShowLocationPermissionDialog,
        onPermissionChanged = {
            viewModel.onEvent(MapEvent.OnPermissionChanged(it))
        }
    )

    MapScreen(
        state = viewModel.state,
        bottomSheetState = bottomSheetState,
        cameraPositionState = cameraPositionState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun MapScreen(
    state: MapState,
    bottomSheetState: SheetState,
    cameraPositionState: CameraPositionState,
    onEvent: (MapEvent) -> Unit
) {

    val isLocationEnabled by rememberLocationState()


    LaunchedEffect(state.selectedParkingSpot) {
        state.selectedParkingSpot?.let {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(
                    it.location,
                    MapConstants.MAX_ZOOM_LEVEL
                ),
                durationMs = MapConstants.ZOOM_ANIMATION_DURATION
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GoogleMap(
            uiSettings = MapUiSettings(zoomControlsEnabled = false),

            cameraPositionState = cameraPositionState,
            modifier = Modifier.fillMaxSize(),
            properties = MapProperties(

                isMyLocationEnabled = state.locationPermissionGranted && isLocationEnabled,
                maxZoomPreference = MapConstants.MAX_ZOOM_LEVEL,
                mapStyleOptions = getMapStyleOptions()
            ),
            onMapLoaded = {
                onEvent(MapEvent.MapLoaded)
            }

        ) {

            Clustering(
                items = state.parkingClusters,
                clusterContent = {
                    CircleClusterContent(it)
                },
                clusterItemContent = {
                    ParkingMarker(
                        parkingSpots = it.parkingSpot
                    )
                },
                onClusterItemClick = {
                    onEvent(MapEvent.OnParkingSpotClick(it.parkingSpot.id))
                    true
                },
                onClusterClick = {
                    onEvent(MapEvent.OnClusterClick(it.position))
                    true
                }
            )

        }
    }

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.surface.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = AppColors.primary)

        }
    }

    state.selectedParkingSpot?.let {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = {
                onEvent(MapEvent.DismissBottomSheet)
            }
        ) {

            MapBottomSheetContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimen.size20),
                state = state,
                onStartParking = {
                    onEvent(MapEvent.StartParking)
                },
                onCarSelected = {
                    onEvent(MapEvent.OnCarSelected(it))
                },
                onShowCarBottomSheet = {
                    onEvent(MapEvent.ShowCarBottomSheet)
                },
                onAddCar = {
                    onEvent(MapEvent.NavigateToAddCar)
                },
                dismissCarBottomSheet = {
                    onEvent(MapEvent.HideCarBottomSheet)
                }
            )


        }
    }

    if (state.showInsufficientBalanceDialog) {
        BaseAlertDialog(
            onDismiss = { onEvent(MapEvent.DismissAlertDialog) },
            setDismissible = true,
            onPositiveButtonClick = {
                onEvent(MapEvent.NavigateToAddBalance)
            },
            onNegativeButtonClick = {
                onEvent(MapEvent.DismissAlertDialog)
            },
            positiveButtonText = stringResource(R.string.add_balance),
            negativeButtonText = stringResource(R.string.cancel),
            title = stringResource(ge.tbca.city_park.core.ui.R.string.insufficient_balance),
            message = stringResource(R.string.please_add_balance_to_start_parking)
        )
    }
}

@Composable
private fun getMapStyleOptions(): MapStyleOptions? {
    val context = LocalContext.current
    return if (isDeviceDarkTheme) {
        MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_dark)
    } else null

}

@AppPreview
@Composable
private fun MapScreenPreview() {
    MapScreen(
        state = MapState(),
        onEvent = {},
        bottomSheetState = rememberModalBottomSheetState(),
        cameraPositionState = rememberCameraPositionState(),
    )
}