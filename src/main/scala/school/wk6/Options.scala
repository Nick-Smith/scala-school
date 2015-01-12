package school.wk6

object Options {

  // Option is a type that allows you to have Some or None of something.
  // To create an Option use the constructors Option() or Some()
  // None is a singleton object.

  val some1: Option[Int] = Some(1)
  val someStr: Option[String] = Option("Hello")
  val none = Option(null) // This is the best thing to do with a null!

  // Use an Option any time you might not be able to return a value (e.g. because of an exception).
  def headOption[A](l: Seq[A]): Option[A] = {
    try {
      val head = Errors.headException(l)
      Some(head)
    }
    catch {
      case e: Throwable => None
    }
  }

  /**
   * Write a function to return an Option of a String.
   * Returns None if s is null or an empty string, otherwise returns Some(s).
   * @return
   */
  def stringOpt(s: String): Option[String] = ???

  // You can access the value of an Option using methods like get or getOrElse
  // Option.get returns the value or throws an exception. Best avoided!
  // Option.getOrElse returns the value or executes the function you pass it.

  /**
   * @return returns the first string in the list or an empty string if none exists.
   */
  def headString(l: List[String]): String = {
    headOption(l) getOrElse ""
  }

  // You can also pattern match on Option as Some and None are case classes.
  def asString(opt: Option[_]): String = opt match {
    case Some(value) => value.toString
    case None => "None"
  }

  /**
   * Implement this function to count the number of characters in the String contained in opt. Return 0 if opt is None.
   * @return
   */
  def countChars(opt: Option[String]): Int = ???

  // You can think of an Option as a collection of size 1 or 0.
  // It has methods like map, flatMap, filter and isEmpty, just like List.
  // You can do a lot by combining map, filter and getOrElse.
  // Generally it makes sense to pass around Options and delay getting the value until as lat as possible

  def evenHeadString(l: List[Int]): String = {
    val evenOpt = headOption(l) filter {
      _ % 2 == 0
    } //Filter out odd numbers
    val stringOpt = evenOpt map {
        _.toString
      } // Convert to Option[String]
    stringOpt getOrElse "Other"
  }

  // In general filter/map/getOrElse is cleaner and more succinct than pattern matching on Options.
  // E.g. you could implement the asString function in a single line: opt map {_.toString} getOrElse "None"

  /**
   * Write a function that returns the last character of the first word of the string s.
   * Note that s can be null. and if so your function should handle it gracefully
   * Only alphabet characters (a-z or A-z) should be accepted.
   * If no character is matched then the function should return the underscore character '_'
   *
   * Hint: Use String.split(" ") to split strings into words (returns a Seq[String])
   * Hint: Use stringOpt, map and flatMap
   * @param s
   * @return
   */
  def firstWordLastLetter(s: String): Char = ???
}
