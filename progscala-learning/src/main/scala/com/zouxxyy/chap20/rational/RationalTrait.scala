package com.zouxxyy.chap20.rational

trait RationalTrait {

  // var numerArg: Int  // 抽象成员只声明名称和类型

  val numerArg: Int
  val denomArg: Int
  // require(denomArg != 0)    // 测试惰性初始化时将它注释

  // 惰性初始化
  private lazy val g = {
    require(denomArg != 0)
    println("执行 require")
    gcd(numerArg, denomArg)
  }

  lazy val numer: Int = numerArg / g
  lazy val denom: Int = {println("denom 初始化成功！"); denomArg / g}

  println("RationalTrait 初始化成功！")

  @scala.annotation.tailrec
  private def gcd(a: Int, b:Int): Int = if(b == 0) a else gcd(b, a % b)

  override def toString: String = if (denom == 1) numer.toString else numer + "/" + denom


}
