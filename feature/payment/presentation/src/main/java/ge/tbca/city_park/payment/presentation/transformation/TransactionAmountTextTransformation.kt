package ge.tbca.city_park.payment.presentation.transformation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

class TransactionAmountTextTransformation(private val endingSymbol: String,private val color:Color) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {


        val transformedText = buildAnnotatedString {
            append(text)
            if (text.text.isNotEmpty()) {
                append(" ")
                withStyle(style = SpanStyle(color = color)) {
                    append(endingSymbol)
                }
            }

        }
        return TransformedText(
            transformedText,
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return offset
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return offset.coerceAtMost(text.length)
                }
            }
        )
    }
}