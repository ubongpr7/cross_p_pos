package org.akwapos.app.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import org.akwapos.app.platform.PlatformOrientation
import org.akwapos.app.theme.*

@Composable
fun ProductsScreen(modifier: Modifier = Modifier) {
    val screenModel = viewModel { ProductsScreenModel() }
    val platformOrientation = rememberPlatformOrientation()
    val (platformWidth, _) = remember(platformOrientation) {
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
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Products", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
            Button(onClick = {}) {
                Icon(TablerIcons.Plus, "add icon")
                Text("Product", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
            }
        }
        Column(
            Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(5))
                .border(PixelDensity.setValue(1), MaterialTheme.colorScheme.onBackground, RoundedCornerShape(5))
                .padding(PixelDensity.large)
        ) {
            when (platformOrientation) {
                is PlatformOrientation.Portrait -> {
                    Column(Modifier.jVerticalScroll().fillMaxSize()) {
                        DisplayProductFilterMobile(screenModel = screenModel)
                        DisplayProductsMobile(
                            modifier = Modifier.fillMaxWidth()
                                .padding(vertical = PixelDensity.medium)
                        )
                    }
                }

                else -> {
                    DisplayProductFilter(screenModel)
                    DisplayProducts(
                        modifier = Modifier.fillMaxWidth()
                            .padding(vertical = PixelDensity.medium),
                        displayWidth = platformWidth
                    )
                }
            }
        }
    }
}

@Composable
private fun DisplayProductsMobile(modifier: Modifier) {
    Column(modifier) {
        Row(
            Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceContainerLow)
                .padding(vertical = PixelDensity.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalTextIcon(
                leadingIcon = {
                    Icon(
                        TablerIcons.ClipboardList,
                        "close / expand icon"
                    )
                }, text = "Product",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

            )
            Text(
                text = "Actions",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )
        }
        repeat(10) {
            DisplayProductMobile(
                Modifier.padding(vertical = PixelDensity.verySmall)
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            )
        }
        Row(
            Modifier
                .fillMaxWidth().padding(vertical = PixelDensity.medium),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Showing 5 of 5 products",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(PixelDensity.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(TablerIcons.ChevronLeft, "previous")
                }
                Text(
                    text = "1",
                    modifier = Modifier
                        .border(
                            PixelDensity.setValue(1),
                            MaterialTheme.colorScheme.onBackground,
                            RoundedCornerShape(10)
                        )
                        .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(10))
                        .padding(horizontal = PixelDensity.small),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondary,
                    )
                )
                IconButton(onClick = {}) {
                    Icon(TablerIcons.ChevronRight, "previous")
                }
            }
        }
    }
}

@Composable
private fun DisplayProductMobile(modifier: Modifier = Modifier) {
    Column(modifier) {
        val isExpand = remember { mutableStateOf(false) }
        val bgColor = remember {
            listOf(
                Color.Green.copy(alpha = 0.5f),
                Color.Red.copy(alpha = 0.5f),
                Color.Yellow.copy(alpha = 0.5f),
            ).random()
        }
        Row(
            Modifier.fillMaxWidth()
                .padding(vertical = PixelDensity.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalTextIcon(
                leadingIcon = {
                    IconButton(onClick = { isExpand.value = !isExpand.value }) {
                        Icon(
                            if (isExpand.value) TablerIcons.ChevronDown else TablerIcons.ChevronUp,
                            "close / expand icon"
                        )
                    }
                },
                text = "Coca Cola",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            )
            IconButton(onClick = {}) {
                Icon(
                    TablerIcons.Edit,
                    "edit icon",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        HorizontalDivider(color = bgColor)
        if (isExpand.value) {
            Column(
                Modifier.background(bgColor)
            ) {
                Row(
                    Modifier.fillMaxWidth()
                        .padding(PixelDensity.small),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "SKU",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = "Category",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = "Price",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = "Stock",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
                Row(
                    Modifier.fillMaxWidth()
                        .padding(vertical = PixelDensity.small),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "FOOD-001",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = "Food",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = "$8.99",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = "100",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun DisplayProductFilterMobile(screenModel: ProductsScreenModel) {
    FlowRow(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(PixelDensity.medium),
        verticalArrangement = Arrangement.spacedBy(PixelDensity.small),
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = screenModel.searchProducts,
            onValueChange = { screenModel.searchProducts = it },
            singleLine = true,
            placeholder = {
                Text(
                    "Search products by name, SKU, or category...",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = { Icon(TablerIcons.Search, "Search icon") },
            maxLines = 1,
            textStyle = MaterialTheme.typography.labelMedium
        )
        TextButton(
            modifier = Modifier
                .padding(vertical = PixelDensity.verySmall)
                .border(
                    PixelDensity.setValue(1),
                    MaterialTheme.colorScheme.onBackground,
                    RoundedCornerShape(5)
                ),
            onClick = { }) {
            Icon(TablerIcons.Filter, "filter icon")
            Text("Filters", style = MaterialTheme.typography.bodyLarge)
        }
        TextButton(
            modifier = Modifier
                .padding(vertical = PixelDensity.verySmall)
                .border(
                    PixelDensity.setValue(1),
                    MaterialTheme.colorScheme.onBackground,
                    RoundedCornerShape(5)
                ),
            onClick = { }) {
            Icon(TablerIcons.Report, "report icon")
            Text("Report", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun ColumnScope.DisplayProducts(
    modifier: Modifier,
    displayWidth: Int
) {
    LandScapeColumnScroll(modifier) {
        Column(Modifier.jHorizontalScroll()) {
            val width = remember(displayWidth) { displayWidth / 8 }
            Row(
                Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceContainerLow)
                    .padding(vertical = PixelDensity.small),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.width(PixelDensity.setValue(width)),
                    text = "Product",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier.width(PixelDensity.setValue(width)),
                    text = "SKU",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier.width(PixelDensity.setValue(width)),
                    text = "Category",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier.width(PixelDensity.setValue(width)),
                    text = "Price",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier.width(PixelDensity.setValue(width)),
                    text = "Stock",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier.width(PixelDensity.setValue(width)),
                    text = "Status",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    modifier = Modifier.width(PixelDensity.setValue(width)),
                    text = "Actions",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
            }

            repeat(10) {
                val randomStock = remember {
                    listOf(
                        Pair(Color.Green.copy(alpha = 0.5f), "In Stock"),
                        Pair(Color.Red.copy(alpha = 0.5f), "Out of Stock"),
                        Pair(Color.Yellow.copy(alpha = 0.5f), "Low Stock"),
                    ).random()
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Coca cola",
                        maxLines = 1,
                        overflow = TextOverflow.MiddleEllipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "FOOD-001",
                        maxLines = 1,
                        overflow = TextOverflow.MiddleEllipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Food",
                        maxLines = 1,
                        overflow = TextOverflow.MiddleEllipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "$8.99",
                        maxLines = 1,
                        overflow = TextOverflow.MiddleEllipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "100",
                        maxLines = 1,
                        overflow = TextOverflow.MiddleEllipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier
                            .width(PixelDensity.setValue(width))
                            .clip(RoundedCornerShape(20))
                            .background(randomStock.first)
                            .padding(horizontal = PixelDensity.verySmall, vertical = PixelDensity.verySmall),
                        text = randomStock.second,
                        maxLines = 1,
                        overflow = TextOverflow.MiddleEllipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.background,
                            textAlign = TextAlign.Center
                        )
                    )
                    Box(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = {}) {
                            Icon(
                                TablerIcons.Edit,
                                "edit icon",
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
                HorizontalDivider()
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = PixelDensity.large * 2),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Showing 5 of 5 products")
            Row(horizontalArrangement = Arrangement.spacedBy(PixelDensity.small)) {
                Box(
                    modifier = Modifier
                        .border(
                            PixelDensity.setValue(1),
                            MaterialTheme.colorScheme.onBackground,
                            RoundedCornerShape(10)
                        )
                        .clickable {}
                        .padding(PixelDensity.medium),
                    contentAlignment = Alignment.CenterStart // Align content to the left
                ) {
                    Text("Previous", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                }
                Text(
                    text = "1",
                    modifier = Modifier
                        .border(
                            PixelDensity.setValue(1),
                            MaterialTheme.colorScheme.onBackground,
                            RoundedCornerShape(10)
                        )
                        .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(10))
                        .padding(PixelDensity.medium),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondary,
                    )
                )
                Box(
                    modifier = Modifier
                        .border(
                            PixelDensity.setValue(1),
                            MaterialTheme.colorScheme.onBackground,
                            RoundedCornerShape(10)
                        )
                        .clickable {}
                        .padding(PixelDensity.medium),
                    contentAlignment = Alignment.CenterStart // Align content to the left
                ) {
                    Text("Next", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                }
            }
        }
    }
}

@Composable
private fun DisplayProductFilter(screenModel: ProductsScreenModel) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(PixelDensity.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = screenModel.searchProducts,
            onValueChange = { screenModel.searchProducts = it },
            placeholder = {
                Text(
                    "Search products by name, SKU, or category...",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = { Icon(TablerIcons.Search, "Search icon") },
            maxLines = 1,
            textStyle = MaterialTheme.typography.labelMedium
        )
        TextButton(
            modifier = Modifier
                .padding(vertical = PixelDensity.verySmall)
                .border(
                    PixelDensity.setValue(1),
                    MaterialTheme.colorScheme.onBackground,
                    RoundedCornerShape(5)
                ),
            onClick = { }) {
            Icon(TablerIcons.Filter, "filter icon")
            Text("Filters", style = MaterialTheme.typography.bodyLarge)
        }
        TextButton(
            modifier = Modifier
                .padding(vertical = PixelDensity.verySmall)
                .border(
                    PixelDensity.setValue(1),
                    MaterialTheme.colorScheme.onBackground,
                    RoundedCornerShape(5)
                ),
            onClick = { }) {
            Icon(TablerIcons.Report, "report icon")
            Text("Report", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
