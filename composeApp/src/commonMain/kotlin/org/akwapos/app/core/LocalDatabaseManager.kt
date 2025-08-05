package org.akwapos.app.core

import database.akwapos.AkwaposDatabase
import database.akwapos.AkwaposDatabaseQueries
import kotlinx.serialization.json.Json
import org.akwapos.app.models.AuthStore
import org.akwapos.app.models.UserDataModel
import org.akwapos.app.platform.createDatabaseDriver
import org.akwapos.app.utils.mapper

object LocalDatabaseManager {
    private var database: AkwaposDatabase? = null
    private var dbQuery: AkwaposDatabaseQueries? = null

    suspend fun initializeDatabase() {
        database = AkwaposDatabase(createDatabaseDriver())
        dbQuery = database?.akwaposDatabaseQueries
    }

    suspend fun insertOrReplaceAuthStore(authStore: AuthStore) {
        dbQuery?.insertOrReplaceAuthStore(authStore.json, if (authStore.isVerified) 1 else 0)
    }

    fun getAuthStore(): AuthStore? {
        return dbQuery?.getAuthStore()?.executeAsOneOrNull()?.mapper {
            AuthStore(
                id = it.id,
                json = it.json ?: "",
                isVerified = it.isVerified == 1L
            )
        }
    }

    fun getUserData(): UserDataModel? {
        return dbQuery?.getAuthStore()?.executeAsOneOrNull()?.mapper {
            Json.decodeFromString<UserDataModel>(it.json ?: "")
        }
    }

    fun getIsVerified(): Boolean {
        return dbQuery?.getAuthStore()?.executeAsOneOrNull()?.mapper {
            it.isVerified == 1L
        } ?: false
    }

    suspend fun deleteAuthStore() {
        dbQuery?.deleteAuthStore()
    }
}