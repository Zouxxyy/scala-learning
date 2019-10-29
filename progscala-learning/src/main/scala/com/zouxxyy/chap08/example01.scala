package com.zouxxyy.chap08

object example01 {

  def main(args: Array[String]): Unit = {

    val numbers = List(-11, -10, 0, 5, 10)

    println("函数字面量：")
    val increase = (x: Int) => {
      println(x + 1)
    }

    println(increase)
    println(increase(10))

    numbers.foreach(increase)

    println("\n占位符：")
    println(numbers.filter(_ > 0))

    println("\n部分应用函数：")
    def sum(a: Int, b: Int, c: Int) = a + b + c
    val b = sum(1, _: Int, 3)  // 函数自面量
    println(b(2))

    println("\n闭包：")

    var more = 1
    val addMore = (x: Int) => x + more // 函数自面量中包含一个自由变量
    println(addMore(10))
    more = 10
    println(addMore(10)) // 闭包能看到自由变量的改变

    numbers.foreach(more += _)
    println(more) // 闭包对自由变量的修改在外面也会生效


    println("\n特殊的函数调用形式：")

    // 重复参数
    def echo(args: String*): Unit = args.foreach(println)

    echo("aaa", "bbb", "ccc")
    echo(Array("aaa", "bbb", "ccc"): _*)

    // 带名字的参数 和 缺省参数
    def speed(distance: Float, time: Float = 5): Float = distance / time
    println(speed(time = 10, distance = 100))
    println(speed(distance = 100))


    // 尾递归
    // 只有在结尾直接递归调用自己 并且 没有 其它中间环节时 生效
    @scala.annotation.tailrec
    def bang(x: Int): Int =
      if(x == 0 ) throw new Exception("bang!")
      else bang(x -1)

    bang(3)

  }
}

/*
函数字面量：
<function1>
11
()
-10
-9
1
6
11

占位符：
List(5, 10)

部分应用函数：
6

闭包：
11
20
4

特殊的函数调用形式：
aaa
bbb
ccc
aaa
bbb
ccc
10.0
20.0
Exception in thread "main" java.lang.Exception: bang!
	at com.zouxxyy.chap08.example01$.bang$1(example01.scala:57)
	at com.zouxxyy.chap08.example01$.main(example01.scala:60)
	at com.zouxxyy.chap08.example01.main(example01.scala)

Process finished with exit code 1
 */