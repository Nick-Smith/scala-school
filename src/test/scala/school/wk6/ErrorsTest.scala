package school.wk6

import org.scalatest.{Matchers, FlatSpec}

class ErrorsTest extends FlatSpec with Matchers {
  import Errors._

  "headException" should "throw an Exception if the list is empty" in {
    headException(List("Foo", "Bar")) should be("Foo")
    try {
      headException(Nil)
      fail("No exception thrown!")
    }
    catch {
      case me: MyException => //pass
      case e => fail("Wrong exception thrown! " + e.getClass.getName)
    }
  }

  "headString" should "return 'Error!' for Nil" in {
    headString(Nil) should be("Error!")
    headString(List("Foo", "Bar")) should be("Foo")
  }

  "safeRead" should "close the connection in the event of an error" in{
    val c = new Connection
    c.isOpen should be(true)
    safeRead(c) should be("Error reading from connection!")
    c.isOpen should be(false)
  }
}
