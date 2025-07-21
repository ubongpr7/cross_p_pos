package org.akwapos.app.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import org.akwapos.app.models.ComposeTextModel
import org.akwapos.app.platform.PlatformOrientation
import org.akwapos.app.theme.*
import org.akwapos.app.utils.toPercentage

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    val platformOrientation = rememberPlatformOrientation()
    val (platformWidth, platformHeight) = remember(platformOrientation) {
        when (platformOrientation) {
            is PlatformOrientation.LandScape -> platformOrientation.width to platformOrientation.height
            is PlatformOrientation.Portrait -> platformOrientation.width to platformOrientation.height
            is PlatformOrientation.Tablet -> platformOrientation.width to platformOrientation.height
        }
    }
    Column(
        modifier = Modifier
            .jVerticalScroll()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(PixelDensity.medium)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(PixelDensity.large * 2)
    ) {
        Text("Dashboard", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
        Row(
            modifier = Modifier.jHorizontalScroll(),
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.large * 2)
        ) {
            DisplayRowItem(
                title = "Today's Sales",
                amount = "$1,245.89",
                info = {
                    StyledText(
                        leadingContent = {
                            Icon(TablerIcons.TrendingUp, "graph icon", tint = Color.Green)
                        },
                        texts = listOf(
                            ComposeTextModel(
                                text = "12.5% ",
                                textStyle = MaterialTheme.typography.labelSmall.copy(color = Color.Green)
                            ), ComposeTextModel("vs last week")
                        )
                    )
                },
                trailingIcon = {
                    Icon(
                        TablerIcons.CurrencyDollar,
                        "money icon",
                        modifier = Modifier.background(
                            MaterialTheme.colorScheme.surfaceContainerHigh,
                            shape = CircleShape
                        ).padding(PixelDensity.small).size(PixelDensity.large * 2),
                        tint = Color.Green.copy(alpha = 0.5f)
                    )
                }
            )
            DisplayRowItem(
                title = "Transactions",
                amount = "40",
                info = {
                    StyledText(
                        leadingContent = {
                            Icon(
                                TablerIcons.TrendingUp,
                                "up arrow",
                                tint = Color.Green
                            )
                        },
                        texts = listOf(
                            ComposeTextModel(
                                "8.2% ",
                                textStyle = MaterialTheme.typography.labelSmall.copy(color = Color.Green)
                            ),
                            ComposeTextModel("vs last week")
                        )
                    )
                },
                trailingIcon = {
                    Icon(
                        TablerIcons.ShoppingCart,
                        "shopping chart",
                        modifier = Modifier.background(
                            MaterialTheme.colorScheme.surfaceContainerHigh,
                            shape = CircleShape
                        ).padding(PixelDensity.small).size(PixelDensity.large * 2),
                        tint = Color.Blue.copy(alpha = 0.5f)
                    )
                }
            )
            DisplayRowItem(
                title = "Customers",
                amount = "12",
                info = {
                    StyledText(
                        leadingContent = { Icon(TablerIcons.TrendingDown, "downward arrow", tint = Color.Red) },
                        texts = listOf(
                            ComposeTextModel(
                                "3.1% ",
                                textStyle = MaterialTheme.typography.labelSmall.copy(color = Color.Red)
                            ),
                            ComposeTextModel("vs last week"),
                        )
                    )
                },
                trailingIcon = {
                    Icon(
                        TablerIcons.Users,
                        "people icon",
                        modifier = Modifier.background(
                            MaterialTheme.colorScheme.surfaceContainerHigh,
                            shape = CircleShape
                        ).padding(PixelDensity.small).size(PixelDensity.large * 2),
                        tint = Color.Magenta.copy(alpha = 0.5f)
                    )
                }
            )
            DisplayRowItem(
                title = "Products Sold",
                amount = "124",
                info = {
                    StyledText(
                        leadingContent = { Icon(TablerIcons.TrendingUp, "upwards arrow", tint = Color.Green) },
                        texts = listOf(
                            ComposeTextModel(
                                "24.3% ",
                                textStyle = MaterialTheme.typography.labelSmall.copy(color = Color.Green)
                            ),
                            ComposeTextModel("vs last week"),
                        )
                    )
                },
                trailingIcon = {
                    Icon(
                        TablerIcons.Box, "box container icon",
                        modifier = Modifier.background(
                            MaterialTheme.colorScheme.surfaceContainerHigh,
                            shape = CircleShape
                        ).padding(PixelDensity.small).size(PixelDensity.large * 2),
                        tint = Color.Yellow.copy(alpha = 0.5f)
                    )
                }
            )
        }
        FlowRow(
            modifier = Modifier.padding(PixelDensity.small),
//            maxItemsInEachRow = 2
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.large * 2),
            verticalArrangement = Arrangement.spacedBy(PixelDensity.large * 2)
        ) {
            RecentTransaction(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(5))
                    .border(
                        width = PixelDensity.setValue(1),
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(5)
                    )
                    .padding(PixelDensity.large * 2),
                isPortrait = platformOrientation is PlatformOrientation.Portrait,
                width = platformWidth,
                height = platformHeight
            )
            TopSellingProducts(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(5))
                    .border(
                        width = PixelDensity.setValue(1),
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(5)
                    )
                    .padding(PixelDensity.large * 2),
                isPortrait = platformOrientation is PlatformOrientation.Portrait,
                width = platformWidth,
                height = platformHeight
            )
        }
    }
}

@Composable
private fun TopSellingProducts(modifier: Modifier, isPortrait: Boolean, width: Int, height: Int) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(PixelDensity.medium)
    ) {
        Text(
            "Top Selling Products",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        repeat(5) {
            ProductItem(
                modifier = Modifier
                    .thenIf(
                        condition = isPortrait,
                        ifTrue = { fillMaxWidth() },
                        ifFalse = { width((width.toPercentage(35)).dp) }
                    )
                    .background(MaterialTheme.colorScheme.surfaceContainerLow, RoundedCornerShape(5))
                    .padding(PixelDensity.small)
            )
        }

        TextButton(onClick = {}) {
            Text(
                "View All Products",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Composable
private fun ProductItem(modifier: Modifier) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PixelDensity.small)
    ) {
        Icon(TablerIcons.PictureInPictureOff, "product icon")
        Column {
            Text("Product 1", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
            Text(
                "SKU:PRO-1001",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }
        Spacer(Modifier.weight(1f))
        Column {
            Text("$56.17", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
            Text(
                "30 sold",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

@Composable
private fun RecentTransaction(modifier: Modifier, isPortrait: Boolean, width: Int, height: Int) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(PixelDensity.medium)
    ) {
        Text(
            "Recent Transactions",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        repeat(5) {
            TransactionItem(
                modifier = Modifier
                    .thenIf(
                        condition = isPortrait,
                        ifTrue = { fillMaxWidth() },
                        ifFalse = { width((width.toPercentage(35)).dp) }
                    )
                    .background(MaterialTheme.colorScheme.surfaceContainerLow, RoundedCornerShape(5))
                    .padding(PixelDensity.small)
            )
        }

        TextButton(onClick = {}) {
            Text(
                "View All Transactions",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Composable
private fun TransactionItem(modifier: Modifier) {
    Row(modifier, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text("Order #1001", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
            Text(
                "7/20/2025",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }
        Column {
            Text("$2.50", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
            Text("completed", style = MaterialTheme.typography.bodySmall.copy(color = Color.Green))
        }
    }
}


@Composable
private fun DisplayRowItem(
    title: String,
    amount: String,
    info: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit
) {
    ListItem(
        modifier = Modifier
            .border(
                width = PixelDensity.setValue(1),
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(10 / 2)
            ),
        headlineContent = {
            Text(
                amount,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        },
        supportingContent = info,
        overlineContent = { Text(title) },
        trailingContent = trailingIcon
    )
}