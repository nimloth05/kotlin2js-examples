import kotlin.test.Test
import kotlin.test.assertEquals

class TestClass {

  @Test
  fun testFullName() {
    val person = Person("S", "O")
    assertEquals("S O", person.fullName)
  }
}