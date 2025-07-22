package org.akwapos.app.theme

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.launch
import org.akwapos.app.models.ComposeTextModel
import org.akwapos.app.platform.PlatformOrientation
import org.akwapos.app.utils.isBetween
import kotlin.math.absoluteValue


@Composable
fun Modifier.drawUnderLine(color: Color, thickness: Dp): Modifier = composed {
    drawBehind {
        val underlineThickness = thickness.toPx() // Thickness of the underline
        // Calculate the start and end positions of the underline
        val startX = 0f
        val endX = size.width
        val baselineY = size.height// + 10
        // Draw the underline
        drawLine(
            color = color, // Color of the underline
            start = Offset(startX, baselineY),
            end = Offset(endX, baselineY),
            strokeWidth = underlineThickness
        )
    }
}

@Composable
fun HorizontalTextIcon(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) leadingIcon()
        Text(
            modifier = Modifier.padding(horizontal = PixelDensity.large, vertical = PixelDensity.medium),
            text = text,
            style = style
        )
        if (trailingIcon != null) trailingIcon()
    }
}

@Composable
fun rememberPlatformOrientation(): PlatformOrientation {
    val configuration = LocalWindowInfo.current
    val check = (configuration.containerSize.width - configuration.containerSize.height).absoluteValue
    return remember(check) {
        when {
            configuration.containerSize.width.isBetween(600, 700) -> {
                PlatformOrientation.Tablet(configuration.containerSize.width, configuration.containerSize.height)
            }

            configuration.containerSize.width > configuration.containerSize.height -> {
                PlatformOrientation.LandScape(configuration.containerSize.width, configuration.containerSize.height)
            }

            else -> {
                PlatformOrientation.Portrait(configuration.containerSize.width, configuration.containerSize.height)
            }
        }
    }
}

inline fun Modifier.thenIf(condition: Boolean, block: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(block())
    } else {
        this
    }
}


inline fun Modifier.thenIf(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier = { this }
): Modifier {
    return if (condition) then(ifTrue()) else then(ifFalse())
}


@Composable
fun ClickableStyledText(
    modifier: Modifier = Modifier,
    texts: List<ComposeTextModel>,
    trialingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
) {
    val annotatedText = buildAnnotatedString {
        for ((index, text) in texts.withIndex()) {
            if (text.onClick != null) {
                withLink(
                    LinkAnnotation.Clickable(
                        "${text.text}_$index",
                        TextLinkStyles(text.textStyle.toSpanStyle()),
                        linkInteractionListener = {
                            text.onClick.invoke()
                        }
                    ),
                ) {
                    append(text.text)
                }
            } else {
                withStyle(text.textStyle.toSpanStyle()) {
                    append(text.text)
                }
            }
        }
    }
    Row {
        leadingContent?.invoke()
        Text(
            modifier = modifier,
            text = annotatedText
        )
        trialingContent?.invoke()
    }
}

@Composable
fun StyledText(
    modifier: Modifier = Modifier,
    texts: List<ComposeTextModel>,
    trialingContent: @Composable (() -> Unit)? = null,
    leadingContent: @Composable (() -> Unit)? = null,
) {
    val annotatedText = buildAnnotatedString {
        for ((index, text) in texts.withIndex()) {
            withStyle(text.textStyle.toSpanStyle()) {
                append(text.text)
            }
        }
    }
    Row {
        leadingContent?.invoke()
        Text(
            modifier = modifier,
            text = annotatedText
        )
        trialingContent?.invoke()
    }
}


@Composable
fun Modifier.jHorizontalScroll(): Modifier {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    return this
        .horizontalScroll(scrollState)
        .draggable(
            orientation = Orientation.Horizontal,
            state = rememberDraggableState { delta ->
                coroutineScope.launch {
                    scrollState.scrollBy(-delta)
                }
            }
        )
}

@Composable
fun Modifier.jVerticalScroll(): Modifier {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    return this
        .verticalScroll(scrollState)
        .draggable(
            orientation = Orientation.Vertical,
            state = rememberDraggableState { delta ->
                coroutineScope.launch {
                    scrollState.scrollBy(-delta)
                }
            }
        )
}

@Composable
fun Modifier.createShimmer(colors: List<Color>, duration: Int = 1000): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition()
    val startOffset by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(duration),
            repeatMode = RepeatMode.Reverse
        )
    )
    background(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(startOffset, 0f),
            end = Offset(startOffset + size.width, size.height.toFloat())
        )
    )
        .onGloballyPositioned { size = it.size }
}
