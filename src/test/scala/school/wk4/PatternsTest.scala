package school.wk4

import org.scalatest.{Matchers, FlatSpec}
import Patterns._

class PatternsTest extends FlatSpec with Matchers {

  "Patterns" should "have a dayOfWeek function" in {
    dayOfWeek(0) should be("Sunday")
    dayOfWeek(3) should be("Wednesday")
    dayOfWeek(5) should be("Friday")
  }

  it should "have a toBoolean function" in {
    toBoolean(5) should be(true)
    toBoolean(0) should be(false)
    toBoolean(-1.1) should be(false)
    toBoolean(1.1) should be(true)
    toBoolean("true") should be(true)
    toBoolean("false") should be(false)
    toBoolean("truelike") should be(false)
  }

  it should "have a pairMatches function" in {
    pairMatches(5) should be(false)
    pairMatches("five") should be(false)
    pairMatches("five" -> 5) should be(false)
    pairMatches(5 -> 5) should be(true)
    pairMatches("five" -> "five") should be(true)
  }

  it should "have a isAdult function" in {
    isAdult(Person("Bob", 25)) should be(true)
    isAdult(Person("Joe", 8)) should be(false)
    isAdult(Person("Joe", 40)) should be(true)
  }

  it should "have a penultimate function" in {
    penultimate(List(1, 2, 3, 4)) should be(3)
    penultimate(List(4, 3, 2, 1)) should be(2)
    try {
      penultimate(Nil)
      fail("Should throw Exception!")
    }
    try {
      penultimate(List(1))
      fail("Should throw Exception!")
    }
  }

}
