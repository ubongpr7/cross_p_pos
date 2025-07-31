package org.akwapos.app.screens.transactions

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
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import org.akwapos.app.platform.PlatformOrientation
import org.akwapos.app.theme.*


object TransactionsScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { TransactionsScreenModel() }
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
                .fillMaxSize()
                .jVerticalScroll()
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                .padding(PixelDensity.medium),
//                .then(modifier),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(PixelDensity.large * 2)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Transactions", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                Button(onClick = {}) {
                    Icon(TablerIcons.Download, "download icon")
                    Text("Export", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
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
                            DisplayTransactionsFilterMobile(screenModel = screenModel)
                            DisplayTransactionsMobile(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(vertical = PixelDensity.medium)
                            )
                        }
                    }

                    else -> {
                        DisplayTransactionFilter(screenModel)
                        DisplayTransactions(
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
    private fun DisplayTransactions(modifier: Modifier, displayWidth: Int) {
        LandScapeColumnScroll(modifier) {
            Column(Modifier.jHorizontalScroll()) {
                val width = remember(displayWidth) { displayWidth / 7 }
                Row(
                    Modifier.fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceContainerLow)
                        .padding(vertical = PixelDensity.small),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Order #",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Customer",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Date & Time",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Amount",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Payment Method",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Status",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Actions",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                repeat(10) {
                    val randomStock = remember {
                        listOf(
                            Pair(Color.Green.copy(alpha = 0.5f), "Completed"),
                            Pair(Color.Yellow.copy(alpha = 0.5f), "Refunded"),
                            Pair(Color.Red.copy(alpha = 0.5f), "Void"),
                        ).random()
                    }
                    val isVisible = remember { mutableStateOf(false) }
                    Row(
                        Modifier.fillMaxWidth()
                            .padding(vertical = PixelDensity.small),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = "ORD-1001",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = "Alice Williams",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = "4/15/2023, 2:30:25 PM",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = "$45.99",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        HorizontalTextIcon(
                            leadingIcon = { Icon(TablerIcons.Cash, "cash icon") },
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = "$45.99",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
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
                            overflow = TextOverflow.Ellipsis,
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
                            IconButton(onClick = { isVisible.value = !isVisible.value }) {
                                Icon(
                                    if (isVisible.value) TablerIcons.Eye else TablerIcons.EyeOff,
                                    "toggle visibility icon",
                                    tint = MaterialTheme.colorScheme.inversePrimary
                                )
                            }
                        }
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = PixelDensity.large * 2),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Showing 5 of 5 transactions")
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
                        Text("Next", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                    }
                }
            }
        }
    }

    @Composable
    private fun DisplayTransactionFilter(screenModel: TransactionsScreenModel) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = screenModel.searchTransactions,
                onValueChange = { screenModel.searchTransactions = it },
                placeholder = {
                    Text(
                        "Search by order number or customer...",
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
        }
    }

    @Composable
    private fun DisplayTransactionsMobile(modifier: Modifier) {
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
                    }, text = "Customer",
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
                DisplayTransactionMobile(
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
                    "Showing 5 of 5 transactions",
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
    private fun DisplayTransactionMobile(modifier: Modifier) {
        Column(modifier) {
            val isExpand = remember { mutableStateOf(false) }
            val isVisible = remember { mutableStateOf(false) }
            val bgColor = remember {
                listOf(
                    Color.Green.copy(alpha = 0.5f),
                    Color.Red.copy(alpha = 0.5f),
                    Color.Yellow.copy(alpha = 0.5f),
                ).random()
            }
            Column {
                Row(
                    Modifier.fillMaxWidth(),
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
                        vSpacing = PixelDensity.setValue(0),
                        hSpacing = PixelDensity.medium,
                        text = "Bob Johnson",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )

                    IconButton(onClick = { isVisible.value = !isVisible.value }) {
                        Icon(
                            if (isVisible.value) TablerIcons.Eye else TablerIcons.EyeOff,
                            "edit icon",
                            tint = MaterialTheme.colorScheme.inversePrimary
                        )
                    }
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "4/15/2023, 2:30:25 PM",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                )
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
                            text = "Order #",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = "Amount",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = "Payment Method",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ORD-1004",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = "$45.99",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        HorizontalTextIcon(
                            leadingIcon = { Icon(TablerIcons.Wallet, "wallet icon") },
                            text = "Mobile Payment",
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
    private fun DisplayTransactionsFilterMobile(screenModel: TransactionsScreenModel) {
        FlowRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.medium),
            verticalArrangement = Arrangement.spacedBy(PixelDensity.small),
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = screenModel.searchTransactions,
                onValueChange = { screenModel.searchTransactions = it },
                singleLine = true,
                placeholder = {
                    Text(
                        "Search by order number or customers...",
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
        }
    }
}