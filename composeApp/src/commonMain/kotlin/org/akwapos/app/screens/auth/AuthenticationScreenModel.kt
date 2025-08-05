package org.akwapos.app.screens.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.akwapos.app.core.AuthClient
import org.akwapos.app.core.LocalDatabaseManager
import org.akwapos.app.models.AuthActionType
import org.akwapos.app.models.AuthStore
import org.akwapos.app.models.UserDataModel
import org.akwapos.app.utils.Tools

class AuthenticationScreenModel : ScreenModel {
    enum class AuthScreenState {
        SIGN_IN, SIGN_UP
    }

    enum class VerificationCode {
        SEND_VERIFICATION_CODE,
        CONFIRM_VERIFICATION_CODE,
    }

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)
    var confirmPassword by mutableStateOf("")
    var verifyCode by mutableStateOf("")
    var popupVisibility by mutableStateOf(Triple(false, "", ""))
    var loadingVisibility by mutableStateOf(Pair(false, ""))
    var authScreenState by mutableStateOf<AuthScreenState>(AuthScreenState.SIGN_IN)
    var verificationCode by mutableStateOf<VerificationCode>(VerificationCode.SEND_VERIFICATION_CODE)
    private val authClient = AuthClient()
    private var userData: UserDataModel? = null


    fun getPasswordStrength(password: String): Pair<Float, Color> {
        return when {
            password.isEmpty() -> Pair(0.2f, Color.LightGray)
            password.length < 4 -> Pair(0.2f, Color.Red)
            password.length < 8 -> Pair(0.5f, Color.Yellow)
            else -> Pair(1f, Color.Green)
        }
    }

    fun sendVerificationCode() {
        if (Tools.isValidEmail(email.trim()) && password.isNotEmpty()) {
            screenModelScope.launch(Dispatchers.Default) {
                loadingVisibility = Pair(true, "Sending verification code please wait ...")
                val signIn = authClient.signInUser(email.trim(), password.trim())
                signIn.onSuccess {
                    userData = it
                    authClient.sendVerificationCode(
                        email = email.trim(),
                        authActionType = AuthActionType.SEND_CODE
                    ).onSuccess {
                        loadingVisibility = Pair(false, "")
                        verificationCode = VerificationCode.CONFIRM_VERIFICATION_CODE
                    }.onFailure {
                        loadingVisibility = Pair(false, "")
                        popupVisibility = Triple(
                            true,
                            "Error Message",
                            "could not send verification code, please check your email and try again later"
                        )
                    }
                }.onFailure {
                    loadingVisibility = Pair(false, "")
                    popupVisibility = Triple(
                        true,
                        "Error Message",
                        "could not send verification user, please check your email and password and try again later"
                    )
                }
            }
        }else{
            popupVisibility = Triple(
                true,
                "Error Message",
                "please check your email and password and try again later"
            )
        }
    }

    fun confirmVerificationCode(
        onSuccess: () -> Unit,
    ) {
        screenModelScope.launch(Dispatchers.Default) {
            loadingVisibility = Pair(true, "Verifying code please wait ...")
            val result = authClient.sendVerificationCode(
                email = email.trim(),
                authActionType = AuthActionType.VERIFY_CODE,
                code = verifyCode.trim()
            )
            result.onSuccess {
                loadingVisibility = Pair(false, "")
                LocalDatabaseManager.insertOrReplaceAuthStore(
                    AuthStore(
                        json = Json.encodeToString(userData),
                        isVerified = true
                    )
                )
                onSuccess()
            }
            result.onFailure {
                loadingVisibility = Pair(false, "")
                println("Failure $it")
                popupVisibility = Triple(
                    true,
                    "Verification Error Message",
                    "The verification code you entered is incorrect, please try again"
                )
            }
        }
    }
}