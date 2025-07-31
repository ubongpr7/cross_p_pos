package org.akwapos.app.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.ScreenModel

class AuthenticationScreenModel : ScreenModel {
    enum class AuthScreenState {
        SIGN_IN, SIGN_UP
    }

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisibile by mutableStateOf(false)
    var confirmPassword by mutableStateOf("")
    var authScreenState by mutableStateOf<AuthScreenState>(AuthScreenState.SIGN_IN)


    fun getPasswordStrength(password: String): Pair<Float, Color> {
        return when {
            password.isEmpty() -> Pair(0.2f, Color.LightGray)
            password.length < 4 -> Pair(0.2f, Color.Red)
            password.length < 8 -> Pair(0.5f, Color.Yellow, )
            else -> Pair(1f, Color.Green, )
        }
    }

}