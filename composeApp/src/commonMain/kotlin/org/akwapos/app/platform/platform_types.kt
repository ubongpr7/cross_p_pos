package org.akwapos.app.platform

enum class Platform {
    ANDROID, IOS, DESKTOP, WEB
}

sealed class PlatformOrientation {
    data class Portrait(val width: Int, val height: Int) : PlatformOrientation()
    data class LandScape(val width: Int, val height: Int) : PlatformOrientation()
    data class Tablet(val width: Int, val height: Int) : PlatformOrientation()
}
