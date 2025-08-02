package org.akwapos.app.core

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


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
    private val client = HttpClient {
        install(ContentNegotiation){
            json(clientJson)
        }
    }
    private val rootPath = "https://dev.users.destinybuilders.africa/api/v1/accounts"

    suspend fun sendVerificationEmail(email: String){
        client.post("$rootPath/verify"){
            setBody(mapOf("email" to email, "action" to "send_code"))
        }
    }
}