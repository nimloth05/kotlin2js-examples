import org.w3c.dom.HTMLElement

@JsModule("jquery")
external val myJQuery: JQueryStatic = definedExternally

external interface JQueryStatic {
  fun ajax(settings: AjaxSettings): JqXHR
}

/**
 * Simulates the $("selector string") function from jQuery
 */
@Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")
inline operator fun JQueryStatic.invoke(selector: String): JQuery {
  return asDynamic()(selector)
}

external class JQuery {
  fun `val`(): Any //String | Number | Array
  fun empty()
  fun append(element: HTMLElement): JQuery
}

external interface JqXHR {

  fun then(success: (data: dynamic) -> Any?): JqXHR

  fun then(success: (data: dynamic) -> dynamic,
           error: (error: dynamic) -> Unit): JqXHR

  fun fail(error: (error: dynamic) -> Unit): JqXHR

  fun done(success: (data: dynamic, textStatus: String, jqXHR: JqXHR) -> Unit): JqXHR
}

external interface AjaxSettings {
  var contentType: Any? get() = definedExternally; set(value) = definedExternally
  var dataType: String? get() = definedExternally; set(value) = definedExternally
  var url: String? get() = definedExternally; set(value) = definedExternally
}

