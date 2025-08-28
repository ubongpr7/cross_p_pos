package org.akwapos.app.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import org.akwapos.app.models.product.PosProductModel
import org.akwapos.app.platform.PlatformOrientation
import org.akwapos.app.theme.*

object ProductsScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { ProductsScreenModel() }
        val platformOrientation = rememberPlatformOrientation()
        val (platformWidth, _) = remember(platformOrientation) {
            when (platformOrientation) {
                is PlatformOrientation.LandScape -> platformOrientation.width to platformOrientation.height
                is PlatformOrientation.Portrait -> platformOrientation.width to platformOrientation.height
                is PlatformOrientation.Tablet -> platformOrientation.width to platformOrientation.height
            }
        }
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceContainerLow)
                        .padding(PixelDensity.medium),
//            .then(modifier),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(PixelDensity.large * 2)
                ) {
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

    @Composable
    private fun DisplayProductsMobile(modifier: Modifier) {
        val screenModel = rememberScreenModel { ProductsScreenModel() }
        val products by screenModel.products.collectAsState()
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
            }

            products?.forEach { product ->
                DisplayProductMobile(
                    modifier = Modifier.padding(vertical = PixelDensity.verySmall)
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh),
                    product = product
                )
            } ?: Box(
                Modifier.fillMaxWidth().height(PixelDensity.large * 3)
                    .clip(MaterialTheme.shapes.medium)
                    .createShimmer(listOf(Color.LightGray, Color.White, Color.LightGray))
                    .padding(vertical = PixelDensity.verySmall)
            )

            Row(
                Modifier
                    .fillMaxWidth().padding(vertical = PixelDensity.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
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
    private fun DisplayProductMobile(modifier: Modifier = Modifier, product: PosProductModel) {
        Column(modifier) {
            val isExpand = remember { mutableStateOf(false) }
            Row(
                Modifier.fillMaxWidth()
                    .padding(vertical = PixelDensity.small),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalTextIcon(
                    modifier = Modifier.weight(0.7f),
                    leadingIcon = {
                        IconButton(onClick = { isExpand.value = !isExpand.value }) {
                            Icon(
                                if (isExpand.value) TablerIcons.ChevronDown else TablerIcons.ChevronUp,
                                "close / expand icon"
                            )
                        }
                    },
                    text = product.name ?: "No name",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                )
                VerticalTextIcon(
                    modifier = Modifier.weight(0.3f),
                    text = "In Stock",
                    trailingIcon = {
                        Text(
                            text = "${product.totalStock ?: 0}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                )
            }

            if (isExpand.value) {
                Column {
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
                            text = product.useBarcode ?: "None",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = product.category ?: "None",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = product.basePrice ?: "None",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = product.lowStockThreshold.toString(),
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
                        "Search products by name, SKU, or category",
                        maxLines = 1,
                        overflow = TextOverflow.MiddleEllipsis,
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
        val screenModel = rememberScreenModel { ProductsScreenModel() }
        val products by screenModel.products.collectAsState()
        LandScapeColumnScroll(modifier) {
            Column(Modifier.jHorizontalScroll()) {
                val width = remember(displayWidth) { displayWidth / 8 }
                Row(
                    Modifier.fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceContainerLow)
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
                }

                products?.forEach { product ->
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = product.name ?: "No name",
                            maxLines = 1,
                            overflow = TextOverflow.MiddleEllipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = product.useBarcode ?: "None",
                            maxLines = 1,
                            overflow = TextOverflow.MiddleEllipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = product.category ?: "None",
                            maxLines = 1,
                            overflow = TextOverflow.MiddleEllipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = "$${product.basePrice ?: 0.0}",
                            maxLines = 1,
                            overflow = TextOverflow.MiddleEllipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = "${product.lowStockThreshold?: "0"}",
                            maxLines = 1,
                            overflow = TextOverflow.MiddleEllipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
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
                        Text(
                            "Previous",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
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
                        Text(
                            "Next",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
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
                        "Search products by name, SKU, or category",
                        maxLines = 1,
                        overflow = TextOverflow.MiddleEllipsis,
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
}