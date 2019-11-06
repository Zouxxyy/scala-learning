package com.zouxxyy.chap20.animal

object AbstractTypeTest {

  class Food

  abstract class Animal {

    // SuitableFood 是 Food 的子类
    type SuitableFood <: Food

    def eat(food: SuitableFood)
  }

  class Grass extends Food {
    override def toString: String = "grass"
  }

  class Cow extends Animal {

    override type SuitableFood = Grass

    override def eat(food: Grass): Unit = println("eat " + food)
  }


  def main(args: Array[String]): Unit = {

    val cow = new Cow

    cow.eat(new Grass)
  }

}

/*
eat grass
 */


