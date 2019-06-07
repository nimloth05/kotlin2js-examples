import kotlin.browser.window

fun main() {
  window.onload = {
    jQuery("body")
        .append("<p>P added via string function from jquery</p>")

    jQuery("body")
        .click { it: dynamic ->
          println("click pos (${it.pageX}, ${it.pageY})")
        }
  }
}