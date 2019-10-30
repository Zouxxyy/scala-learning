package com.zouxxyy.chap15

// 模式匹配
object example01 {

  def main(args: Array[String]): Unit = {

    println("******常量模式******")
    def describe(x: Any) = x match {
      case 5 => "five"
      case true => "truth"
      case "hello" => "hi!"
      case Nil => "the empty list"
      case _ => "something else"
    }

    println(describe(Nil))

    println("\n******变量模式******")
    val pi = math.Pi
    val Pi = math.Pi

    // case 后面 以小写字母开头的简单名称 是变量，其它都是常量。   这可太秀了
    // 如果坚持想让小写做常量，可以用this.pi  或者 `pi`
    println(1 match {
      case pi => "pi is " + pi   // 可以发现这里pi是match里的变量，和上面的pi没关系。（如果你用idea会发现上面的pi是黑的）
      case _=> "不是pi"
    })

    println(1 match {
      case Pi => "pi is " + Pi   // 可以发现这里Pi是常量，也就是上面的Pi
      case _ => "不是pi"
    })

    println("\n******序列模式******")
    List(0, 1, 2) match {
      case List(0, _*) => println("found it")
      case _ =>
    }

    println("\n******元祖模式******")
    (0, 1, 2) match {
      // case 1 => println("found it")   // 这句会报错；如果把它封成def describe(x: Any)这样的函数，就不会报错。服了
      case (a, b, c) => println("matched" + a + b + c)
      case _ =>
    }

    println("\n******带类型模式******")
    def generalSize(x: Any) = x  match {
      case s: String => s.length
      // case m: Map[Int, Int] => "here"               // 由于类型擦除机制，它等效与 Map[_, _]
      case m: Map[_, _] => m.size                      // Map[_, _] 匹配任何 Map
      case n: Array[String] => "我是 Array[String]" //  但是数组的类型有效
      case _ =>
    }

    println(generalSize("zzzzzz"))
    println(generalSize(Map(1 -> "z", 2 -> "x", 3 -> "y")))
    println(generalSize(Array("zzz")))

    println("\n******变量绑定******")
    (0, 1, 2) match {
      case m @ (_, _, _) => println("matched" + m)  // 这里将(_, _, _) 绑定给变量 m
      case _ =>
    }

    println("\n******模式守卫******")
    (0, 1, 1) match {
      // case (_, b, b) => println("我是abb型")          // 错误写法
      case (_, b, c) if b == c => println("我是abb型")
      case _ =>
    }

    println("\n******模式重叠******")
    def badMatch(x: Any) = x match {
      case List(0, _*) => "here"
      case List(0, 1, 2) =>
    }
    println(badMatch(List(0, 1, 2)))

    println("\n******Option类型******")
    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

    println(capitals("France"))       // Paris
    //println(capitals("?"))          // 抛异常
    println(capitals get "France")    // Some(Paris)
    println(capitals get "?")         // None

    def show(x: Option[String]) = x match {
      case Some(s) => s
      case None => "?"
    }

    println(show(capitals get "France"))
    println(show(capitals get "?"))


    println("\n******模式解析******")
    val (num, str) = (123, "abc")
    println(num + " " + str)


    println("\n******偏函数******")

    // 偏函数和函数字面量对比，是不是很像～  可以发现偏函数就是 多条 带模式匹配 的 函数字面量
    val withDefault: Option[Int] => Int =
    {
      case Some(x) => x
      case None => 0
    }

    val increase: Option[Int] => Option[Int] =
      (x: Option[Int]) => x

    println("偏函数： " + withDefault(Some(10)))
    println("函数字面量： " + increase(Some(10)))

    // 指定类型为 偏函数 才有 isDefinedAt() 方法。上面的 withDefault 是没有isDefinedAt() 方法的，其实它是全函数
    val second: PartialFunction[List[Int], Int] = {
      case  List(_, y, _) => y
    }

    println(second.isDefinedAt(List(1, 2, 3, 4)))

    println("\n******for表达式中的模式******")
    val result = List(Some("apple"), None, Some("orange"))
    for (Some(fruit) <- result)    // 未匹配到的会被丢弃
      println(fruit)



    // p298 的 一个复杂的例子，太长跳过

  }

}

/*
******常量模式******
the empty list

******变量模式******
pi is 1
不是pi

******序列模式******
found it

******元祖模式******
matched012

******带类型模式******
6
3
我是 Array[String]

******变量绑定******
matched(0,1,2)

******模式守卫******
我是abb型

******模式重叠******
here

******Option类型******
Paris
Some(Paris)
None
Paris
?

******模式解析******
123 abc

******偏函数******
偏函数： 10
函数字面量： Some(10)
false

******for表达式中的模式******
apple
orange
 */