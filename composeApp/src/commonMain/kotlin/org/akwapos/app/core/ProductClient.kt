package org.akwapos.app.core

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import org.akwapos.app.models.product.PosProductModel

private const val PRODUCT_ROOT_PATH =
    "https://dev.product.destinybuilders.africa/product_api/products/"

object ProductClient {
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

    suspend fun getAllProducts(): Result<List<PosProductModel>> {
        return runCatching {
            val result = client.get(PRODUCT_ROOT_PATH) {
                header("Content-Type", "application/json")
            }
            val productModel =
                clientJson.decodeFromString<List<PosProductModel>>(result.bodyAsText())
            if (result.status != io.ktor.http.HttpStatusCode.OK) {
                throw Exception(productModel.toString())
            } else {
                productModel
            }
        }
    }

    suspend fun getProductById(id: String): Result<PosProductModel> {
        return runCatching {
            val result = client.get("$PRODUCT_ROOT_PATH$id/") {
                header("Content-Type", "application/json")
            }
            val productModel = result.body<PosProductModel>()
            if (result.status != io.ktor.http.HttpStatusCode.OK) {
                throw Exception(productModel.toString())
            } else {
                productModel
            }
        }
    }

    fun getProductsFlow(): Flow<List<PosProductModel>?> {
        return flow {
            val p = getAllProducts().getOrNull()
            emit(p)
        }
    }

}