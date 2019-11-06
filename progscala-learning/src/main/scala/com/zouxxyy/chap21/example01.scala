package com.zouxxyy.chap21

import com.zouxxyy.chap06.Rational

object example01 {

  def main(args: Array[String]): Unit = {

    println("******隐式转换到一个预期到类型*****")

    implicit def doubleToInt(x: Double): Int = x.toInt

    val i: Int = 3.5

    println(i)

    println("\n*****转换接收端*****")

    // 与新类型互操作
    val oneHalf = new Rational(1, 2)

    implicit def intToRational(x: Int): Rational = new Rational(x, 1)

    println(1 + oneHalf)

    // 模拟新的语法
    println(1 -> "one")

    // 先将 1 转换成 ArrowAssoc，再执行 -> 方法
    println(new ArrowAssoc[Int](1).->("one"))

    // 隐式类
    case class Rectangle(width: Int, height: Int)

    // 隐式类构造方法的参数只能有一个
    implicit class RectangleMaker(width: Int) {
      def x(height: Int) = Rectangle(width, height)
    }

    val myRectangle = 3 x 4

    println(myRectangle)

    println("\n*****隐式参数*****")

    // 对隐式参数使用定制名称
    class PreferredPrompt(val preference: String)
    class PreferredDrink(val preference: String)

    // implicit 是应用到整个参数列表
    object Greeter {
      def greet(name: String)(implicit prompt: PreferredPrompt, drink: PreferredDrink): Unit = {
        println("Welcome " + name)
        println(prompt.preference)
        println("give you " + drink.preference)
      }
    }

    object JoesPrefs {
      implicit val prompt: PreferredPrompt = new PreferredPrompt("Yes, master~")
      implicit val drink: PreferredDrink = new PreferredDrink("red tea!")
    }

    // 引入它
    import JoesPrefs._

    Greeter.greet("Bob")

    println("\n*****上下文界定*****")

    def maxList[T: Ordering](elements: List[T]): T = {
      elements match {
        case List() => throw new IllegalArgumentException("empty list!")
        case List(x) => x
        case x :: rest =>
          val maxRest = maxList(rest)
          if(implicitly[Ordering[T]].gt(x, maxRest)) x
          else maxRest
      }
    }

    println(maxList(List(2, 3, 4, 1)))

    println("\n*****当多个转换可选时*****")

    def printLength(seq: Seq[Int]): Unit = println(seq.length)

    implicit def intToRange(i: Int) = 1 to i

    implicit def intToDigits(i: Any) = List(i)

    // 碰到一样的转换时，会选择更具体的
    printLength(100)

    println("zxy" == "zxy".reverse.reverse)

  }

}

/*
******隐式转换到一个预期到类型*****
3

*****转换接收端*****
3/2
(1,one)
(1,one)
Rectangle(3,4)

*****隐式参数*****
Welcome Bob
Yes, master~
give you red tea!

*****上下文界定*****
4

*****当多个转换可选时*****
100
true
 */