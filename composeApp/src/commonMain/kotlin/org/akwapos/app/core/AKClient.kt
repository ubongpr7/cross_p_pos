package org.akwapos.app.core

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object AKClient {
    private val cliJson = Json {
        ignoreUnknownKeys = true
    }
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(cliJson)
        }
    }
}