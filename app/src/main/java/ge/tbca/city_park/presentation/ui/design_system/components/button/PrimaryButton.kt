package ge.tbca.city_park.presentation.ui.design_system.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import ge.tbca.city_park.presentation.ui.theme.PrimaryButtonColor
import ge.tbca.city_park.presentation.ui.theme.Dimen
import ge.tbca.city_park.presentation.ui.util.AppPreview

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: String
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryButtonColor
        ),
        modifier = modifier
            .padding(start = Dimen.paddingStart, end = Dimen.paddingEnd)
            .height(Dimen.primaryButtonHeight)
            .fillMaxWidth()
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
@AppPreview
fun PrimaryButtonPreview() {
    PrimaryButton(
        onClick = {},
        text = "Login",
        enabled = true
    )
}