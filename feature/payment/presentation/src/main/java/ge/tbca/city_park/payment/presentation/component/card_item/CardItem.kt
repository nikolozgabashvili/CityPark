package ge.tbca.city_park.payment.presentation.component.card_item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.theme.TextStyles
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.payment.domain.model.CardType
import ge.tbca.city_park.payment.presentation.R
import ge.tbca.city_park.payment.presentation.mapper.getIcon
import ge.tbca.city_park.payment.presentation.model.CreditCardUi

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    card: CreditCardUi,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    hasDeleteIcon: Boolean = false,
    onDeleteClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = AppColors.surface,
                shape = RoundedCornerShape(Dimen.roundedCornerMediumSize)
            )
            .border(
                border = BorderStroke(Dimen.size1, AppColors.secondary), shape = RoundedCornerShape(
                    Dimen.roundedCornerMediumSize
                )
            )
            .padding(Dimen.appPadding)
            .clickable(
                enabled = onClick != null && enabled,
                indication = null,
                interactionSource = null,
                onClick = {
                    onClick?.invoke()
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = card.cardType.getIcon(),
            contentDescription = null,
            modifier = Modifier.width(Dimen.size100),
        )

        Spacer(modifier = Modifier.width(Dimen.size16))

        Column(
            verticalArrangement = Arrangement.spacedBy(Dimen.size8)
        ) {
            Text(
                text = stringResource(R.string.hidden_card_number, card.cardNumber.takeLast(4)),
                style = TextStyles.bodyLarge,
                color = AppColors.primary
            )

            Text(
                text = card.expirationDate,
                style = TextStyles.bodyLarge,
                color = AppColors.primary
            )

            Text(
                text = card.holderName,
                style = TextStyles.bodyLarge,
                color = AppColors.primary
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (hasDeleteIcon) {
            Icon(
                modifier = Modifier.clickable(
                    onClick = { onDeleteClick?.invoke() },
                    indication = null,
                    interactionSource = null
                ),
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = AppColors.error
            )
        }
    }
}

@AppPreview
@Composable
private fun CardItemPreviewMasterCard() {
    AppTheme {
        CardItem(
            card = CreditCardUi(
                id = 1,
                cardNumber = "1234 5678 9012 3456",
                expirationDate = "12/25",
                cvv = "123",
                holderName = "John Doe",
                balance = 200.0,
                cardType = CardType.MASTERCARD
            )
        )
    }
}

@AppPreview
@Composable
private fun CardItemPreviewVisa() {
    AppTheme {
        CardItem(
            card = CreditCardUi(
                id = 2,
                cardNumber = "1234 5678 9012 3456",
                expirationDate = "12/25",
                cvv = "123",
                holderName = "John Doe",
                balance = 100.0,
                cardType = CardType.VISA
            )
        )
    }
}

@AppPreview
@Composable
private fun CardItemPreviewOtherWithDeleteIcon() {
    AppTheme {
        CardItem(
            card = CreditCardUi(
                id = 3,
                cardNumber = "1234 5678 9012 3456",
                expirationDate = "12/25",
                cvv = "123",
                holderName = "John Doe",
                balance = 100.0,
                cardType = CardType.OTHER
            ),
            hasDeleteIcon = true
        )
    }
}
