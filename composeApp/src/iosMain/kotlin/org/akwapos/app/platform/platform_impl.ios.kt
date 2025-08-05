package org.akwapos.app.platform

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import database.akwapos.AkwaposDatabase

actual fun getPlatform(): Platform = Platform.IOS
actual suspend fun createDatabaseDriver(): SqlDriver {
    return NativeSqliteDriver(AkwaposDatabase.Schema.synchronous(), "objects.db")
}