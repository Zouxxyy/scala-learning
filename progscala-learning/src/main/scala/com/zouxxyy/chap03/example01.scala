package com.zouxxyy.chap03

import scala.collection.mutable
import scala.io.Source

object example01 {

  def main(args: Array[String]): Unit = {

    // val : value 不可变
    // var : variable 可变

    println("数组测试：")

    val numNames = Array("zero", "one", "two")  // 实际上是 Array.apply("zero", "one", "two")

    numNames.foreach(print)

    numNames(0) = "new" // 数组里的元素是可变的，相当于 numNames.update(0, "new")

    println()

    for (i <- 0 to 2)
      print(numNames(i))


    println("\n\nlist测试：")

    val oneTwoThree = List(1, 2, 3) // list不可变
    val threeFour = List(4, 5)

    println(oneTwoThree ::: threeFour) // list拼接

    println(4 :: oneTwoThree) // 以：结尾的是右的对象的操作符，相当于 oneTwoThree.::(4)

    println(1 :: 2 :: 3 :: Nil) // 初始化list的另一种方式

    // list添加元素一般是用::在头添加，再转置；或者和Java的String一样，用个Buffer。  我服了

    println("\ntuple测试：")

    val pair = (99, "zzz") // 元组 可以容纳不同类型的元素
    println(pair)
    println(pair._1 + " 和 " + pair._2) // 可以发现这里是从1开始的。   我服了

    // 书上写目前 tuple 只支持最多22个元素 ～～

    println("\nset测试：")

    var jetSet = Set("Boeing", "Airbus") // 默认是不可变的set
    jetSet += "Lear" // 实际上是 jetSet = jetSet +  "Lear" 产生一个新的set
    println(jetSet.contains("Lear"))

    val movieSet = mutable.Set("Hitch", "Poltergeist")
    movieSet += "Shrek" //   这是可变set的方法 def +=(elem: A): this.type
    println(movieSet)

    // 是不是觉得 var val 傻傻分不清呢 ？
    // 不可变的set用的是var，因为后面要赋值新set。可变的set用的是val，但里面的元素可以改变。  在下佩服

    println("\nmap测试：")

    val intToString = mutable.Map[Int, String]() // 可变的map
    intToString += (1 -> "zzz") // 和set同理
    intToString += (2 -> "xxx")
    intToString += (3 -> "yyy")
    println(intToString(2))

    println(1 -> "zzz") // 1 -> "zzz" 实际上是 1.->("zzz") ，返回一个tuple。  我服了

    println("\n初始函数式编程风格：")

    // 书上说向函数式编程风格转变的方向是： 尽可能不用 var

    def printArgs(args: Array[String]) : Unit = {
      args.foreach(println)
    }

    printArgs(numNames) // 有副作用，因为它的返回值是Unit

    def formatArgs(args : Array[String]) = args.mkString("\n") // 升级版，没副作用

    println(formatArgs(numNames))

    println("\n从文件读取文本行：")

    // 计算所代表数字的宽度
    def withOfLength(s: String) = s.length.toString.length

    // 可以发现咱们scala打开文件不用写异常～
    // 而且这里貌似只能写全路径
    val textSource = Source.fromFile("/Users/zxy/IdeaProjects/scala-learning/progscala-learning/data/input/word.txt")
    val lines = textSource.getLines().toList
    textSource.close()

    // 计算最长的一行
    val longestLine = lines.reduceLeft(
      (a, b) => if (a.length > b.length) a else b
    )

    val maxWidth: Int = withOfLength(longestLine)

    for (line <- lines) {
      val numSpaces = maxWidth - withOfLength(line)
      println(" " * numSpaces + line.length + " | " + line )
    }

  }
}


/*
数组测试：
zeroonetwo
newonetwo

list测试：
List(1, 2, 3, 4, 5)
List(4, 1, 2, 3)
List(1, 2, 3)

tuple测试：
(99,zzz)
99 和 zzz

set测试：
true
Set(Poltergeist, Shrek, Hitch)

map测试：
xxx
(1,zzz)

初始函数式编程风格：
new
one
two
new
one
two

从文件读取文本行：
 9 | zxy apple
 3 | dog
 3 | pig
14 | cat fish apple
 7 | dog dog

 */