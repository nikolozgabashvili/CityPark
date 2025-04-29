package ge.tbca.city_park.user.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.PrimaryButton
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.user.presentation.R
import java.util.Locale

@Composable
fun UserBalanceCard(
    modifier: Modifier = Modifier,
    balance: Double,
    enabled: Boolean = true,
    onAddBalanceClick: () -> Unit,
) {

    Surface(
        modifier = modifier
            .border(
                border = BorderStroke(Dimen.size1, AppColors.secondary), shape = RoundedCornerShape(
                    Dimen.roundedCornerMediumSize
                )
            ),
        shape = RoundedCornerShape(Dimen.roundedCornerMediumSize),
        shadowElevation = Dimen.size2
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = Dimen.size12, vertical = Dimen.size20)
        ) {
            Column {
                Text(
                    style = TextStyles.labelLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = AppColors.primary,
                    text = stringResource(R.string.balance)
                )
                Text(
                    style = TextStyles.titleLarge,
                    color = AppColors.primary,
                    fontWeight = FontWeight.ExtraBold,
                    text = stringResource(
                        R.string.balance_formatted_gel,
                        String.format(locale = Locale.US, "%.2f", balance)
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                buttonSize = ButtonSize.MEDIUM,
                endIcon = Icons.Rounded.Add,
                enabled = enabled,
                text = stringResource(R.string.add_balance),
                onClick = onAddBalanceClick,
            )
        }
    }


}


@AppPreview
@Composable
private fun UserBalanceCardPrev() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            UserBalanceCard(
                balance = 100.0,
                onAddBalanceClick = {}
            )
        }
    }
}