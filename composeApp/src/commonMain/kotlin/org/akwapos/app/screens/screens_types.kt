package org.akwapos.app.screens

import cafe.adriel.voyager.core.screen.Screen


fun getScreenName(screen: Screen): String {
    val name = screen::class.simpleName?.removeSuffix("Screen").orEmpty()
    return buildString {
        name.forEachIndexed { index, c ->
            if (c.isUpperCase() && index != 0) append(' ')
            append(c)
        }
    }
}
