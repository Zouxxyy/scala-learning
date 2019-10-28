package com.zouxxyy.chap04

object Summer {

  def main(args: Array[String]): Unit = {

    val array = Array("zero", "one", "two")

    for (a <- array)
      println(a + ": " + ChecksumAccumulator.calculate(a))

  }
}

/*
zero: -192
one: -66
two: -90
 */
