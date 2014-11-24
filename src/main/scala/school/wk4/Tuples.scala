package school.wk4

object Tuples {

  // Tuples are a built-in data structure in Scala which allow you to group together values of different types
  // into an ordered sequence.
  // Tuples are most commonly used when a function needs to return more than one value.

  // Creating a tuple
  val pair: (Int, String) = (2, "Two")

  val triple: (Int, Double, String) = (3, 3.0, "Three")

  // A simple example

  /**
   * Returns the result and the remainder for an Integer division.
   * @param n the numerator
   * @param d the denominator
   * @return a pair with the result and the remainder
   */
  def divide(n: Int, d: Int): (Int, Int) = {
    val result = n / d
    val remainder = n % d
    (result, remainder)
  }

  /**
   * Calculates the square, cube and 4th power of <code>i</code>
   * @param i the value whose powers will be calculated
   * @return a tuple with the square, cube and 4th power of i.
   */
  def powersOf(i: Int): (Int, Int, Int) = ???

  // To access the values in a tuple use _1, _2, etc.
  // Note that these functions are 1-indexed unlike lists and arrays.

  def oddsAndEvens(l: List[Int]): String = {
    // partition separates the list into 2 new lists, one where the elements satisfy the predicate and one where they do not.
    val results = l partition { i => i % 2 == 0 }
    val evens = results._1
    val odds = results._2
    s"The odds were $odds and the evens were $evens"
  }

  // You can directly assign the components of a tuple to values as seen below
  val (oneInt, oneStr) = pair

  // You can ignore components of the tuple using _
  val (threeInt, _ , threeStr) = triple

  /**
   * Use powersOf to create a String of the format
   * "The powers of 2 are square: 4, cube: 8, 4th power: 16"
   * You should be able to do this in a 2-line method using the val (x, y, z) notation.
   * @param i
   * @return
   */
  def powerMessage(i: Int): String = ???

  // Scala provides a special constructor for creating key-value pairs as these are a very common use-case for tuples:
  val ones = 1 -> "One" //is the same as (1, "One")
  val twos = 2 -> "Two" //is the same as (1, "One")
  val threes = 3 -> "Three" //is the same as (1, "One")

}
