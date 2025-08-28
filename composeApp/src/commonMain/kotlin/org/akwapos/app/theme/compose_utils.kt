package org.akwapos.app.theme

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.akwapos.app.models.ComposeTextModel
import org.akwapos.app.models.SlideAction
import org.akwapos.app.platform.PlatformOrientation
import org.akwapos.app.utils.isBetween
import kotlin.math.abs
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
    vSpacing: Dp = PixelDensity.medium,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    hSpacing: Dp = PixelDensity.large,
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
            modifier = Modifier.padding(horizontal = hSpacing, vertical = vSpacing),
            text = text,
            style = style,
            overflow = overflow,
            maxLines = maxLines,
            minLines = minLines,
        )
        if (trailingIcon != null) trailingIcon()
    }
}


@Composable
fun VerticalTextIcon(
    text: String,
    modifier: Modifier = Modifier,
    vSpacing: Dp = PixelDensity.medium,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    hSpacing: Dp = PixelDensity.large,
    style: TextStyle = TextStyle.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (leadingIcon != null) leadingIcon()
        Text(
            modifier = Modifier.padding(horizontal = hSpacing, vertical = vSpacing),
            text = text,
            style = style,
            overflow = overflow,
            maxLines = maxLines,
            minLines = minLines,
        )
        if (trailingIcon != null) trailingIcon()
    }
}

@Composable
fun rememberPlatformOrientation(): PlatformOrientation {
    val configuration = LocalWindowInfo.current
    val check =
        (configuration.containerSize.width - configuration.containerSize.height).absoluteValue
    return remember(check) {
        when {
            configuration.containerSize.width.isBetween(600, 700) -> {
                PlatformOrientation.Tablet(
                    configuration.containerSize.width,
                    configuration.containerSize.height
                )
            }

            configuration.containerSize.width > configuration.containerSize.height -> {
                PlatformOrientation.LandScape(
                    configuration.containerSize.width,
                    configuration.containerSize.height
                )
            }

            else -> {
                PlatformOrientation.Portrait(
                    configuration.containerSize.width,
                    configuration.containerSize.height
                )
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
fun Modifier.jHorizontalScroll(scrollState : ScrollState = rememberScrollState()): Modifier {
    val coroutineScope = rememberCoroutineScope()
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
fun Modifier.jVerticalScroll(scrollState: ScrollState = rememberScrollState()): Modifier {
    val coroutineScope = rememberCoroutineScope()
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
fun Modifier.jDragScroll(
    horizontalSensitivity: Float = 0.5f, // Lower = less sensitive (default 0.5f)
    verticalSensitivity: Float = 1f     // Normal sensitivity for vertical
): Modifier {
    val coroutineScope = rememberCoroutineScope()
    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberScrollState()
    var accumulatedHorizontalDrag by remember { mutableFloatStateOf(0f) }

    return this
        .verticalScroll(verticalScrollState)
        .horizontalScroll(horizontalScrollState)
        .pointerInput(Unit) {
            detectDragGestures(
                onDrag = { change, dragAmount ->
                    change.consume()

                    // Handle vertical scrolling (normal sensitivity)
                    coroutineScope.launch {
                        verticalScrollState.scrollBy(-dragAmount.y * verticalSensitivity)
                    }

                    // Handle horizontal scrolling with threshold
                    accumulatedHorizontalDrag += dragAmount.x
                    if (abs(accumulatedHorizontalDrag) > 20.dp.toPx()) { // 20dp threshold
                        coroutineScope.launch {
                            horizontalScrollState.scrollBy(-accumulatedHorizontalDrag * horizontalSensitivity)
                            accumulatedHorizontalDrag = 0f // Reset after scroll
                        }
                    }
                },
                onDragEnd = {
                    accumulatedHorizontalDrag = 0f // Reset on gesture end
                }
            )
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayPopup(
    show: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)
) {
    if (show) {
        BasicAlertDialog(onDismissRequest = onDismiss, modifier = modifier, content = content)
    }
}


@Composable
fun LandScapeColumnScroll(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    // Measure the full height of the content
    var contentHeight by remember { mutableStateOf(0) }
    var viewportHeight by remember { mutableStateOf(0) }

    Row(modifier) {
        // Scroll track
        Box(
            modifier = Modifier
                .fillMaxWidth(0.03f)
                .fillMaxHeight()
                .onGloballyPositioned { coordinates ->
                    viewportHeight = coordinates.size.height
                }
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, dragAmount ->
                        coroutineScope.launch {
                            scrollState.scrollBy(dragAmount)
                        }
                    }
                }
        ) {
            // Scroll thumb
            if (contentHeight > 0 && viewportHeight > 0) {
                val scrollableHeight = contentHeight - viewportHeight
                val thumbHeight =
                    (viewportHeight.toFloat() / contentHeight * viewportHeight).coerceAtLeast(20f)
                val thumbOffset =
                    (scrollState.value.toFloat() / scrollableHeight * (viewportHeight - thumbHeight))
                        .coerceIn(0f, viewportHeight - thumbHeight)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(thumbHeight.dp)
                        .offset { IntOffset(0, thumbOffset.toInt()) }
                        .padding(PixelDensity.verySmall)
                        .background(MaterialTheme.colorScheme.onSecondaryContainer)
                )
            }
        }

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .onGloballyPositioned { coordinates ->
                    contentHeight = coordinates.size.height
                },
            content = content
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayDropDown(
    shape: Shape = MaterialTheme.shapes.medium,
    containerColor :Color = MaterialTheme.colorScheme.surface,
    borderStroke: BorderStroke? = null,
    items: List<@Composable () -> Unit>,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.clickable {
        isExpanded = !isExpanded
        onClick()
    }) {
        content()
        DropdownMenu(
            modifier = Modifier.padding(PixelDensity.medium),
            shape = shape,
            containerColor = containerColor,
            border = borderStroke,
            expanded = isExpanded,
            onDismissRequest = { isExpanded = !isExpanded }
        ) {
            for (item in items) {
                item()
            }
        }
    }
}


@Composable
fun InfiniteSlideIcon(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    distance: Dp = 30.dp,
    durationMillis: Int = 2000,
    animateVertical: Boolean = true,
    movementStyle: SlideAction = SlideAction.ContinuousDown
) {
    val infiniteTransition = rememberInfiniteTransition(label = "slideAnim")

    val distancePx = with(LocalDensity.current) { distance.toPx() }

    val offset by infiniteTransition.animateFloat(
        initialValue = -distancePx,
        targetValue = distancePx,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis),
            repeatMode = when (movementStyle) {
                SlideAction.ContinuousDown -> RepeatMode.Restart
                SlideAction.Bounce -> RepeatMode.Reverse
            }        ),
        label = "offsetAnim"
    )

    Icon(
        imageVector = icon,
        contentDescription = null,
        modifier = modifier.graphicsLayer {
            if (animateVertical) {
                translationY = offset
            } else {
                translationX = offset
            }
        }
    )
}