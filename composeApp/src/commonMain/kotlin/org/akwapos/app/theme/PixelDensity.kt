package org.akwapos.app.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object PixelDensity {
    val verySmall = 3.dp
    val small = 6.dp
    val medium = 9.dp
    val large = 12.dp

    fun setValue(num: Int): Dp = num.dp
}