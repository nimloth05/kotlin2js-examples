import kotlin.browser.document
import kotlin.browser.window

fun addNode() {
  val span = document.createElement("span")
  span.textContent = "Hello NodeJs Toolchain"
  document.body?.appendChild(span)
}

