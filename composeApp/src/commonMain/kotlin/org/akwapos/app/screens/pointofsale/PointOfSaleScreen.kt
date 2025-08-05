package org.akwapos.app.screens.pointofsale

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import org.akwapos.app.platform.PlatformOrientation
import org.akwapos.app.theme.*


object PointOfSaleScreen : Screen {
    const val screenName = "Point of Sale"

    @Composable
    override fun Content() {
        val platformOrientation = rememberPlatformOrientation()
        val screenModel = rememberScreenModel { PointOfSaleScreenModel() }
//    val (platformWidth, platformHeight) = remember(platformOrientation) {
//        when (platformOrientation) {
//            is PlatformOrientation.LandScape -> platformOrientation.width to platformOrientation.height
//            is PlatformOrientation.Portrait -> platformOrientation.width to platformOrientation.height
//            is PlatformOrientation.Tablet -> platformOrientation.width to platformOrientation.height
//        }
//    }
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .padding(PixelDensity.medium),
//                .then(modifier),
            floatingActionButton = {
                if (platformOrientation is PlatformOrientation.Portrait) {
                    FloatingActionButton(onClick = {
                        screenModel.displayCurrentSaleMobile = !screenModel.displayCurrentSaleMobile
                    }) {
                        Icon(
                            if (screenModel.displayCurrentSaleMobile) TablerIcons.LayoutList else TablerIcons.CashBanknote,
                            "money icon"
                        )
                    }
                }
            }
        ) { pv ->
            DisplayPopup(
                screenModel.showCustomerPopup,
                onDismiss = { screenModel.showCustomerPopup = !screenModel.showCustomerPopup },
                content = {
                    CustomerPopup(
                        modifier = Modifier.background(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(5)
                        ).padding(vertical = PixelDensity.medium),
                        screenModel = screenModel
                    )
                })
            DisplayPopup(
                screenModel.showTablePopup,
                onDismiss = { screenModel.showTablePopup = !screenModel.showTablePopup },
                content = {
                    TablePopup(
                        modifier = Modifier.background(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(5)
                        ).padding(vertical = PixelDensity.medium),
                        screenModel = screenModel
                    )
                })
            when (platformOrientation) {
                is PlatformOrientation.Portrait -> {
                    PortraitDisplay(
                        modifier = Modifier.padding(pv).fillMaxSize(),
                    )
                }

                else -> {
                    LandScapeDisplay(
                        modifier = Modifier.padding(pv).fillMaxSize(),
                    )
                }
            }
        }
    }

    @Composable
    private fun CustomerPopup(modifier: Modifier, screenModel: PointOfSaleScreenModel) {
        Column(
            modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                Modifier.fillMaxWidth()
                    .padding(horizontal = PixelDensity.large),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Select Customer",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                IconButton(onClick = {
                    screenModel.showCustomerPopup = !screenModel.showCustomerPopup
                }) {
                    Icon(TablerIcons.X, "close")
                }
            }
            HorizontalDivider()
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PixelDensity.large, vertical = PixelDensity.small),
                value = screenModel.customer,
                onValueChange = { screenModel.customer = it },
                placeholder = { Text("Search customers...") },
                textStyle = MaterialTheme.typography.bodyMedium
            )
            repeat(5) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = PixelDensity.large, vertical = PixelDensity.verySmall)
                        .border(
                            PixelDensity.setValue(1),
                            MaterialTheme.colorScheme.onBackground,
                            RoundedCornerShape(10)
                        )
                        .clickable {}
                        .padding(PixelDensity.large),
                    contentAlignment = Alignment.CenterStart // Align content to the left
                ) {
                    Text(
                        text = "Mike Johnson",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = PixelDensity.large, vertical = PixelDensity.verySmall)
                    .border(
                        PixelDensity.setValue(1),
                        MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(10)
                    )
                    .clickable {}
                    .padding(PixelDensity.large),
                contentAlignment = Alignment.CenterStart // Align content to the left
            ) {
                Text(
                    text = "Walk-in Customer",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }


    @Composable
    private fun TablePopup(modifier: Modifier, screenModel: PointOfSaleScreenModel) {
        Column(
            modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                Modifier.fillMaxWidth()
                    .padding(horizontal = PixelDensity.large),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Select Table",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                IconButton(onClick = { screenModel.showTablePopup = !screenModel.showTablePopup }) {
                    Icon(TablerIcons.X, "close")
                }
            }
            HorizontalDivider()
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PixelDensity.large, vertical = PixelDensity.small),
                value = screenModel.table,
                onValueChange = { screenModel.table = it },
                placeholder = { Text("Search tabes...") },
                textStyle = MaterialTheme.typography.bodyMedium
            )
            repeat(5) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = PixelDensity.large, vertical = PixelDensity.verySmall)
                        .border(
                            PixelDensity.setValue(1),
                            MaterialTheme.colorScheme.onBackground,
                            RoundedCornerShape(10)
                        )
                        .clickable {}
                        .padding(PixelDensity.large),
                    contentAlignment = Alignment.CenterStart // Align content to the left
                ) {
                    Text(
                        text = "Table $it",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = PixelDensity.large, vertical = PixelDensity.verySmall)
                    .border(
                        PixelDensity.setValue(1),
                        MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(10)
                    )
                    .clickable {}
                    .padding(PixelDensity.large),
                contentAlignment = Alignment.CenterStart // Align content to the left
            ) {
                Text(
                    text = "No Table",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

    @Composable
    private fun PortraitDisplay(modifier: Modifier) {
        val screenModel = rememberScreenModel { PointOfSaleScreenModel() }
        val dragOffset = remember { mutableFloatStateOf(0f) }
        val dragThreshold = 100f
        val isFilterRevealed = remember { mutableStateOf(false) }

        val dragState = rememberDraggableState { delta ->
            dragOffset.floatValue += delta
            if (dragOffset.floatValue > dragThreshold) {
                isFilterRevealed.value = true
            } else if (dragOffset.floatValue < -dragThreshold) {
                isFilterRevealed.value = false
            }
        }
        Box(modifier, contentAlignment = Alignment.TopCenter) {
            if (screenModel.displayCurrentSaleMobile) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .jVerticalScroll()
                        .background(MaterialTheme.colorScheme.background, RoundedCornerShape(5))
                        .border(
                            PixelDensity.setValue(1),
                            MaterialTheme.colorScheme.onBackground,
                            RoundedCornerShape(5)
                        )
                        .padding(PixelDensity.medium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CurrentSale(screenModel)
                    DisplayCart()
                    CheckoutButtons()
                }
            } else {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        Modifier.draggable(
                            orientation = Orientation.Vertical,
                            state = dragState,
                            onDragStopped = {
                                // Reset offset after drag
                                dragOffset.floatValue = 0f
                            }
                        )
                    ) {
                        AnimatedVisibility(visible = isFilterRevealed.value) {
                            FilterBySearch(Modifier.fillMaxWidth(), screenModel)
                        }
                        FilterByProduct(
                            Modifier
                                .jHorizontalScroll(),
                            isFilterRevealed.value
                        )
                    }
                    DisplayProducts(
                        Modifier
                            .jVerticalScroll(),
                    )
                }
            }
        }
    }


    @Composable
    private fun LandScapeDisplay(modifier: Modifier) {
        val screenModel = rememberScreenModel { PointOfSaleScreenModel() }
        Row(modifier, horizontalArrangement = Arrangement.spacedBy(PixelDensity.medium)) {
            Column(
                modifier = Modifier.fillMaxWidth(0.6f).jVerticalScroll(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FilterBySearch(Modifier.fillMaxWidth(), screenModel)
                FilterByProduct(Modifier.jHorizontalScroll(), false)
                DisplayProducts(Modifier)
            }
            Column(
                modifier = Modifier.weight(0.4f)
                    .jVerticalScroll()
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(5))
                    .border(
                        PixelDensity.setValue(1),
                        MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(5)
                    )
                    .padding(PixelDensity.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CurrentSale(screenModel)
                DisplayCart()
                CheckoutButtons()
            }
        }
    }

    @Composable
    private fun ColumnScope.CurrentSale(
        screenModel: PointOfSaleScreenModel
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Current Sale",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            TextButton(
                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error),
                onClick = {}
            ) {
                Text("clear")
            }
        }
        FlowRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(PixelDensity.small),
            itemVerticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalTextIcon(
                modifier = Modifier.clip(RoundedCornerShape(10))
                    .padding(vertical = PixelDensity.small, horizontal = PixelDensity.medium)
                    .background(
                        MaterialTheme.colorScheme.surfaceContainerHigh,
                        RoundedCornerShape(10)
                    ),
                leadingIcon = { Icon(TablerIcons.User, "user icon") },
                text = "Walk-in",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                trailingIcon = {
                    TextButton(onClick = {
                        screenModel.showCustomerPopup = !screenModel.showCustomerPopup
                    }) {
                        Text(
                            modifier = Modifier.padding(start = PixelDensity.small),
                            text = "Change",
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            )
            HorizontalTextIcon(
                modifier = Modifier.clip(RoundedCornerShape(10))
                    .padding(vertical = PixelDensity.small, horizontal = PixelDensity.medium)
                    .background(
                        MaterialTheme.colorScheme.surfaceContainerHigh,
                        RoundedCornerShape(10)
                    ),
                leadingIcon = { Icon(TablerIcons.GripVertical, "table icon") },
                text = "No table",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                trailingIcon = {
                    TextButton(onClick = {
                        screenModel.showTablePopup = !screenModel.showTablePopup
                    }) {
                        Text(
                            modifier = Modifier.padding(start = PixelDensity.small),
                            text = "Change",
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            )
        }
    }

    @Composable
    private fun ColumnScope.DisplayCart() {
        Box(Modifier.fillMaxWidth().height(PixelDensity.setValue(200)), Alignment.Center) {
            Text("Cart is empty")
        }
        HorizontalDivider()
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "Subtotal",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                "$0.00",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "Tax",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                "$0.00",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.verySmall)
        ) {
            Text(
                "Discount",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Icon(TablerIcons.Tag, "tag icon", tint = MaterialTheme.colorScheme.tertiary)
            Spacer(Modifier.weight(1f))
            Text(
                "$0.00",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
        HorizontalDivider()
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "Total",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                "$0.00",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }

    @Composable
    private fun ColumnScope.CheckoutButtons() {
        FlowRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.small),
            verticalArrangement = Arrangement.spacedBy(PixelDensity.small),
            itemVerticalAlignment = Alignment.CenterVertically
        ) {
            Button(modifier = Modifier.weight(1f), onClick = {}) {
                Text("Pay", style = MaterialTheme.typography.bodyMedium)
            }
            Button(modifier = Modifier.weight(1f), enabled = false, onClick = {}) {
                Text("Hold", style = MaterialTheme.typography.bodyMedium)
            }
        }
        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = {}) {
            Text("Split Bill", style = MaterialTheme.typography.bodyMedium)
        }
    }

    @Composable
    private fun FilterByProduct(modifier: Modifier, value: Boolean) {
        Row(
            modifier,
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.small)
        ) {
            if (rememberPlatformOrientation() is PlatformOrientation.Portrait && !value) {
                InfiniteSlideIcon(
                    icon = TablerIcons.ArrowsDownUp
                )
            }
            TextButton(
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {}
            ) {
                Text(
                    text = "All",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            TextButton(
                colors = ButtonDefaults.textButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                onClick = {}
            ) {
                Text(
                    text = "Food",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            TextButton(
                colors = ButtonDefaults.textButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                onClick = {}
            ) {
                Text(
                    text = "Beverages",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            TextButton(
                colors = ButtonDefaults.textButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                onClick = {}
            ) {
                Text(
                    text = "Electronics",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            TextButton(
                colors = ButtonDefaults.textButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                onClick = {}
            ) {
                Text(
                    text = "Clothing",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            TextButton(
                colors = ButtonDefaults.textButtonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                onClick = {}
            ) {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

        }
    }

    @Composable
    private fun FilterBySearch(
        modifier: Modifier,
        screenModel: PointOfSaleScreenModel
    ) {
        FlowRow(
            modifier,
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.small)
        ) {
            OutlinedTextField(
                value = screenModel.searchProduct,
                onValueChange = { screenModel.searchProduct = it },
                placeholder = {
                    Text(
                        "Search products or scan barcode",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                leadingIcon = { Icon(TablerIcons.Search, "Search icon") },
                maxLines = 1,
                textStyle = MaterialTheme.typography.labelMedium
            )
            IconButton(
                modifier = Modifier
                    .border(
                        PixelDensity.setValue(1),
                        MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(5)
                    )
                    .padding(vertical = PixelDensity.verySmall),
                onClick = {}) {
                Icon(TablerIcons.Scan, "scan bar code icon")
            }
            TextButton(
                modifier = Modifier
                    .border(
                        PixelDensity.setValue(1),
                        MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(5)
                    )
                    .padding(vertical = PixelDensity.verySmall),
                onClick = { screenModel.showCustomerPopup = !screenModel.showCustomerPopup }) {
                Icon(TablerIcons.User, "user icon")
                Text("Customer", style = MaterialTheme.typography.bodyLarge)
            }
            TextButton(
                modifier = Modifier
                    .border(
                        PixelDensity.setValue(1),
                        MaterialTheme.colorScheme.onBackground,
                        RoundedCornerShape(5)
                    )
                    .padding(vertical = PixelDensity.verySmall),
                onClick = { screenModel.showTablePopup = !screenModel.showTablePopup }) {
                Icon(TablerIcons.GripVertical, "table icon")
                Text("Table", style = MaterialTheme.typography.labelMedium)
            }

        }
    }

    @Composable
    private fun DisplayProducts(
        modifier: Modifier
    ) {
        FlowRow(
            modifier,
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.medium),
            verticalArrangement = Arrangement.spacedBy(PixelDensity.medium)
        ) {
            repeat(10) {
                DisplayProduct(
                    modifier = Modifier
                        .size(PixelDensity.setValue(150), PixelDensity.setValue(200))
                        .background(MaterialTheme.colorScheme.background, RoundedCornerShape(10))
                        .border(
                            PixelDensity.setValue(1),
                            MaterialTheme.colorScheme.onBackground,
                            RoundedCornerShape(10)
                        )
                        .padding(PixelDensity.medium)
                )
            }
        }
    }

    @Composable
    private fun DisplayProduct(modifier: Modifier) {
        Column(
            modifier,
            verticalArrangement = Arrangement.spacedBy(PixelDensity.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(PixelDensity.setValue(150), PixelDensity.setValue(125))
                    .createShimmer(listOf(Color.LightGray, Color.DarkGray), 3000)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Pizza",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    "custom",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10))
                        .background(MaterialTheme.colorScheme.inversePrimary)
                        .padding(horizontal = PixelDensity.verySmall),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "$8.59",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    "stock: 30",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    )
                )
            }
        }
    }
}


