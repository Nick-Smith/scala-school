package school.wk1

object GettingStarted {

  /*
   * We'll cover:
   * - using the REPL
   * - basic types
   * - declaring variables
   * - truthiness
   * - Operators, methods and functions
   */

  // REPL
  //
  // To user the REPL type 'scala' in a command terminal.

  // BASIC TYPES
  //
  // Some commonly used types are:
  // Boolean - a true or false value, e.g. true
  // Int - Whole numbers, e.g. 1
  // Long - bigger whole numbers, e.g. 1l
  // Double -  a big floating point number, e.g. 1.0
  // Float - a small floating point number, e.g. 1.0f
  // Char - a single character, e.g. 'a'
  // String - a sequence of characters, e.g. "Hello World!"

  // VALUES AND VARIABLES
  //
  // You can declare values using the 'val' keyword
  val one = 1
  val nil = 0.0
  val a = 'a'

  // You cannot change the value of a val, it is fixed on creation.
  // one = 2 will not compile.
  // If you really want a reassignable variable use the 'var' keyword instead (but you almost never need this!)
  var changeMe = 1
  changeMe = 2

  // In the previous examples Scala inferred the type of the val or var.
  // E.g. one has a type of Int, nil has a type of double
  // You can explicitly specify the type of a val or var using : as follows:
  val b: Char = 'b' // this is a Char
  val s: String = "Hellow World!" // this is a String

  // TRUTHINESS
  //
  // What is the definition of truth?
  // By which we mean, which of these expresssions are true...

  true // ? true
  false // ? false
  true == true // ? true - use == to compare two values
  false == false // ? true
  true == false // ? false
  true != false // ? true - use != to return the opposite of ==

  1 == 1 // ?  true - compare two Int values
  1 == 0 // ?  false
  1 == "1" // ? false - an Int and a String are not the same!
  1 == '1' // ? false - ditto Int and Char
  1 == 1.0 // ? true - Scala knows that Int and Double are both Numbers so it can compare them!

  // OPERATORS, METHODS AND FUNCTIONS
  //
  // You can call methods or operators on a value or variable using the 'dot(.) notation' as follows:

  1 + 1 // Add two integers together.

  val oneInt = 1
  val twoInt = 2
  oneInt + twoInt // Add two Integers together

  // Note that '+' is an operator on Int which takes one other parameter (the number to be added).
  // Int has lots of other parameters, e.g. * / - % etc.
  // You can also use dot(.) notation:

  1.+(1) // same as 1 + 1. The '.' indicates that + is a member of 1 and the () indicate that + takes a parameter.

  // Note that methods and operators are the same thing in Scala.
  // Another example:
  1.max(5) // Method returns the maximum of 1 or 5, in this case the result is 5

  // Can also be written:
  1 max 5

  // In general method calls can be made using dot notation or space notation as above.

  // You can also define a function to add Ints together as below:
  def add(i: Int, j: Int): Int = i + j

  // The def keyword indicates you are making a new function.
  // The parameters are provided in a comma-separated list in () parentheses
  // The return type is provided after the parameter list, preceded by a :.
  // Finally, the body of the function is provided after =.
  // You can also optionally add {} braces to provide a scope.

  // To call the function:
  add( 1, 2) // Adds the Ints 1 and 2




}
