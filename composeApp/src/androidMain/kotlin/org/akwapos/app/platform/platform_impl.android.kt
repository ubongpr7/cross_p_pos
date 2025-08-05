package org.akwapos.app.platform

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import database.akwapos.AkwaposDatabase
import org.akwapos.app.AppActivity


actual fun getPlatform(): Platform = Platform.ANDROID

actual suspend fun createDatabaseDriver(): SqlDriver {
    return AndroidSqliteDriver(
        schema = AkwaposDatabase.Schema.synchronous(),
        AppActivity.instance,
        "objects.db"
    )
}