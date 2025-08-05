package org.akwapos.app.core

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.akwapos.app.models.AuthActionType
import org.akwapos.app.models.SuccessResponse
import org.akwapos.app.models.UserDataModel

private const val ROOTPATH = "https://dev.users.destinybuilders.africa"
private const val API_ROOT_PATH = "$ROOTPATH/api/v1"
private const val AUTH_ROOT_PATH = "$ROOTPATH/jwt"
//"https://dev.users.destinybuilders.africa/api/v1"



//        VERIFY CODE
//{
//    "message": "Verification successful",
//    "user_id": 5,
//    "email": "aigbojeohiorenua@gmail.com"
//}
//{
//    "email":"aigbojeohiorenua@gmail.com",
//    "code":"721464",
//    "action":"verify_code"
//}

//        VERIFY EMAIL
//{
//    "message": "Verification successful",
//}
//{
//    "email":"aigbojeohiorenua@gmail.com",
//    "action":"verify_code"
//}


class AuthClient {
    private val clientJson = Json {
        ignoreUnknownKeys = true
    }
    private val client = HttpClient() {
        install(HttpTimeout) {
            requestTimeoutMillis = 60_000  // Wait up to 60 seconds for the full request
            connectTimeoutMillis = 30_000  // Wait up to 30 seconds to establish connection
            socketTimeoutMillis = 60_000   // Wait up to 60 seconds for socket data
        }
        install(ContentNegotiation) {
            json(clientJson)
        }
    }

    suspend fun sendVerificationCode(
        email: String,
        code: String? = null,
        authActionType: AuthActionType
    ): Result<SuccessResponse> {
        return runCatching {
            val result = client.post("$API_ROOT_PATH/accounts/verify/") {
                header("Content-Type", "application/json")
                if (code != null) setBody(
                    mapOf(
                        "email" to email,
                        "code" to code,
                        "action" to authActionType.name.lowercase()
                    )
                )
                else
                    setBody(mapOf("email" to email, "action" to authActionType.name.lowercase()))
            }
            println("Result ${result.bodyAsText()}")
            val message: SuccessResponse = result.body()
            if (result.status != io.ktor.http.HttpStatusCode.OK) {
                throw Exception(message.message)
            } else {
                message
            }
        }
    }


    suspend fun signInUser(email: String, password: String): Result<UserDataModel> {
        return runCatching {
            val result = client.post("$AUTH_ROOT_PATH/create/") {
                header("Content-Type", "application/json")
                setBody(mapOf("email" to email, "password" to password))
            }
            println("Result ${result.bodyAsText()}")
            val userData: UserDataModel = result.body()
            if (result.status != io.ktor.http.HttpStatusCode.OK) {
                throw Exception(userData.toString())
            } else {
                userData
            }
        }
    }

}