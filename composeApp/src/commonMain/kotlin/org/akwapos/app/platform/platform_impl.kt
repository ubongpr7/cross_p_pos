package org.akwapos.app.platform

import app.cash.sqldelight.db.SqlDriver


expect fun getPlatform(): Platform

expect suspend fun createDatabaseDriver(): SqlDriver