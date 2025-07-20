import androidx.compose.ui.window.ComposeUIViewController
import org.akwapos.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
