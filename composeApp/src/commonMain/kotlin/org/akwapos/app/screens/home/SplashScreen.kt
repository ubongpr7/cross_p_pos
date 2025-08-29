package org.akwapos.app.screens.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.TablerIcons
import compose.icons.tablericons.ShoppingCart
import org.akwapos.app.theme.PixelDensity

object SplashScreen: Screen {
    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize(), Alignment.Center){
            Icon(
                TablerIcons.ShoppingCart,
                "splash icon",
                tint=Color.Green.copy(alpha = 0.5f),
                modifier = Modifier.size(PixelDensity.large*10)
            )
        }
    }
}