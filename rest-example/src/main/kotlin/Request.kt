class RequestFailedException(message: String) : RuntimeException(message)

private fun createPostalCodeSearchUrl(postalCode: String) = "http://api.geonames.org/postalCodeSearchJSON?country=CH&postalcode=$postalCode&maxRows=10&username=sandro_vis"

fun createPostalCodeSearchRequest(postalCode: String): AjaxSettings {
  return jsObject {
    contentType = "text/plain"
    dataType = "json"
    url = createPostalCodeSearchUrl(postalCode)
  }
}

external interface PostalCodeSearchResult {
  val postalCodes: Array<PostalCodeEntry>
}

external interface PostalCodeEntry {
  val adminCode2: String
  val adminCode3: String
  val adminName3: String
  val adminCode1: String
  val adminName2: String
  val lng: String
  val lat: String
  val countryCode: String
  val postalCode: String
  val adminName1: String
  val ISO3166: String
  val placeName: String
}


external interface TimeZoneResult {
  val countryCode: String
  val countryName: String
  val dstOffset: String
  val gmtOffset: String
  val lat: String
  val lng: String
  val rawOffset: String
  val sunrise: String
  val sunset: String
  val time: String
  val timezoneId: String
}

private fun createTimeZoneUrl(lat: String, long: String) = "http://api.geonames.org/timezoneJSON?lat=$lat&lng=$long&username=sandro_vis"

fun createTimeZoneRequest(lat: String, long: String): AjaxSettings {
  return jsObject {
    contentType = "text/plain"
    dataType = "json"
    url = createTimeZoneUrl(lat, long)
  }
}




