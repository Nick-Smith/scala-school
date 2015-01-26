package school.wk8

import scala.util.Try

class ForComprehensions {

  // Scala's for syntax is far more powerful than most other languages.
  // It supports the basic for loop functionality as follows:

  /**
   * Returns all the factors of n as a Seq[Int]
   */
  def factors(n: Int): Seq[Int] = {
    var l: List[Int] = Nil
    for (i <- 1 to n) {
      if(n % i == 0)
        l =  i :: l
    }
    l
  }

  // Note that this style of for loop can only really work with side effects, e.g. in this case mutating l2.
  // By introducing the yield keyword we can return a value from the for comprehension,
  // allowing us to write functional for loops:

  def funFactors(n: Int): Seq[Int] = {
    for {
      i <- 1 to n // Map over the sequence of numbers 1 to n
      if n % i ==0 // Filter out numbers that are not factors of n
    } yield i // Return the resulting sequence of numbers
  }

  // What about a more complex example, iterating over multiple values:
  // In Java or C you would need to use nested for loops.

  /**
   * Get the neighbouring co-ordinates for a given cell (game of life).
   */
  def neighbours(x: Int, y: Int): Seq[(Int, Int)] = {
    for{
      i <- -1 to 1
      j <- -1 to 1
      if !(i==0 && j==0)
    } yield (i+x, j+y)
  }

  // What is this doing under the hood?
  // It is essentially just using map, flatMap and filter as follows:
  // - The last <- in a for comprehension is a map.
  // - All other <-  in a for comprehension are flatMaps.
  // - Guard conditions (ifs) are filters.

  // So the above for comprehension would look like this:
  def mapNeighbours(x: Int, y: Int): Seq[(Int, Int)] = {
      -1 to 1 flatMap { i =>
        -1 to 1 filter {j => !(i==0 && j==0)} map { j =>
          (i+x, j+y)
        }
      }
  }

  // We can use for comprehensions on anything that implements map, flatMap and filter.
  // That includes, Lists, Options, Trys and the left and right projections of Either.

  // Let's look at some examples:

  /**
   * Returns an option of a touple containing the head of each list
   */
  def headZip[A, B](l1: List[A], l2: List[B]): Option[(A, B)] = for{
    a <- l1.headOption
    b <- l2.headOption
  } yield (a, b)

  def tryRead(url: String): Try[String] = ???

  /**
   * Compares the output from two connections.
   * The result is wrapped in a Try and will bea  failure if either connection fails.
   */
  def compareStreams(url1: String, url2: String): Try[Boolean] = for {
    s1 <- tryRead(url1)
    s2 <- tryRead(url2)
  } yield s1 == s2

  // Finally, you can pattern match within a for comprehension:

  /**
   * Returns a list containing all the elements that match in both lists.
   */
  def matchingElems[A](l1: List[A], l2: List[A]) = for{
    (a, b) <- l1 zip l2 // zip combines two lists together into a list of tuples - List[(A, A)]
    if a == b
  } yield a
}
