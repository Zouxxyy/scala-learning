package com.zouxxyy.chap20.animal

class Food

abstract class Animal {
  def eat(food: Food)
}

class Crass extends Food

class Cow extends Animal {
  override def eat(food: Grass): Unit = {}
}


