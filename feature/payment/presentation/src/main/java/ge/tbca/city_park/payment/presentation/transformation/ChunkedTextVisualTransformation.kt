package ge.tbca.city_park.payment.presentation.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ChunkedTextVisualTransformation(private val chunkSize: Int = 4, private val separator:String = " ") : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.replace(" ", "")
        val formatted = trimmed.chunked(chunkSize).joinToString(separator)

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val spacesBeforeOffset = (offset / chunkSize).coerceAtMost((trimmed.length - 1) / chunkSize)
                return offset + spacesBeforeOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                val chunkIndex = offset / (chunkSize + 1)
                val spacesBeforeOffset = minOf(chunkIndex, (trimmed.length - 1) / chunkSize)


                val calculatedOffset = offset - spacesBeforeOffset
                return minOf(calculatedOffset, trimmed.length)
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}