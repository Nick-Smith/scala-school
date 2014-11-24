package school.wk3

object Animals {

  // Let's create some Animals!

  object Kitty {
    // the cat
    def behaviour(isPredator: Boolean): String = if (isPredator) "Get lost!" else "Dinner Time!"
  }

  object Tweety {
    // the canary
    def behaviour(isPredator: Boolean): String = if (isPredator) "Run Away!" else "Hey There!"
  }

  // objects are singletons, there is only ever one instance of each object.

  // We can generalize this behaviour into a class Animal:
  class Animal(predator: Boolean) {

    val isPredator = predator
    // Note Animal has a constructor which takes the Boolean parameter 'isPredator'.
    def behaviour(animal: Animal): String = {
      if (this.isPredator)
        if (animal.isPredator) "Get lost!" else "Dinner Time!"
      else
      if (animal.isPredator) "Run Away!" else "Hey There!"
    }
  }

  // Now we can create animal instances using the 'new' keyword to invoke Animal's constructor:
  val kittyTheCat = new Animal(true)

  // Or we can use inheritance to create objects or classes that extend Animal:
  // A type inherits all of the behaviour of it's parents.
  // A type can only have one parent class (but it may inherit other types as we'll see later).
  // You cannot inherit from an object.
  // In this case Dog is the child and Animal is it's parent.
  // Dog has inherited the method 'behaviour' from Animal.
  // It must also call the constructor of Animal with the parameter 'isPredator'.
  class Dog extends Animal(true)

  val fido = new Dog()

  // We pass the isPredator constructor param from the Fish constructor to the constructor of Animal.
  class Fish(isPredator: Boolean) extends Animal(isPredator){

    // We can define alternative constructors using the 'this' keyword
    def this() = {
      this(false) // These alternate constructors must ultimately call the primary constructor by invoking 'this'.
    }

  }

  val goldy = new Fish()
  val sharky = new Fish(true)

  // Similarly objects can also inherit behaviour from classes
  object PorkyThePig extends Animal(false)

  // Companion Objects
  // You can create an object with the same name as a class, defined in the same file§. When you do so, this object is called the companion of the class.
  // Such objects are commonly used to provide alternative constructors for their companion class.
  object Animal {

    def apply(isPredator: Boolean): Animal = new Animal(isPredator)

    // Companion objects often have useful general-purpose instances of their companion class
    val florenceTheCow = Animal(false) // Note we no longer need the 'new' keyword
  }

  Animal.florenceTheCow.behaviour(fido)

  // Abstract types
  //
  // Sometimes we want to create types that are not fully defined.
  // They outline some piece of behaviour we want to share across other types (descendants),
  // but they are not well defined enough to be instantiated.

  // Abstract classes are one example of an abstract type.
  // Abstract classes can have constructors, just like classes
  // A type may only extend one abstract class.
  // Let's create the abstract class Bird. There is no such thing as just a Bird, rather there are specific varieties of birds.
  // But all birds share common behaviour that we want to express in this class
  abstract class Bird(isPredator: Boolean) extends Animal(isPredator) {

    // Unimplemented (abstract) val
    val flightSpeed: Int

    // Unimplemented (abstract) method
    def plumageColour: String

    def isFasterThan(speed: Int): Boolean = flightSpeed > speed

    def isHidden(backgroundColour: String): Boolean = backgroundColour == plumageColour
  }

  // Now we can create a class that extends Bird.
  // We need to fill in the missing gaps (flightSpeed and plumageColour) but the rest of the behaviour is inherited
  class Canary extends Bird(false) {
    val flightSpeed = 30
    val plumageColour = "yellow" // Note we can turn a def into a val here!
  }

  val tweety = new Canary()
  tweety.isFasterThan(10)
  tweety.isHidden("Green")
  tweety.behaviour(kittyTheCat)

  // Note tweety still inherits the 'behaviour' method from Animal since Canary < Bird < Animal.

  // Traits are another abstract type.
  // Traits do not have constructors.
  // Traits are more flexible than abstract classes since a class can extend multiple traits using the keyword 'with'.
  // Traits can only extend other traits.
  trait Swimmer {
    val swimSpeed: Int

    def swimsFasterThan(speed: Int): Boolean = swimSpeed > speed
  }

  trait Jumper {
    def jumpDistance: Int

    def jumpsFartherThan(distance: Int): Boolean = jumpDistance > distance
  }

  object Frog extends Swimmer with Jumper {
    //Frogs can jump and Swim!
    val jumpDistance = 5
    val swimSpeed = 10
  }

  Frog.jumpsFartherThan(10)
  Frog.swimsFasterThan(10)

  // But wait! WHAT ABOUT THE PENGUIN????!
  // Penguins are birds that can swim but can't fly!
  // Sure we could set the flySpeed to 0 but that still gives an unhelpful result for isFasterThan.

  // Solution: override the isFasterThan method!
  class Penguin extends Bird(true) with Swimmer {
    val swimSpeed = 20
    val flightSpeed = 0
    val plumageColour = "black and white"

    override def isFasterThan(speed: Int) = swimsFasterThan(speed)
  }

  // Anonymous classes
  // Sometimes you just want an instance of an abstract class or trait without going to the effort of explicitly making one.
  // Scala lets you do this by creating an anonymous class with the 'new' keyword.
  val mrFish = new Swimmer {
    val swimSpeed = 30
  }

  val duck = new Bird(false) with Swimmer {
    val flightSpeed = 50
    val swimSpeed = 10
    val plumageColour = "brown and black"
  }
}

// Properties and Case Classes
object RationalNumbers {

  // Sometimes you want a class that holds properties you can read and possibly change.
  // Ass an example, let's consider rational numbers
  // You could represent a rational number using the class below:
  class RationalNum(n: Int, d: Int) {
    // n and d are only accessible within the class
    val numerator = n

    // Create an accessor for denominator
    val denominator = d // Create an accessor for denominator
  }

  val half = new RationalNum(1, 2)
  val halfFloat = half.numerator.toFloat / half.denominator.toFloat

  // Scala offers a simpler way to achieve this with Properties.
  class RationalVals(val numerator: Int, val denominator: Int)

  // the val keyword here makes the constructor arg publicly visible
  val third = new RationalVals(1, 3)
  val thirdFloat = third.numerator.toFloat / third.denominator.toFloat

  // Note you can also use the var keyword in a constructor to make a property both readable and writeable (but don't!).

  // Case classes are a special type of class which give you a few things for free:
  // - all constructor params are automatically made into val properties
  // - A hashcode and equals method are automatically generated which compare all properties.
  // - A toString() is generated which includes all properties
  // - A special constructor method is created which doesn't require the 'new' keyword
  // - A copy method which allows you to make a copy of the case class with specific modifications.
  case class Rational(numerator: Int, denominator: Int) {

    // We can use the copy method to implement a '*' method which returns a new Rational.
    def *(i: Int): Rational = copy(numerator * i)
  }

  val twoThirds = Rational(2, 3)
  //Special constructor
  val twoThirdsFloat = twoThirds.numerator.toFloat / twoThirds.denominator.toFloat

}

// Guidelines
// - In general you should try to use traits, objects and case classes in preference to classes and abstract classes.
// - Traits are more extensible than other types so try to always put shared behaviour in them.
// - Case classes are an excellent way of storing structured data and give you a lot of useful functionality for free.
// - Any time you have a general-purpose function that doesn't belong on a method put it in an object.
// - Avoid directly referencing objects for anything with side effects(e.g. any type of IO or state), they are not extensible so they are very hard to test.

// Exercises
//
// 1) How would you add a 'toFloat' method to all of the Rational classes above (RationalNum, RationalVal and Rational)?
// Your solution should only require one definition of the toFloat method, not one per class.
//
// 2) How would you implement the operations * and / on Rational, taking another Rational as a parameter?
// Don't worry about normalizing the result, ie.e Rational(1/3) * Rational (3/2) = Rational(3/6)
