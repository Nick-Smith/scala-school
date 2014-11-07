package school.wk3

object Animals {

  // Let's create some Animals!

  object Kitty { // the cat
    def message = "I go Meow"
  }

  object Fido { // the dog
    def message = "I go Woof"
  }

  object Tweety { // the canary
    def message = "I go tweet tweet"
  }

  // We can generalize this behaviour into a class Animal:
  class Animal(call: String){ // Note Animal has a constructor which takes the String parameter 'call'.
    def message = "I go " + call
  }

  // Now we can create animal instances using the 'new' keyword to invoke Animal's constructor:
  val kitty = new Animal("Meow")
  println(kitty.message)

  // Or we can use inheritance to create objects or classes that extend Animal:
  // A type inherits all of the behaviour of it's parents.
  // A type can only have one parent class (but it may inherit other types as we'll see later).
  // You cannot inherit from an object.
  // In this case Dog is the child and Animal is it's parent.
  // Dog has inherited the method 'message' from Animal.
  // It must also call the constructor of Animal.
  class Dog extends Animal("Woof")
  val fido = new Dog()
  println(fido.message)

  // Similarly Porky the pig inherits the method 'message' from Animal and must call Animal's constructor.
  object Porky extends Animal("Oink!")
  println(Porky.message)

  // Companion Objects
  // You can create an object with the same name as a class. When you do so, this object is called the companion of the class.
  // Such objects are commonly used to provide alternative constructors for their companion class.
  object Animal{

    def apply(call: String): Animal = new Animal(call)

    // Companion objects often have useful general-purpose instances of their companion class
    val florence = Animal("Moo") // Note we no longer need the 'new' keyword
  }


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
  abstract class Bird(call: String) extends Animal(call){
    val flightSpeed: Int // Unimplemented (abstract) val
    def plumageColour: String // Unimplemented (abstract) method

    def flyMessage = s"I fly at $flightSpeed mpg"

    def feathers = s"My feathers are " + plumageColour
  }

  // Now we can create a class that extends Bird.
  // We need to fill in the missing gaps (flightSpeed and plumageColour) but the rest of the behaviour is inherited
  class Canary extends Bird("tweet tweet"){
    val flightSpeed = 30
    val plumageColour = "yellow" // Note we can turn a def into a val here!
  }
  val tweety = new Canary()
  println(tweety.flyMessage)
  println(tweety.feathers)
  println(tweety.message) // Note tweety still inherits the 'message' method from Animal since Canary < Bird < Animal.

  // Traits are another abstract type.
  // Traits do not have constructors.
  // Traits are more flexible than abstract classes since a class can extend multiple traits using the keyword 'with'.
  // Traits can only extend other traits.
  trait Swimmer {
    val swimSpeed: Int
    def swimMessage = s"I can swim $swimSpeed mph"
  }

  trait Jumper {
    def jumpDistance: Int
    def jumpMessage = s"I can jump $jumpDistance feet"
  }

  object Frog extends Swimmer with Jumper { //Frogs can jump and Swim!
    val jumpDistance = 5
    val swimSpeed = 10
  }
  println(Frog.jumpMessage)
  println(Frog.swimMessage)

  // But wait! WHAT ABOUT THE PENGUIN????!
  // Penguins are birds that can swim but can't fly!
  // Sure we could set the flySpeed to 0 but that still gives an unhelpful message.

  // Solution - override the flyMessage method!
  class Penguin extends Bird("...") with Swimmer{
    val swimSpeed = 20
    val flightSpeed = 0
    val plumageColour = "black and white"
    override def flyMessage = "I can't fly!"
  }

  // Anonymous classes
  // Sometimes you just want an instance of an abstract class or trait without going to the effort of explicitly making one.
  // Scala lets you do this by creating an anonymous class with the 'new' keyword.
  val mrFish = new Swimmer{
    val swimSpeed = 30
  }

  val duck = new Bird("quack") with Swimmer{
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
  class RationalNum(n: Int, d: Int){ // n and d are only accessible within the class
    val numerator = n // Create an accessor for numerator
    val denominator = d // Create an accessor for denominator
  }
  val half = new RationalNum(1, 2)
  val halfFloat = half.numerator.toFloat / half.denominator.toFloat

  // Scala offers a simpler way to achieve this with Properties.
  class RationalVals(val numerator: Int, val denominator: Int) // the val keyword here makes the constructor arg publicly visible
  val third = new RationalVals(1, 3)
  val thirdFloat = third.numerator.toFloat / third.denominator.toFloat

  // Case classes are a special type of class which give you a few things for free:
  // - all constructor params are automatically made into val properties
  // - A hashcode and equals method are automatically generated which compare all properties.
  // - A toString() is generated which includes all properties
  // - A special constructor method is created which doesn't require the 'new' keyword
  case class Rational(numerator: Int, denominator: Int)

  val twoThirds = Rational(2, 3)//Special constructor
  val twoThirdsFloat = twoThirds.numerator.toFloat / twoThirds.denominator.toFloat

}

// Guidelines
// - In general you should try to use traits, objects and case classes in preference to classes and abstract classes.
// - Traits are more extensible than other types so try to always put shared behaviour in them.
// - Case classes are an excellent way of storing structured data and give you a lot of useful functionality for free.
// - Any time you have a general-purpose function that doesn't belong on a method put it in an object.
// - Avoid using objects for anything with side effects(e.g. any type of IO or state), they are not extensible so they are very hard to test.

// Exercises
//
// 1) Write a new function in Animal that prints out the animal's message.
//    Will this function work for all of the objects and vals specified in Animals?
//
// 2) How would you add the methods + - * and / to all of the Rational classes and objects above?
//    Your solution should only require one definition of each method, not one definition per class.
//
// 2)
