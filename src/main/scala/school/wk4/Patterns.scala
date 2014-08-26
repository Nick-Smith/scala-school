package school.wk4

import scala.annotation.tailrec
import scala.util.matching.Regex

object Patterns {

  // Pattern matching is a very powerful feature of Scala.
  // First, let's see a simple example, matching on a string value:

  def fooBar(s: String): String ={
    s match {
      case "foo" => "bar" // If s is 'foo' return 'bar'
      case "bar" => "foo"// If s is 'bar' return 'foo'
      case _ => "What's this???" // You can use _ as a default if nothing else matches.
    }
  }

  /**
   * Create a function to return the day of the week based on an index <code>i</code>.
   * Note i is 0-indexed so 0 -> Sunday and 6 -> Saturday.
   * @param i
   * @return
   */
  def dayOfWeek(i: Int): String = ???

  // In addition to matching exact values, we can also match on types:

  /**
   * Returns a String message indicating the type of <code>a</code>
   * @param a
   * @return
   */
  def whatAmI(a: Any): String = a match {
    case s: String => "I am a String!" // Matches a String
    case i: Int => "I am an Int!"
    case b: Boolean => "I am a Boolean!"
    case any: Any => "I could be anything!" // Note the cases match in order so Strings, Ints and Booleans won't get here!
  }

  /**
   * Create a function that converts the param into a Boolean without calling .toBoolean on <code>a</code>.
   * This should handle Booleans, Ints, Doubles and the Strings 'true' and 'false'.
   * It should default to <code>false</code>.
   * @param a
   */
  def toBoolean(a: Any): Boolean = ???

  // You can add conditions to your pattern matching too:
  def isEvenInt(a: Any): Boolean = a match {
    case i: Int if i % 2 == 0 => true // Note you don't need (brackets) around the if statement here!
    case _ => false
  }

  // You can reference the 'matched' values in the 'body' (right hand side) of the case statement:
  def toIntOrZero(a: Any): Int = a match {
    case i: Int => i
    case _ => 0
  }

  // You can match on tuples!

  import Tuples._

  /**
   * Returns a String for pairs in a nice 'a -> b' format.
   * @param t the value to convert to String
   * @return
   */
  def printPair(t: Any): String = t match {
    case (l, r) => s"$l -> $r"
    case _ => t.toString
  }

  /**
   * Write a function that returns true if <code>p</code> is a pair whose values are equal. Otherwise return false.
   * @param p the value to be considered
   * @return
   */
  def pairMatches(p: Any): Boolean = ???

  // You can also match on case classes:

  case class Person(name: String, age: Int)

  def schoolRole(p: Person): String = p match {
    case Person("Nick", _) => "Teacher"
    case Person("Robin", _) => "Student"
    case Person("Stephan", _) => "Student"
    case Person("Stuart", _) => "Student"
    case _ => "You're in the wrong class!"
  }

  /** Write a function that returns true if the person is over 18.
   *
   * @param p
   * @return
  */
  def isAdult(p: Person): Boolean = ???

  // You can also pattern match on regular expressions!

  val MaxIntPattern: Regex = """inf""".r // Note triple quotes """ creates a RichString and .r returns a Regex.
  val IntPattern: Regex = """^\d+$""".r // Match a string of digits

  def parseInt(s: String): Int = s match {
    case MaxIntPattern(i) => Integer.MAX_VALUE // Note, the params MaxIntPattern and IntPattern must be capitalised!
    case IntPattern(i) => Integer.parseInt(s)
    case _ => throw new NumberFormatException()
  }

  // You can pattern match on Lists, very useful for recursion:

  /**
   * Finds the last element in the list
   * @param l the list to get the last element of.
   * @tparam A the type of the list.
   * @return
   */
  @tailrec // This does compile-time check that function is tail recursive.
  def last[A](l: List[A]): A = l match {
    case Nil => throw new IndexOutOfBoundsException("Called last on Nil!")
    case head :: Nil => head // Matches on a list with 1 element
    case head :: tail => last(tail) // Tail recursion
  }

  /**
   * Write a function to get the penultimate element from the list.
   * @param l
   * @tparam A
   * @return
   */
  def penultimate[A](l: List[A]): A = ???
}
