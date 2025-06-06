package com.example.core.designsystem.util

import androidx.annotation.FloatRange
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.withSaveLayer
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints
import androidx.core.graphics.transform
import kotlin.math.tan

fun Modifier.shimmer(
    visible: Boolean,
    config: ShimmerConfig = ShimmerConfig()
): Modifier = composed {
    var progress: Float by remember { mutableFloatStateOf(0f) }
    if (visible) {
        val infiniteTransition = rememberInfiniteTransition(label = "")
        progress = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(
                    durationMillis = config.duration.toInt(),
                    delayMillis = config.delay.toInt(),
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            ),
            label = "",
        ).value
    }
    ShimmerModifier(visible = visible, progress = progress, config = config)
}

internal class ShimmerModifier(
    private val visible: Boolean,
    private val progress: Float,
    private val config: ShimmerConfig
) : DrawModifier, LayoutModifier {
    private val cleanPaint = Paint()
    private val paint = Paint().apply {
        isAntiAlias = true
        style = PaintingStyle.Fill
        blendMode = BlendMode.SrcIn
    }
    private val angleTan = tan(Math.toRadians(config.angle.toDouble())).toFloat()
    private var translateHeight = 0f
    private var translateWidth = 0f
    private val intensity = config.intensity
    private val dropOff = config.dropOff
    private val colors = listOf(
        config.contentColor,
        config.higLightColor,
        config.higLightColor,
        config.contentColor
    )
    private val colorStops: List<Float> = listOf(
        ((1f - intensity - dropOff) / 2f).coerceIn(0f, 1f),
        ((1f - intensity - 0.001f) / 2f).coerceIn(0f, 1f),
        ((1f + intensity + 0.001f) / 2f).coerceIn(0f, 1f),
        ((1f + intensity + dropOff) / 2f).coerceIn(0f, 1f)
    )

    override fun ContentDrawScope.draw() {
        drawIntoCanvas {
            it.withSaveLayer(Rect(0f, 0f, size.width, size.height), paint = cleanPaint) {
                drawContent()
                if (visible) {
                    val (dx, dy) = when (config.direction) {
                        ShimmerDirection.LeftToRight -> Pair(
                            offset(-translateWidth, translateWidth, progress),
                            0f
                        )
                        ShimmerDirection.RightToLeft -> Pair(
                            offset(translateWidth, -translateWidth, progress),
                            0f
                        )

                        ShimmerDirection.TopToBottom -> Pair(
                            0f,
                            offset(-translateHeight, translateHeight, progress)
                        )


                        ShimmerDirection.BottomToTop -> Pair(
                            0f,
                            offset(translateHeight, -translateHeight, progress)
                        )

                    }
                    paint.shader?.transform {
                        reset()
                        postRotate(config.angle, size.width / 2f, size.height / 2f)
                        postTranslate(dx, dy)
                    }
                    it.drawRect(Rect(0f, 0f, size.width, size.height), paint = paint)
                }
            }
        }
    }

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val placeable = measurable.measure(constraints)
        val size = Size(width = placeable.width.toFloat(), height = placeable.height.toFloat())
        updateSize(size)
        return layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }

    private fun updateSize(size: Size) {
        translateWidth = size.width + angleTan * size.height
        translateHeight = size.height + angleTan * size.width
        val toOffset = when (config.direction) {
            ShimmerDirection.RightToLeft, ShimmerDirection.LeftToRight -> Offset(size.width, 0f)
            else -> Offset(0f, size.height)
        }
        paint.shader = LinearGradientShader(
            Offset(0f, 0f),
            toOffset,
            colors,
            colorStops
        )
    }

    private fun offset(start: Float, end: Float, progress: Float): Float {
        return start + (end - start) * progress
    }
}


data class ShimmerConfig(
    val contentColor: Color = Color(0xFFB9B9B9),
    val higLightColor: Color = Color(0xFF6D6D6D),
    @FloatRange(from = 0.0, to = 1.0)
    val dropOff: Float = 0.5f,
    @FloatRange(from = 0.0, to = 1.0)
    val intensity: Float = 0.2f,
    val direction: ShimmerDirection = ShimmerDirection.LeftToRight,
    val angle: Float = 20f,
    val duration: Float = 1000f,
    val delay: Float = 200f
)

enum class ShimmerDirection {
    LeftToRight,
    TopToBottom,
    RightToLeft,
    BottomToTop
}

