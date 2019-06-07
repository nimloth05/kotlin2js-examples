package util

import kotlin.test.Test
import kotlin.test.assertEquals


class DateParserTest {

  @Test
  fun testReplaceDashWithDot() {
    val actual = formatDate("2019-10-04")
    val expected = "2019.10.04"
    assertEquals(expected, actual)
  }
}