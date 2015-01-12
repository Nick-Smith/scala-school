package school.wk6

import org.scalatest.{Matchers, FlatSpec}

class OptionsTest extends FlatSpec with Matchers {
  import Options._

  "stringopt" should "return an Option[String]" in {
    stringOpt(null) should be(None)
    stringOpt("") should be(None)
    stringOpt("Foo") should be(Some("Foo"))
  }

    "countChars" should "return the number of characters]" in {
      countChars(None) should be(0)
      countChars(Some("")) should be(0)
      countChars(Some("Foo")) should be(3)
    }

    "firstWordLastLetter" should "return the last letter of the first word or _]" in {
      firstWordLastLetter(null) should be('_')
      firstWordLastLetter("") should be('_')
      firstWordLastLetter("FooBar123") should be('_')
      firstWordLastLetter("Foo% Bar") should be('_')

      firstWordLastLetter("FooBar") should be('r')
      firstWordLastLetter("Foo bar") should be('o')
      firstWordLastLetter("Foo 123") should be('o')
    }
}
