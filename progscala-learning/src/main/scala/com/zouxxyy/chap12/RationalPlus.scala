package com.zouxxyy.chap12

import com.zouxxyy.chap06.Rational

// 注意继承 有参构造函数父类 的写法
class RationalPlus(n: Int, d: Int) extends Rational(n: Int, d: Int) with Ordered[Rational]{

  override def compare(that: Rational): Int =
    (this.numer * that.denom) - (that.numer * this.denom)

}

object RationalPlus {

  def main(args: Array[String]): Unit = {

    val rational1 = new RationalPlus(1, 2)
    val rational2 = new RationalPlus(2, 3)

    println(rational1 < rational2)
    println(rational1 >= rational2)

  }

}

/*
true
false
 */
