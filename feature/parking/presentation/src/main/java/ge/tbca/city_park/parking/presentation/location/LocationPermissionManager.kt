package ge.tbca.city_park.parking.presentation.location

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.core.designsystem.components.dialog.BaseAlertDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ge.tbca.city_park.parking.presentation.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionManager(
    onDismissRequest: () -> Unit,
    onPermissionChanged: (Boolean) -> Unit,
    showPermissionDialog: Boolean
) {

    val locationPermission =
        listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )


    val context = LocalContext.current
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }

    val locationPermissionState =
        rememberMultiplePermissionsState(locationPermission)

    val allPermissionsRevoked =
        locationPermissionState.permissions.size ==
                locationPermissionState.revokedPermissions.size
    LaunchedEffect(allPermissionsRevoked) {
        onPermissionChanged(!allPermissionsRevoked)
    }

    if (allPermissionsRevoked) {
        if (!locationPermissionState.shouldShowRationale) {
            LaunchedEffect(Unit) {
                locationPermissionState.launchMultiplePermissionRequest()
            }
        } else {
            if (showPermissionDialog)
                BaseAlertDialog(
                    title = stringResource(R.string.location_permission_required),
                    message = stringResource(R.string.location_permission_required_message),
                    positiveButtonText = stringResource(R.string.allow_location_permission),
                    negativeButtonText = stringResource(R.string.cancel),
                    onDismiss = onDismissRequest,
                    setDismissible = true,
                    onNegativeButtonClick = onDismissRequest,
                    onPositiveButtonClick = {
                        context.startActivity(intent)
                    }
                )
        }
    }
}