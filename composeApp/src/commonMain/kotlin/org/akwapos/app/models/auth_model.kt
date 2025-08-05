package org.akwapos.app.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthStore(
    val id: Long?=null,
    val json: String,
    val isVerified: Boolean
)

@Serializable data class SuccessResponse(
    val message: String
)

enum class AuthActionType(action:String){
    SEND_CODE("send_code"),
    VERIFY_CODE("verify_code")
}