import kotlinx.html.*
import kotlinx.html.js.onClickFunction

/**
 * Adds a simple form, sends an Ajax request and displays the result
 */
fun uiExample() {
  myJQuery("body").appendHTML {
    div {
      span { +"Enter PLZ" }
      input {
        attributes["id"] = Ids.POSTAL_INPUT
      }
      button {
        +"Update"
        onClickFunction = {
//                    doRequestPromise(myJQuery("#${Ids.POSTAL_INPUT}").`val`().toString())
          doRequestCoroutine(myJQuery("#${Ids.POSTAL_INPUT}").`val`().toString())
        }
      }
      div(id = Ids.RESULT_DIV) {
        div(id = Ids.POSTAL_DIV)
        div(id = Ids.TIMEZONE_DIV)
        div(id = Ids.ERROR_DIV)
      }
    }
  }
}

fun updatePostalCodeUi(result: PostalCodeEntry) {
  myJQuery("#${Ids.POSTAL_DIV}").apply {
    empty()
    appendHTML {
      div {
        h1 { +"Request 1" }
        div {
          span {
            +"Ortschaft"
            attributes["style"] = "margin-right: 20px"
          }
          span {
            +result.placeName
          }
        }
        div {
          span {
            +"Latitude"
            attributes["style"] = "margin-right: 20px"
          }
          span {
            +result.lat
          }
        }
        div {
          span {
            +"Longitude"
            attributes["style"] = "margin-right: 20px"
          }
          span {
            +result.lng
          }
        }
      }
    }
  }
}

fun updateSunsetInfo(sunrise: String, sunset: String) {
  myJQuery("#${Ids.TIMEZONE_DIV}")
      .apply {
        empty()
        appendHTML {
          div {
            h1 { +"Request 2" }
            div {
              span {
                attributes["style"] = "margin-right: 20px"
                +"Sonnenaufgang"
              }
              span {
                +sunrise
              }
            }
            div {
              span {
                attributes["style"] = "margin-right: 20px"
                +"Sonnenuntergang"
              }
              span {
                +sunset
              }
            }
          }
        }
      }
}

fun updateUiError(e: RequestFailedException) {
  myJQuery("#${Ids.ERROR_DIV}")
      .apply {
        empty()
        appendHTML {
          div {
            span {
              +(e.message ?: "Unknown error")
              attributes["style"] = "color: red"
            }
          }
        }
      }
}

object Ids {
  const val ERROR_DIV = "error"
  const val RESULT_DIV = "result"
  const val POSTAL_INPUT = "postalCode"
  const val POSTAL_DIV = "postal"
  const val TIMEZONE_DIV = "timezone"

}

fun <T, C : TagConsumer<T>> C.div(id: String? = null, classes: String? = null, block: DIV.() -> Unit = {}): T =
    DIV(attributesMapOf("id", id, "class", classes), this).visitAndFinalize(this, block)


