package ge.tbca.city_park.presentation.ui.design_system.components.text_field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ge.tbca.city_park.presentation.ui.theme.AppColors
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.Dimen
import ge.tbca.city_park.presentation.ui.theme.TextStyles
import ge.tbca.city_park.presentation.ui.design_system.util.AppPreview

@Composable
fun TextInputField(
    value: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
    endIcon: ImageVector? = null,
    errorText: String? = null,
    startIcon: ImageVector? = null,
    imeAction: ImeAction = ImeAction.None,
    keyboardType: KeyboardType = KeyboardType.Unspecified
) {
    val colors = TextFieldDefaults.colors(
        unfocusedContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        errorTrailingIconColor = AppColors.onSurfaceVariant
    )

    OutlinedTextField(
        modifier = modifier,
        value = value,
        enabled = enabled,
        colors = colors,
        label = label?.let {
            {
                Text(
                    text = it,
                    style = TextStyles.bodySmall
                )
            }
        },
        textStyle = TextStyles.bodyLarge,
        onValueChange = { onTextChanged(it) },
        supportingText = errorText?.let {
            {
                Text(
                    text = it,
                    style = TextStyles.bodySmall,
                    color = AppColors.error
                )
            }
        },
        singleLine = true,
        isError = errorText != null,
        shape = RoundedCornerShape(Dimen.roundedCornerMediumSize),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        leadingIcon = startIcon?.let { { Icon(imageVector = it, contentDescription = null) } },
        trailingIcon = endIcon?.let { { Icon(imageVector = it, contentDescription = null) } },
    )
}


@AppPreview
@Composable
private fun TextInputFieldPreview() {

    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimen.sizeSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextInputField(
                value = "value",
                label = "Label",
                onTextChanged = {}
            )

            TextInputField(
                value = "value",
                label = "Label",
                startIcon = Icons.Default.Email,
                enabled = false,
                onTextChanged = {}
            )

            TextInputField(
                value = "Test",
                label = "Label",
                errorText = "error",
                endIcon = Icons.Default.Lock,
                startIcon = Icons.Default.Lock,
                onTextChanged = {}
            )
        }
    }
}

