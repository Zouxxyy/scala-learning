package com.zouxxyy.chap13

abstract class Fruit (val name: String, val color: String)

object Fruits {

  object Apple extends Fruit("apple", "red")
  object Orange extends Fruit("orange", "orange")
  object Pear extends Fruit("pear", "yellowish")
  val menu = List(Apple, Orange, Pear)


  def main(args: Array[String]): Unit = {

    showFruit(Apple) // 调用包对象里的方法

  }
}

/*
apples are red
 */