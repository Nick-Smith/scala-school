package school.wk4

import Tuples._
import org.scalatest.{Matchers, FlatSpec}

class TuplesTest extends FlatSpec with Matchers{

  "Tuples" should "have a powersOf function" in {
    powersOf(2) should be((4, 8, 16))
    powersOf(3) should be((9, 27, 81))
  }

  it should "have a powerMessage function" in {
    powerMessage(2) should be("The powers of 2 are square: 4, cube: 8, 4th power: 16")
    powerMessage(3) should be("The powers of 2 are square: 9, cube: 27, 4th power: 81")
  }


}
