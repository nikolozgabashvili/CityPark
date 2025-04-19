package com.example.core.designsystem.components.button.text_button

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.example.core.designsystem.components.button.base.BaseButton
import com.example.core.designsystem.components.button.base.ButtonSize

@Composable
fun BaseTextButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    buttonSize: ButtonSize = ButtonSize.MEDIUM,
    loading: Boolean = false,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    enabled: Boolean = true,
) {

    val buttonParams = buttonSize.getButtonParams()

    BaseButton(
        modifier = modifier,
        onClick = onClick,
        buttonSize = buttonSize,
        loading = loading,
        colors = colors,
        enabled = enabled,
    ){
        Row {
            startIcon?.let { vector ->
                Icon(
                    imageVector = vector,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = buttonParams.inPadding)
                        .size(buttonParams.iconSize)
                )
            }

            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = text,
                modifier = Modifier.align(Alignment.CenterVertically),
                style = buttonParams.textStyle
            )

            endIcon?.let { vector ->
                Icon(
                    imageVector = vector,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = buttonParams.inPadding)
                        .size(buttonParams.iconSize)
                )

            }
        }
    }

}