package school.wk6

object Errors {

  // In Scala there are several different ways to handle errors.
  // The most basic is to throw and catch Exceptions, similar to those used in Java.
  // All Exceptions in Scala are Runtime exceptions, i.e. they are not declared on method signatures.
  // Since Exceptions are side-effects functions that throw them are impure (non-functional)!

  // There are lots of different types of Exception.
  // You can even create your own types by extending Runtime Exception.
  class MyException(message: String) extends RuntimeException(message)

  /**
   * To throw an Exception create a new instance of it using the 'new' keyword and then 'throw' it.
   * When you throw an exception it interrupts the flow, immediately exiting the function.
   */
  def headException[A](l: Seq[A]): A = {
    if(l.isEmpty)
      throw new MyException("Called head on Empty List!")
    l.head
  }

  /**
   * You need to use try/catch blocks to handle exceptions.
   * The code that throws the exception should be inside the try block.
   * The catch block uses pattern matching to match on the exception to be handled.
   */
  def headString(l: Seq[_]): String = {
    try {
      headException(l).toString
    }
    catch {
      case e: MyException => "Error!"
    }
  }

  // There is also an optional 'finally' block which is always called whether an exception is thrown or not.
  // This can be useful if you have resources that need to be released, e.g. DB connections.

  class Connection {
    var isOpen = true

    def read(): String = throw new MyException("Boom!")
    def close() { isOpen = false }
  }

  // Reads from the connection and guarantees the connection is closed even if an exception is thrown.
  def safeRead(c: Connection): String = {
    try {
      val msg = c.read()
      c.close()
      msg
    }
    catch {
      case e: Exception => "Error reading from connection!"
    }
    finally{
      c.close()
    }
  }
}