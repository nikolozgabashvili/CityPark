package com.example.core.designsystem.components.empty_data_indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.core.designsystem.components.image.IconWithBackground
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles

@Composable
fun EmptyDataIndicator(
    modifier: Modifier = Modifier,
    icon :ImageVector,
    text:String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                AppColors.surface, shape = RoundedCornerShape(
                    Dimen.roundedCornerMediumSize
                )
            )
            .padding(vertical = Dimen.size8),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimen.size12)
    ) {
        IconWithBackground(
            icon = icon
        )

        Text(
            style = TextStyles.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = AppColors.onBackground,
            text = text
        )

    }
}