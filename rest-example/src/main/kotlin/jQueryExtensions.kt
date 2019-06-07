import kotlinx.html.TagConsumer
import kotlinx.html.dom.create
import org.w3c.dom.HTMLElement
import kotlin.browser.document
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun JQuery.appendHTML(block: TagConsumer<HTMLElement>.() -> HTMLElement) {
  append(block(document.create))
}

suspend fun <T> JQueryStatic.ajaxCoroutine(params: AjaxSettings): T = suspendCoroutine { continuation ->
  ajax(params)
      .done { data: dynamic, _, _ ->
        continuation.resume(data as T)
      }
      .fail { error: dynamic ->
        continuation.resumeWithException(RequestFailedException("Could not perform request: error: '$error'"))
      }
}

inline fun <T : Any> jsObject(builder: T.() -> Unit): T {
  val obj: T = js("({})")
  return obj.apply {
    builder()
  }
}

inline fun paramObj(builder: dynamic.() -> Unit): dynamic = jsObject(builder)
