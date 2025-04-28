package ge.tbca.city_park.home.presentation.notification

import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.core.designsystem.components.dialog.BaseAlertDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import ge.tbca.city_park.home.presentation.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationPermissionManager() {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

    val notificationPermission = android.Manifest.permission.POST_NOTIFICATIONS

    val context = LocalContext.current
    val intent = Intent().apply {
        action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
    }

    val notificationPermissionState =
        rememberPermissionState(notificationPermission)

    if (!notificationPermissionState.status.isGranted) {
        if (!notificationPermissionState.status.shouldShowRationale) {
            LaunchedEffect(Unit) {
                notificationPermissionState.launchPermissionRequest()
            }
        } else {
            BaseAlertDialog(
                title = stringResource(R.string.notification_permission_required),
                message = stringResource(R.string.notification_permission_required_message),
                positiveButtonText = stringResource(R.string.settings),
                onDismiss = {},
                setDismissible = false,
                onPositiveButtonClick = {
                    context.startActivity(intent)
                }
            )
        }
    }
}