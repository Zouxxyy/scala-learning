package com.zouxxyy

class Time {

  var h = 12  // 默认是 private[this]
  var m = 0

//  def h_(x: Int): Unit = {
//    require(0 <= x && x < 24)
//    h = x
//  }

  def m_(x: Int): Unit = {
    require(0 <= x && x < 60)
    m = x
  }

}


object Time {

  def main(args: Array[String]): Unit = {

    val time = new Time()

    println(time.h)

    time.h_(3)

    println(time.h)

  }

}