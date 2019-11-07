package com.zouxxyy.chap07

import scala.util.control.Breaks._

/**
 * 第7章：内建的控制结构
 */

object example01 {

  def main(args: Array[String]): Unit = {

    println("函数返回值：")
    def funUnit(): Unit = {}
    def funInt(): Int = {1}

    println(funUnit()) // ()
    println(funInt())  // 1
    println({} == ())

    println("\nfor表达式：")

    val myArray = Array(Array("zzz", "xxx"), Array("zzz", "xxx", "yyy"), Array("zip", "zxy"))
    val animals = Array("apple", "cat", "dog", "pig")


    // for {} yield {}
    val newArray: Array[String] = for {
      array <- myArray if array.length == 2
      i <- array if i contains "z"             // 2重嵌套加过滤
    } yield i                                  // 保存结果到Array中

    for (i <- newArray)
      println("contains z: " + i)

    println("\ntry表达式：")

    def f(): Int = try return 1 finally return 2
    def g(): Int = try 1 finally 2
    println(f())
    println(g())

    println("\nmatch表达式：")

    val a = "zxy"

    a match {
      case "zxy" => println(a + " is me")
      case _ => println("no")
    }

    println("\n没有break 和 continue:")
    @scala.annotation.tailrec
    def searchFrom(i: Int):Int =
      if (i >= animals.length) -1
      else if (animals(i).startsWith("p")) searchFrom(i + 1)
      else if (animals(i).endsWith("g")) i
      else searchFrom(i + 1)

    println("不以p开头，以g结尾的动物是：" + animals(searchFrom(0)))

    // 使用break
    breakable {
      for (animal <- animals) {
        if (animal.endsWith("g"))
        {
          println(animal)
          break
        }
      }
    }

    println("\n变量作用域:")

    val b = 1; // 这里必须的加;
    {
      val b =2
      println(b)
    }
    println(b)


    println("\n乘法表 :")

    def makeRowSeq(row: Int): Seq[String] = {
      for (col <- 1 to 10) yield {
        val prod = (row * col).toString
        val padding = " " * (4 - prod.length)
        padding + prod
      }
    }

    def makeRow(row: Int): String = makeRowSeq(row).mkString  // 一行

    def multiTable(): String = {
      val tableSeq = for (row <- 1 to 10) yield makeRow(row)
      tableSeq.mkString("\n")
    }

    println(multiTable())

  }

}


/*
函数返回值：
()
1
true

for表达式：
contains z: zzz
contains z: zip
contains z: zxy

try表达式：
2
1

match表达式：
zxy is me

没有break 和 continue:
不以p开头，以g结尾的动物是：dog
dog

变量作用域:
2
1

乘法表 :
   1   2   3   4   5   6   7   8   9  10
   2   4   6   8  10  12  14  16  18  20
   3   6   9  12  15  18  21  24  27  30
   4   8  12  16  20  24  28  32  36  40
   5  10  15  20  25  30  35  40  45  50
   6  12  18  24  30  36  42  48  54  60
   7  14  21  28  35  42  49  56  63  70
   8  16  24  32  40  48  56  64  72  80
   9  18  27  36  45  54  63  72  81  90
  10  20  30  40  50  60  70  80  90 100
 */