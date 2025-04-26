package ge.tbca.city_park.payment.presentation.component.credit_card_dropdown

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.designsystem.components.divider.Divider
import com.example.core.designsystem.components.dropdown.BaseDropDownMenu
import com.example.core.designsystem.theme.AppColors
import com.example.core.designsystem.theme.AppTheme
import com.example.core.designsystem.theme.Dimen
import com.example.core.designsystem.util.AppPreview
import ge.tbca.city_park.payment.domain.model.CardType
import ge.tbca.city_park.payment.presentation.R
import ge.tbca.city_park.payment.presentation.mapper.getIcon
import ge.tbca.city_park.payment.presentation.model.CreditCardUi

@Composable
fun CardDropDownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    items: List<CreditCardUi>,
    onDismiss: () -> Unit,
    onCardClick: (Int) -> Unit,
    onAdditionalItemClick: () -> Unit
) {
    BaseDropDownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismiss = onDismiss
    ) {
        items.forEachIndexed { index, item ->
            CreditCardDropDownItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimen.size20),
                cardUi = item,
                onClick = onCardClick
            )

            if (index != items.lastIndex) {
                Divider(modifier = Modifier.padding(vertical = Dimen.size8))
            }
        }

        Divider(text = stringResource(R.string.or))

        Row(
            modifier = Modifier
                .padding(top = Dimen.size8)
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = onAdditionalItemClick
                )
                .padding(horizontal = Dimen.size20)
                .align(Alignment.CenterHorizontally)
            ,
            verticalAlignment = Alignment.CenterVertically


        ) {
            Icon(imageVector = Icons.Rounded.AddCard, contentDescription = null)
            Spacer(modifier = Modifier.width(Dimen.size12))
            Text(text = stringResource(R.string.add_card))

        }


    }

}

@Composable
private fun CreditCardDropDownItem(
    modifier: Modifier = Modifier,
    cardUi: CreditCardUi,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = modifier.clickable(
            indication = null,
            interactionSource = null,
            onClick = { onClick(cardUi.id) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = stringResource(R.string.hidden_card_number,cardUi.cardNumber.takeLast(4)),
                color = AppColors.onBackground)
            Spacer(modifier = Modifier.height(Dimen.size12))
            Text(text = cardUi.holderName, color = AppColors.onBackground)
        }
        Spacer(modifier = Modifier
            .width(Dimen.size32)
            .weight(1f))
        Image(
            modifier = Modifier.width(Dimen.size40),
            imageVector = cardUi.cardType.getIcon(),
            contentDescription = null
        )
    }
}


@AppPreview
@Composable
private fun CreditCardDropdownItemPrev() {
    AppTheme {
        CreditCardDropDownItem(
            cardUi = CreditCardUi(
                id = 13,
                cardNumber = "1234 5678 9012 3456",
                holderName = "John Doe",
                balance = 100.0,
                expirationDate = "1122",
                cvv = "123",
                cardType = CardType.MASTERCARD
            ),
            onClick = {}
        )
    }
}

@AppPreview
@Composable
private fun CardDroDownMenuPrev() {
    AppTheme {
        CardDropDownMenu(
            modifier = Modifier,
            expanded = true,
            items = listOf(
                CreditCardUi(
                    id = 13,
                    cardNumber = "1234 5678 9012 3456",
                    holderName = "John Doe",
                    balance = 100.0,
                    expirationDate = "1122",
                    cvv = "123",
                    cardType = CardType.MASTERCARD
                ),

                CreditCardUi(
                    id = 13,
                    cardNumber = "1234 5678 9012 3456",
                    holderName = "John Doe",
                    balance = 100.0,
                    expirationDate = "1122",
                    cvv = "123",
                    cardType = CardType.MASTERCARD
                )

            ),
            onDismiss = {},
            onCardClick = {},
            onAdditionalItemClick = {}
        )
    }
}