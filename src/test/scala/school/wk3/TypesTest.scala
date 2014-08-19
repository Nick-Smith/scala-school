package school.wk3

import org.scalatest.{Matchers, FlatSpec}
import People._
class TypesTest extends FlatSpec with Matchers {

  "Sue" should "have a name, age and greeting" in{
    Sue.name should be("Sue")
    Sue.age should be(21)
    Sue.greeting should be("Hello my name is Sue, I am 21 years old.")
  }

  "Bob" should "have a name, age and greeting" in{
    bob.name should be("Bob")
    bob.age should be(42)
    bob.greeting should be("Hello my name is Bob, I am 42 years old.")

    bob2.name should be("Bob")
    bob2.age should be(42)
    bob2.greeting should be("Hello my name is Bob, I am 42 years old.")
  }
}
