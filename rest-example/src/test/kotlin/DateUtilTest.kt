import kotlin.test.Test
import kotlin.test.assertEquals

class DateUtilTest {

  @Test
  fun testFormat() {
    val actual = formatDate("2019-10-04")
    val expected = "2019.10.04"
    assertEquals(expected, actual)
  }
}