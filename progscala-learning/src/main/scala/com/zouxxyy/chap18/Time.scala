package com.zouxxyy.chap18

class Time {

  var h: Int = _  // 默认是 private[this]
  var m: Int = _

  def h_(x: Int): Unit = {
    require(0 <= x && x < 24)
    h = x
  }

  def m_(x: Int): Unit = {
    require(0 <= x && x < 60)
    m = x
  }

  // P366 写的会自动生成 h_ 我模拟了好久没成功。。。
}


object Time {

  def main(args: Array[String]): Unit = {

    val time = new Time()

    println(time.h)

    time.h_(3)

    println(time.h)

  }

}

/*
0
3
 */