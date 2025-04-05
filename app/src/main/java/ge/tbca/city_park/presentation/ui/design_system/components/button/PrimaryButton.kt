package ge.tbca.city_park.presentation.ui.design_system.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import ge.tbca.city_park.presentation.ui.theme.AppColors
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.Dimen
import ge.tbca.city_park.presentation.ui.theme.PrimaryButtonColor
import ge.tbca.city_park.presentation.ui.util.AppPreview

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
//    buttonSize: ButtonSize = ButtonSize.MEDIUM,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    loading: Boolean = false,
    enabled: Boolean = true
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(

        ),
        modifier = modifier
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
@AppPreview
fun PrimaryButtonPreview() {
    AppTheme {
        Column {
            PrimaryButton(
                onClick = {},
                text = "Login",
                enabled = true
            )
        }
    }
}