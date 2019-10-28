package com.zouxxyy.chap05

object example01 {

  def main(args: Array[String]): Unit = {

    println(
      """|第五章
         |基础类型和操作
         |""".stripMargin)


    println("符号自面量：")

    val a: Symbol = 'aSymbol

    println(a + "   " + a.name)


    println("\n字符串插值：")

    val name = "reader"

    println(s"Hello, $name, today is 2019 Oct ${3  * 4}!")
    println(f"Pi is approximately ${math.Pi}%.8f")


    println("\n操作符即方法：")

    // 任何方法都可以是操作符！！反过来，操作符也就是方法

    // 中缀操作符： 对象 + 操作符 + 参数
    println(name indexOf "e") // 相当与 name.indexOf("e")
    println(name indexOf ("e", 2)) // 相当于 name.indexOf("e", 2)

    // 前缀操作符： 操作符 + 对象 (只能是 + - = ~)
    println(- 9) // 相当与 9.unary_-
    println(9.unary_-)

    // 后缀操作符： 对象 + 操作符 (用于 不接受参数 和 方法没有副作用时)
    println("Hello World!" toLowerCase)

    println("\n对象相等性：")

    // java 的 == 比较的是 引用相等性； scala 的 == 是调用 equals
    println("zzz" == "zzz")

  }
}


/*
第五章
基础类型和操作

符号自面量：
'aSymbol   aSymbol

字符串插值：
Hello, reader, today is 2019 Oct 12!
Pi is approximately 3.14159265

操作符即方法：
1
4
-9
-9
hello world!

对象相等性：
true
 */