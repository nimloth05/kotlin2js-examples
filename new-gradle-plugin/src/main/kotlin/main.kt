import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.dom.create
import kotlinx.html.p
import kotlin.browser.document

fun main() {
    val myDiv = document.create.div("panel") {
      p {
        +"Hello js world, this is kotlin speaking with the new plugin"
        p {
          a("https://kotlinlang.org") { +"official Kotlin site" }
        }
      }
    }

    document.getElementById("container")!!.appendChild(myDiv)
}

class Person(val name: String, val lastName: String) {
  val fullName get() = "$name $lastName"
}