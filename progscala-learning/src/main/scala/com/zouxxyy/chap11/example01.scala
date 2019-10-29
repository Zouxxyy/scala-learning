package com.zouxxyy.chap11

object example01 {

  def main(args: Array[String]): Unit = {

    println(42.hashCode())

    val x = new String("abc")
    val y = new String("abc")
    val m = new Integer(1)
    val n = new Integer(1)

    println(x == y) // true  而 java 是 false
    println(x eq y) // false
    println(x ne y) // true
    println(m == n) // true 而 java 是 false

  }

}

/*
42
true
false
true
true
 */