package ge.tbca.city_park.presentation.ui.design_system.components.text_field

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ge.tbca.city_park.presentation.ui.theme.AppColors
import ge.tbca.city_park.presentation.ui.theme.AppTheme
import ge.tbca.city_park.presentation.ui.theme.Dimen
import ge.tbca.city_park.presentation.ui.theme.TextStyles
import ge.tbca.city_park.presentation.ui.util.AppPreview

@Composable
fun PasswordTextField(
    value: String,
    onTextChanged: (String) -> Unit,
    onToggleTextVisibility: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    isPasswordVisible: Boolean = false,
    errorText: String? = null,
    enabled: Boolean = true,
    startIcon: ImageVector? = null,
    imeAction: ImeAction = ImeAction.None,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
) {

    val visualTransformation =
        if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()

    val endIcon = if (isPasswordVisible) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility
    OutlinedTextField(
        modifier = modifier,
        value = value,
        enabled = enabled,
        colors = TextFieldDefaults.colors(errorTrailingIconColor = AppColors.onSurfaceVariant),
        label = label?.let { { Text(text = it, style = TextStyles.bodySmall) } },
        textStyle = TextStyles.bodyLarge,
        onValueChange = { onTextChanged(it) },
        visualTransformation = visualTransformation,
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
        shape = RoundedCornerShape(Dimen.sizeSmall),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        leadingIcon = startIcon?.let { { Icon(imageVector = it, contentDescription = null) } },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .clickable(
                        enabled = enabled,
                        onClick = onToggleTextVisibility,
                        interactionSource = null,
                        indication = null
                    ),
                imageVector = endIcon, contentDescription = null
            )
        }

    )

}


@AppPreview
@Composable
private fun PasswordTextFieldPreview() {

    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimen.sizeSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PasswordTextField(
                value = "value",
                label = "Label",
                onTextChanged = {},
                onToggleTextVisibility = {}
            )

            PasswordTextField(
                value = "value",
                enabled = false,
                label = "Label",
                onTextChanged = {},
                onToggleTextVisibility = {}
            )

            PasswordTextField(
                value = "value",
                startIcon = Icons.Rounded.Lock,
                label = "Labefffffffl",
                errorText = "asdas",
                onTextChanged = {},
                onToggleTextVisibility = {}
            )
        }
    }
}