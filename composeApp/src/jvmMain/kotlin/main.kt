import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import org.akwapos.app.App

fun main() = application {
    Window(
        title = "Akwapos",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(350, 600)
        App()
    }
}

