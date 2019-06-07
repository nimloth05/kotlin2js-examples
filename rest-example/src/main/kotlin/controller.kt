import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun doRequestCoroutine(postalCode: String) {
  GlobalScope.launch {
    try {
      val postalCodeResult = doPostalCodeRequest(postalCode)

      val postalCodeEntry = postalCodeResult.postalCodes[0]
      updatePostalCodeUi(postalCodeEntry)

      val timeZoneResult = doTimeZoneRequest(postalCodeEntry)
      updateSunsetInfo(formatDate(timeZoneResult.sunrise), formatDate(timeZoneResult.sunset))

    } catch (e: RequestFailedException) {
      updateUiError(e)
    }
  }
}

private suspend fun doTimeZoneRequest(postalCodeEntry: PostalCodeEntry): TimeZoneResult {
  return myJQuery
      .ajaxCoroutine(createTimeZoneRequest(postalCodeEntry.lat, postalCodeEntry.lng))
}

private suspend fun doPostalCodeRequest(postalCode: String): PostalCodeSearchResult {
  return myJQuery.ajaxCoroutine(createPostalCodeSearchRequest(postalCode))
}

fun doRequestPromise(postalCode: String) {
  myJQuery
      .ajax(createPostalCodeSearchRequest(postalCode))
      .then { data: PostalCodeSearchResult ->
        val entry = data.postalCodes[0]
        updatePostalCodeUi(entry)
        myJQuery.ajax(createTimeZoneRequest(entry.lat, entry.lng))
      }
      .then { data: TimeZoneResult ->
        updateSunsetInfo(data.sunrise, data.sunset)
      }
      .fail { error: dynamic ->
        updateUiError(RequestFailedException("Could not perform request:  '$error'"))
      }
}

fun formatDate(data: String): String {
  return data.replace("-", ".")
}
