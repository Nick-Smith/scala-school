package school.wk1

import org.scalatest.{Matchers, FlatSpec}

class GettingStartedSpec extends FlatSpec with Matchers {

  "A Scala programmer" should "be able to execute tests!" in {
    val ICanWriteScala = true

    ICanWriteScala should be (true)
  }
}
