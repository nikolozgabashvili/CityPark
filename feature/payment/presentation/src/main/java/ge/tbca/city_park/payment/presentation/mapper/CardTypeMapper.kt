package ge.tbca.city_park.payment.presentation.mapper

import androidx.compose.runtime.Composable
import com.example.core.designsystem.theme.MasterCardIcon
import com.example.core.designsystem.theme.OtherCardIcon
import com.example.core.designsystem.theme.VisaIcon
import ge.tbca.city_park.payment.domain.model.CardType

@Composable
fun CardType.getIcon() = when (this) {
    CardType.VISA -> VisaIcon
    CardType.MASTERCARD -> MasterCardIcon
    CardType.OTHER -> OtherCardIcon
}