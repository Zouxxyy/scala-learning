package com.zouxxyy.chap06

object Test {

  // 使用隐式转换的效果：
  // x * 2 可以，但 2 * x 不行，因为 2 没有 def * (another: Rational): Rational  这个方法
  // 所以用 隐式转换 将 2 转换成 Rational
  implicit def intToRational(x: Int): Rational = new Rational(x)

  def main(args: Array[String]): Unit = {

    val x = new Rational(3, 9)

    val y = new Rational(4, 7)

    println("toString 测试：")
    println("x = " + x)
    println("y = " + y)

    println("\nmax 测试：")
    println("max(x, y) =  " + (x max y))

    println("\n+ - * / 测试：")
    println("x + y =  " + (x + y))
    println("x - y =  " + (x - y))
    println("x * y =  " + (x * y))
    println("x / y =  " + (x / y))

    println("\n重载 测试：")
    println("y * -2 = " + (y * -2))

    println("\n隐式转换 测试：")
    println("-2 * y = " + (-2 * y))

  }
}


/*
toString 测试：
x = 1/3
y = 4/7

max 测试：
max(x, y) =  4/7

+ - * / 测试：
x + y =  19/21
x - y =  -5/21
x * y =  4/21
x / y =  7/12

重载 测试：
y * -2 = -8/7

隐式转换 测试：
-2 * y = -8/7
 */
