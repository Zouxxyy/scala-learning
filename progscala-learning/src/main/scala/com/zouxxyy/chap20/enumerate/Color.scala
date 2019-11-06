package com.zouxxyy.chap20.enumerate

/**
 * 枚举测试
 */

object Color extends Enumeration {


  val Red: Value = Value("Red")
  val Blue: Value = Value("Blue")
  val Green: Value = Value("Green")
  def main(args: Array[String]): Unit = {


    for (c <- Color.values)
      println(c + " ")

    println(Color.Blue)

    println(Color.Red.id)

    println(Color(0))

  }
}

/*
Red
Blue
Green
Blue
0
Red
 */