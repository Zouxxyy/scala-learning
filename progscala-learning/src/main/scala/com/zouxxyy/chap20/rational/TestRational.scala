package com.zouxxyy.chap20.rational

object TestRational {

  def main(args: Array[String]): Unit = {

    val x = 2

    // {} 在 RationalTrait 初始化后执行
    // 因此在 RationalTrait 中 denomArg 初始化0，不满足 require(denomArg != 0)
    // val rational = new RationalTrait {
    //   val numerArg: Int = 1 * x
    //   val denomArg: Int = 2 * x
    // }
    // Exception in thread "main" java.lang.IllegalArgumentException: requirement failed


    println("******预初始化******")
    // {} 在 RationalTrait 初始化前执行
    val rational1 = new {
      val numerArg: Int = 1 * x
      val denomArg: Int = 2 * x
    } with RationalTrait


    println("\n******惰性初始化******")
    val rational2 = new RationalTrait {
      val numerArg: Int = 1 * x
      val denomArg: Int = 2 * x
    }

    println(rational2)

  }
}

/*
******预初始化******
RationalTrait 初始化成功！

******惰性初始化******
RationalTrait 初始化成功！
denom 初始化成功！
执行 require
1/2
 */
