package org.akwapos.app.screens.customers

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


object CustomersScreen : Screen {
    @Composable
    override fun Content() {
        val platformOrientation = rememberPlatformOrientation()
        val screenModel = rememberScreenModel { CustomersScreenModel() }
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
                    DisplayCustomersFilterMobile(screenModel = screenModel)
                    DisplayCustomersMobile(
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
                    DisplayCustomerFilter(screenModel)
                    DisplayCustomers(
                        modifier = Modifier.fillMaxWidth()
                            .padding(vertical = PixelDensity.medium),
                        displayWidth = platformWidth
                    )
                }
            }
        }
    }


    @Composable
    private fun DisplayCustomersFilterMobile(screenModel: CustomersScreenModel) {
        FlowRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.medium),
            verticalArrangement = Arrangement.spacedBy(PixelDensity.small),
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = screenModel.searchCustomers,
                onValueChange = { screenModel.searchCustomers = it },
                singleLine = true,
                placeholder = {
                    Text(
                        "Search customers by name, email, or phone...",
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
    private fun DisplayCustomersMobile(modifier: Modifier) {
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
                    }, text = "Customers",
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
                DisplayCustomerMobile(
                    Modifier.padding(vertical = PixelDensity.verySmall)
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth().padding(vertical = PixelDensity.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Showing 5 of 5 customers",
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
    private fun DisplayCustomerMobile(modifier: Modifier = Modifier) {
        Column(modifier) {
            val isExpand = remember { mutableStateOf(false) }
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
                        vSpacing = PixelDensity.small,
                        hSpacing = PixelDensity.medium,
                        text = "Bob Johnson",
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
                Row(
                    Modifier.fillMaxWidth()
                        .padding(
                            bottom = PixelDensity.small,
                            start = PixelDensity.small,
                            end = PixelDensity.small
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "bob@example.com",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        text = "+1 (555) 456-7890",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
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
                            text = "Total Spent",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = "Last Purchase",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = "Loyalty Points",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
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
                            text = "$2345.20",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = "4/5/2023",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = "780 pts",
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
    private fun DisplayCustomerFilter(screenModel: CustomersScreenModel) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(PixelDensity.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = screenModel.searchCustomers,
                onValueChange = { screenModel.searchCustomers = it },
                placeholder = {
                    Text(
                        "Search customers by name, email, or phone...",
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
    private fun ColumnScope.DisplayCustomers(
        modifier: Modifier,
        displayWidth: Int
    ) {
        LandScapeColumnScroll(modifier) {
            Column(Modifier.jHorizontalScroll()) {
                val width = remember(displayWidth) { displayWidth / 6 }
                Row(
                    Modifier.fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceContainerLow)
                        .padding(vertical = PixelDensity.small),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                        text = "Contact",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Total Spent",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Last Purchase",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Text(
                        modifier = Modifier.width(PixelDensity.setValue(width)),
                        text = "Loyalty Points",
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
                            Pair(Color.Magenta.copy(alpha = 0.5f), "1000 pts"),
                            Pair(Color.Cyan.copy(alpha = 0.5f), "700 pts"),
                            Pair(Color.Yellow.copy(alpha = 0.5f), "400 pts"),
                        ).random()
                    }
                    Row(
                        Modifier.fillMaxWidth()
                            .padding(vertical = PixelDensity.small),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
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
                        Column(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            HorizontalTextIcon(
                                vSpacing = PixelDensity.setValue(0),
                                hSpacing = PixelDensity.verySmall,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                leadingIcon = { Icon(TablerIcons.Mail, "mail icon") },
                                text = "alice@example.com"
                            )
                            HorizontalTextIcon(
                                hSpacing = PixelDensity.verySmall,
                                vSpacing = PixelDensity.setValue(0),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                leadingIcon = { Icon(TablerIcons.Phone, "mail icon") },
                                text = "+1 (555) 789-0123"
                            )
                        }
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = "$567.80",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            modifier = Modifier.width(PixelDensity.setValue(width)),
                            text = "3/28/2023",
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
                                .padding(
                                    horizontal = PixelDensity.verySmall,
                                    vertical = PixelDensity.verySmall
                                ),
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
                            IconButton(onClick = {}) {
                                Icon(
                                    TablerIcons.Edit,
                                    "edit icon",
                                    tint = MaterialTheme.colorScheme.secondary
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
                Text("Showing 5 of 5 customers")
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
}