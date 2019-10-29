package com.zouxxyy.chap09

import java.io.{File, PrintWriter}
import java.util.Date

object example01 {

  private def fileHere: Array[File] = new File("src/main/scala/com/zouxxyy/chap04").listFiles()

  // 传入参数是函数字面量，它的类型是 String => Boolean
  private def filesMatching(matcher: String => Boolean) =
    for (file <- fileHere if matcher(file.getName))
      yield file

  def main(args: Array[String]): Unit = {

    println("减少重复代码：")

    def filesEnding(query: String): Array[File] = filesMatching(_.endsWith(query))
    def filesContaining(query: String): Array[File] = filesMatching(_.contains(query))
    def filesRegex(query: String): Array[File] = filesMatching(_.matches(query))

    println(filesEnding("scala").mkString(" "))

    println("\n简化调用方法代码：")

    def containsNeg(nums: List[Int]): Boolean = nums.exists(_ < 0)

    println(containsNeg(List(0, 1, 2, -3)))

    println("\n柯里化：")
    def curriedSum(x: Int)(y: Int) = x + y

    val twoPlus: Int => Int = curriedSum(2)

    println(twoPlus(2))

    println("\n编写新的控制结构：")

    println{"只有单个入参才能用花括号"}

    def withPrintWriter(file :File)(op: PrintWriter => Unit): Unit = {
      val  writer = new PrintWriter(file)
      try {
        op(writer)
      } finally
        writer.close()
    }

    val file = new File("data/input/empty.txt")

    // 因为只有单个入参才能用花括号，所以用柯里化将参数分开，以此用{}来包裹函数。作者觉得这样更"好看"。     我服了
    withPrintWriter(file){ writer =>
      {
        writer.println(new Date())   // 向文件中写入当前日期
        println("写入成功")
      }
    }


    println("\n传名参数：")

    // () => 5 > 3 对应 () => Boolean     5 > 3  对应 => Boolean
    var assertionsEnabled = true
    def byNameAssert(predicate: => Boolean): Unit =
      if (assertionsEnabled && !predicate)
        throw new AssertionError

    // 用法
    byNameAssert(5 > 3)

    // 对比
    def boolAssert(predicate: Boolean): Unit =
      if (assertionsEnabled && !predicate)
        throw new AssertionError

    assertionsEnabled = false

    byNameAssert(1 / 0 == 0)

    println("byNameAssert 没有异常")

    // 先执行 1 / 0 == 0，所以有异常
    boolAssert(1 / 0 == 0)


    // scala 作者怕是个强迫症患者

  }
}

/*
减少重复代码：
src/main/scala/com/zouxxyy/chap04/Summer.scala src/main/scala/com/zouxxyy/chap04/AppSummer.scala src/main/scala/com/zouxxyy/chap04/ChecksumAccumulator.scala

简化调用方法代码：
true

柯里化：
4

编写新的控制结构：
只有单个入参才能用花括号
写入成功

传名参数：
byNameAssert 没有异常
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at com.zouxxyy.chap09.example01$.main(example01.scala:84)
	at com.zouxxyy.chap09.example01.main(example01.scala)
 */