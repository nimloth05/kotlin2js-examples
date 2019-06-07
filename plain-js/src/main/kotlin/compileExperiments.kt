import kotlin.browser.document

fun experiments() {
  val element = document.createElement("span")
  val element2 = document.createElement("span")

  if (element == element2) {
    println("equals")
  }

  val x: Int = 10
//  println(x::class)
  val y: Double = 12.3
  println(y::class)

  val jsArray = js("['sandro']") as Array<Int>

  println(jsArray::class)
  jsArray[0] = 10

  printNumber(x)
  printNumber(y)

  val person = Person("Gandalf")
  val hasEquals = js("typeof person.equals === 'function'")
  println("Has Equals function $hasEquals")
  println("call equals" + person.equals("Hallo"))

//  when (x) {
//    is Double -> println("is Double")
//    is Int -> println("is int")
//    else -> println("IDK")
//  }
}

private fun printNumber(value: Int) {
  println("Int value")
}

private fun printNumber(value: Double) {
  println("Double value")
}

open class FakeEquals {
  override fun equals(other: Any?): Boolean {
    return false
  }
}

class Person(val x: String) {
}

//Wenn eine Klasse keine equals Funktion besitzt, und auch eine parent Klasse keine equals Funktion besitzt, wird das generische equals aufgerufen
//
