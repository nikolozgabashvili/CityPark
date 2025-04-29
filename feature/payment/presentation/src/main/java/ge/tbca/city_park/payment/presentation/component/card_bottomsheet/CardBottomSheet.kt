package ge.tbca.city_park.payment.presentation.component.card_bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.designsystem.components.button.base.ButtonSize
import com.example.core.designsystem.components.button.text_button.TertiaryButton
import com.example.core.designsystem.components.divider.Divider
import com.example.core.designsystem.theme.Dimen
import ge.tbca.city_park.payment.presentation.R
import ge.tbca.city_park.payment.presentation.component.card_item.CardItem
import ge.tbca.city_park.payment.presentation.model.CreditCardUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    loading: Boolean = false,
    cards: List<CreditCardUi>,
    onCardClick: (Int) -> Unit,
    onAddCardClick: () -> Unit,
) {

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimen.size12),
            contentPadding = PaddingValues(horizontal = Dimen.size20)
        ) {
            items(cards, key = { it.id }) { card ->
                CardItem(
                    card = card,
                    enabled = !loading,
                    onClick = { onCardClick(card.id) }
                )
            }

            if (cards.isNotEmpty()) {
                item {
                    Divider(text = stringResource(R.string.or))
                }
            }

            item {
                TertiaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    buttonSize = ButtonSize.LARGE,
                    text = stringResource(R.string.add_card),
                    enabled = !loading,
                    onClick = onAddCardClick
                )
            }

            item {
                Spacer(modifier = Modifier.height(Dimen.size16))
            }
        }
    }

}