package org.akwapos.app.platform

import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.w3c.dom.Worker


actual fun getPlatform(): Platform = Platform.WEB

actual suspend fun createDatabaseDriver(): app.cash.sqldelight.db.SqlDriver {
    return WebWorkerDriver(
        Worker(
            js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")
        )
    )
}