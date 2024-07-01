//import androidx.compose.ui.window.Window
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import androidx.compose.ui.window.ComposeViewport
//import androidx.compose.ui.window.ComposeWindow
//import androidx.compose.ui.window.DefaultWindowState
import kotlinx.browser.document
//import androidx.compose.ui.window.Window
import org.jetbrains.skiko.wasm.onWasmReady
import org.w3c.dom.Element
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement
import ui.ChatApp

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val compose = document.getElementById("area") as? Element ?: throw RuntimeException("the DOM has no 'ComposeTarget' id")
//    onWasmReady {
    CanvasBasedWindow(
        "KChat",
//            document.body!!
        ) {
            ChatApp()
        }
//    }
}

//@OptIn(ExperimentalComposeUiApi::class)
//fun main() {
//    ComposeViewport(document.body!!) {
//        ChatApp()
//    }
//}

//
//fun main() {
//    onWasmReady {
//        Window("KMChat - WEB") {
//            ChatApp()
//        }
//    }
//}

//@ExperimentalComposeUiApi
//fun CustomComposeViewport(
//    viewportContainer: Element,
//    content: @Composable () -> Unit = { }
//) {
//    val canvas = document.createElement("canvas") as HTMLCanvasElement
//    canvas.setAttribute("tabindex", "0")
//
//    viewportContainer.appendChild(canvas)
//
//    ComposeWindow(
//        canvas = canvas,
//        content = content,
//        state = DefaultWindowState(viewportContainer)
//    )
//}