import kotlin.browser.document

/*
 * First example: Create a span node, set some text and add it to the document
 */
fun main() {
  val span = document
      .createElement("span")
  span.textContent = "Hello KotlinJs!"
  document.body?.appendChild(span)
}

