




@JsModule("jquery")
external fun jQuery(selector: String = definedExternally): JQuery

/**
 * Result object from selector function
 */
external class JQuery {
  fun click(handler: (dynamic) -> Unit): JQuery
  fun append(s: String): JQuery
}

