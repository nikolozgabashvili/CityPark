package ge.tbca.city_park.presentation.ui.design_system.components.text_input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ge.tbca.city_park.R
import ge.tbca.city_park.presentation.ui.theme.Dimen
import ge.tbca.city_park.presentation.ui.theme.PrimaryTextColor
import ge.tbca.city_park.presentation.ui.theme.SecondaryTextColor
import ge.tbca.city_park.presentation.ui.util.AppPreview

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    onTextChanged: (String) -> Unit,
    isTextVisible: Boolean = true,
    trailingIcon: Int? = null,
    onToggleTextVisibility: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimen.authTextFieldHeight)
            .padding(start = Dimen.paddingStart, end = Dimen.paddingEnd)
    ) {
        OutlinedTextField(
            value = text,
            placeholder = {
                Text(
                    text = hint,
                    color = SecondaryTextColor,
                    fontSize = 14.sp
                )
            },
            textStyle = TextStyle(color = PrimaryTextColor, fontSize = 14.sp),
            onValueChange = { onTextChanged(it) },
            visualTransformation = if (isTextVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            trailingIcon = {
                if (trailingIcon != null && onToggleTextVisibility != null) {
                    IconButton(
                        onClick = onToggleTextVisibility
                    ) {
                        Icon(
                            painter = painterResource(id = trailingIcon),
                            contentDescription = null
                        )
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryTextColor,
                unfocusedBorderColor = SecondaryTextColor
            ),
            modifier = modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@AppPreview
@Composable
fun AuthTextFieldPreview() {
    AuthTextField(
        text = "",
        hint = "Enter Email or phone number",
        onTextChanged = {},
        trailingIcon = R.drawable.ic_launcher_foreground,
        onToggleTextVisibility = {}
    )
}