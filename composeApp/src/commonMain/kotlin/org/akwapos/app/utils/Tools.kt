package org.akwapos.app.utils

import cafe.adriel.voyager.core.screen.Screen

object Tools {
    fun getScreenName(screen: Screen): String {
        val name = screen::class.simpleName?.removeSuffix("Screen").orEmpty()
        return buildString {
            name.forEachIndexed { index, c ->
                if (c.isUpperCase() && index != 0) append(' ')
                append(c)
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        return "^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*\$"
            .toRegex().matches(email)
    }

}