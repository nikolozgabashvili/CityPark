package com.example.core.designsystem.components.dropdown

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen

@Composable
fun BaseDropDownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = modifier.padding(Dimen.size12),
        shape = RoundedCornerShape(Dimen.roundedCornerMediumSize),
        containerColor = AppColors.background,
        border = BorderStroke(width = Dimen.size1, color = AppColors.outline)
    ) {
        content()
    }


}


@Preview
@Composable
private fun BaseDropDownMenuPrev() {
    AppTheme {
        BaseDropDownMenu(
            modifier = Modifier,
            expanded = true,
            onDismiss = {},
            content = {
               Text("hellow")
               Text("hellow")
               Text("hellow")
               Text("hellow")
               Text("hellow")
            }
        )
    }
}