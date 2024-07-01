import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import ui.ChatApp

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow(
        "KChat WEB",
    ) {
        ChatApp()
    }
}
