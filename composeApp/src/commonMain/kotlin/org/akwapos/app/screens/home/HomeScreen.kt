package org.akwapos.app.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import compose.icons.TablerIcons
import compose.icons.tablericons.*
import kotlinx.coroutines.launch
import org.akwapos.app.platform.PlatformOrientation
import org.akwapos.app.screens.CustomersScreenType
import org.akwapos.app.screens.DashboardScreenType
import org.akwapos.app.screens.PointOfSaleScreenType
import org.akwapos.app.screens.ProductsScreenType
import org.akwapos.app.screens.customers.CustomersScreen
import org.akwapos.app.screens.dashboard.DashboardScreen
import org.akwapos.app.screens.pointofsale.PointOfSaleScreen
import org.akwapos.app.screens.products.ProductsScreen
import org.akwapos.app.theme.*
import org.akwapos.app.utils.toPercentage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var isDark by LocalThemeIsDark.current
    var isSideBarOpen by remember { mutableStateOf(false) }
    val platformOrientation = rememberPlatformOrientation()
    val (platformWidth, _) = remember(platformOrientation) {
        when (platformOrientation) {
            is PlatformOrientation.LandScape -> platformOrientation.width to platformOrientation.height
            is PlatformOrientation.Portrait -> platformOrientation.width to platformOrientation.height
            is PlatformOrientation.Tablet -> platformOrientation.width to platformOrientation.height
        }
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // 2. Create a coroutine scope for state changes
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            when (platformOrientation) {
                is PlatformOrientation.Portrait -> {
                    scope.launch { drawerState.close() }
                    ModalDrawerSheet(
                        modifier = Modifier.width(platformOrientation.width.toPercentage(70).dp)
                    ) {
                        Box(
                            Modifier.fillMaxWidth()
                                .height(platformOrientation.height.toPercentage(20).dp)
                                .drawUnderLine(
                                    MaterialTheme.colorScheme.onBackground,
                                    PixelDensity.verySmall
                                )
                                .padding(PixelDensity.small),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "POS System",
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }
                        Spacer(Modifier.height(PixelDensity.medium))
                        NavPageItems(
                            modifier = Modifier
                                .width(platformOrientation.width.toPercentage(70).dp)
                                .padding(PixelDensity.medium),
                            showText = true,
                            navController = navController,
                        )
                    }
                }

                else -> Unit
            }
        }
    ) {
        // Screen content
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.drawUnderLine(MaterialTheme.colorScheme.onBackground, PixelDensity.small),
                    navigationIcon = {
                        if (platformOrientation is PlatformOrientation.Portrait) {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {

                                Icon(
                                    TablerIcons.Menu2,
                                    "display navigation items",
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        } else {
                            if (isSideBarOpen) {
                                Row(
                                    modifier = Modifier.width(platformWidth.toPercentage(25).dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("POS System")
                                    IconButton(onClick = { isSideBarOpen = !isSideBarOpen }) {
                                        Icon(TablerIcons.ChevronLeft, "left arrow")
                                    }
                                }
                            } else {
                                IconButton(onClick = { isSideBarOpen = !isSideBarOpen }) {
                                    Icon(TablerIcons.ChevronRight, "right arrow")
                                }
                            }
                        }
                    },
                    title = {
                        Row {
                            Text("Point of Sale", style = MaterialTheme.typography.titleLarge)
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(TablerIcons.Bell, "notification icon")
                        }
                        IconButton(onClick = { isDark = !isDark }) {
                            Icon(if (isDark) TablerIcons.Moon else TablerIcons.Sun, "theme icon")
                        }
                        IconButton(onClick = {}) {
                            Icon(TablerIcons.User, "notification icon")
                        }
                    })
            }
        ) { pv ->
            Row(
                modifier = Modifier.padding(pv).padding(PixelDensity.medium)
            ) {
                // Sidebar
                when (platformOrientation) {
                    is PlatformOrientation.Portrait -> {
                        // No action needed for portrait mode
                    }

                    else -> {
                        NavPageItems(
                            modifier = Modifier
                                .padding(PixelDensity.small)
                                .thenIf(isSideBarOpen) {
                                    width(platformWidth.toPercentage(20).dp)
                                },
                            showText = isSideBarOpen,
                            navController = navController
                        )
                    }
                }
                // MAIN page
                NavHost(
                    modifier = modifier.weight(1f),
                    navController = navController,
                    startDestination = DashboardScreenType
                ) {
                    composable<DashboardScreenType> { DashboardScreen() }
                    composable<PointOfSaleScreenType> { PointOfSaleScreen() }
                    composable<ProductsScreenType> { ProductsScreen() }
                    composable<CustomersScreenType> { CustomersScreen() }
                }
            }
        }
    }
}

@Composable
private fun NavPageItems(
    modifier: Modifier,
    showText: Boolean,
    navController: NavHostController,
) {
    Column {
        val curRoute by navController.currentBackStackEntryAsState()
        // Dashboard
        NavPageItem<DashboardScreenType>(
            curRoute, navController,
            "Dashboard", TablerIcons.Home,
            modifier, showText
        )
        // Point of Sale
        NavPageItem<PointOfSaleScreenType>(
            curRoute, navController,
            "Point of Sale", TablerIcons.ShoppingCart,
            modifier, showText
        )
        // Product
        NavPageItem<ProductsScreenType>(
            curRoute, navController,
            "Product", TablerIcons.Box,
            modifier, showText
        )
        // Product
        NavPageItem<CustomersScreenType>(
            curRoute, navController,
            "Customers", TablerIcons.Users,
            modifier, showText
        )
    }
}


@Composable
private inline fun <reified T> NavPageItem(
    curRoute: NavBackStackEntry?,
    navController: NavHostController,
    title: String,
    imageVector: ImageVector,
    modifier: Modifier,
    showText: Boolean
) {
    Row(
        modifier = Modifier
            .padding(PixelDensity.small)
            .clip(RoundedCornerShape(30))
            .background(
                if (curRoute?.destination?.route == T::class.qualifiedName) MaterialTheme.colorScheme.inversePrimary
                else MaterialTheme.colorScheme.surface
            )
            .clickable(
                enabled = curRoute?.destination?.route != T::class.qualifiedName,
                onClick = { navController.navigate(T::class.qualifiedName ?: "") }
            )
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(PixelDensity.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector, "$title page")
        if (showText) Text(title, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}
