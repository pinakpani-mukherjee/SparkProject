package Recap

import scala.concurrent.Future
import scala.util.{Failure, Success}

object Recap extends App {
  val bool: Boolean = false
  println(bool)
  val ifExpression = if (2 > 3) "bigger" else "smaller"
  println(ifExpression)

  //instructions(imperative programming) vs expressions(functional programming)

  val theUnit = println("Wassup Unit")

  def myFunc(x: Int) = {
    x + 42
  }

  //OOP
  class Animal

  class Dog extends Animal

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(animal: Animal): Unit = println("Nom Nom Nom! Tasty !!!")
  }

  val Capibara = new Animal
  val croc1 = new Crocodile
  croc1.eat(animal = Capibara)

  //singleton pattern
  object MySingleton

  //companions
  object Carnivore

  //generics
  trait MyList[+A]

  //method notation
  val x = 1 + 2
  val y = 1.+(2)

  //Functional Programming
  //Lambda func
  val incrementer: Int => Int = x => x + 1

  val incremented = incrementer(31)
  println(incremented)

  // map,flatmap,filter (higher order functions, can take in functions as arguments)
  val processedList = List(1, 2, 3).map(incrementer)

  //Pattern Matching
  val unknown: Any = 452
  val ordinal: String = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }
  // try-catch
  try {
    throw new NullPointerException()
  }
  catch {
    case _: NullPointerException => "Some returned value"
    case _ => "Something else"
  }

  //Futures

  import scala.concurrent.ExecutionContext.Implicits.global

  val aFuture = Future {
    //some expensive coputation runs on another thread
    42
  }
  aFuture.onComplete {
    case Success(meaningOfLife) => println(s"Ive found the meaning of life that is $meaningOfLife")
    case Failure(ex) => println(s"I have failed $ex")
  }

  //Partial Functions
  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 34
    case 8 => 22
    case _ => 999
  }

  // Implicits
  // auto injected by the compiler
  def methodWithImplicitArgument(implicit x: Int) = x + 423

  implicit val implicitInt = 67
  val implicitCall = methodWithImplicitArgument

  //implicit conversions

  case class Person(name: String) {
    def greet = println(s"Hi!. My name is $name")
  }

  implicit def fromStringToPerson(name: String) = Person(name)

  "Bob".greet

  // implicit classes
  implicit class Cat(name: String) {
    def mew = println("Bark!")
  }

  "Gyoza".mew
}

