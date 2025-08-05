package org.akwapos.app.platform

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.akwapos.AkwaposDatabase

actual fun getPlatform(): Platform = Platform.DESKTOP
actual suspend fun createDatabaseDriver(): SqlDriver {
//    You are using JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY),
    //    which creates an in-memory database that disappears when the app closes.
//    return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
//        .also { AppDatabase.Schema.create(it).await() }

    return JdbcSqliteDriver("jdbc:sqlite:objects.db")
        .also { AkwaposDatabase.Schema.create(it).await() }
}