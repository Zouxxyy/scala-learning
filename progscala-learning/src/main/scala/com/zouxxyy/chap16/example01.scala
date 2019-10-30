package com.zouxxyy.chap16

/**
 * 使用 List
 */

object example01 {

  def main(args: Array[String]): Unit = {

    // 记住List的几大特点：
    // 不可变      Array是可变的
    // 链表        Array是平的
    // 同构        Tuple是不同构的
    // 协变： List[String] 是 List[Object]的子类型


    val fruit: List[String] = "apples" :: "oranges" :: "pears" :: Nil
    val nums: List[Int] = List(0 ,1, 2, 3)

    println("******列表模式******")

    val a :: b :: _ = fruit   // 取列表前两个
    println(a + " " + b)

    println("\n******List类的初阶方法******")
    // 初阶方法：不接收任何函数作为入参

    // 挑些有意思的：
    println("::拼接：" + ("Bananas" :: fruit))
    println(":::拼接：" + (List("Bananas") ::: fruit))

    println("反转数组：" + fruit.reverse)

    println("指定位置分割：" + fruit.splitAt(2))

    println("扁平：" + List(List(1, 3), List(5, 6, 7)).flatten)

    println("拉链：" + (fruit.indices zip fruit))  // 将下标和值拉链

    println("mkString：" + fruit.mkString("[", ", ", "]"))

    println("toArray：" + fruit.toArray)


    println("\n******List类的高阶方法******")

    println("map：" + nums.map(_ + 1))

    println("flatMap：" + List(List(1, 3), List(5, 6, 7)).flatMap(_.map(_ + 1)))  // 加一并展开。可以发现和spark的不同

    println("partition：" + nums.partition(_ > 0))

    println("takeWhile：" + List(1, 2, 4, -1 ,5).takeWhile(_ > 0)) // 取满足条件的连续部分

    println("forAll：" + nums.forall(_ > 0)) // 对全部判断

    println("左折叠：" + (fruit.head /: fruit.tail) (_ + " " +  _))

    println("右折叠：" + (fruit.tail :\ fruit.head) (_ + " " +  _))

    println("排序：" + nums.sortWith(_ < _))

    println("\n******List对象的方法******")

    println("range：" + List.range(0, 10, 2))

    println("fill：" + List.fill(5)("hi"))

    println("tabulate：" + List.tabulate(5)(n => n * n))


  }

}

/*
******列表模式******
apples oranges

******List类的初阶方法******
::拼接：List(Bananas, apples, oranges, pears)
:::拼接：List(Bananas, apples, oranges, pears)
反转数组：List(pears, oranges, apples)
指定位置分割：(List(apples, oranges),List(pears))
扁平：List(1, 3, 5, 6, 7)
拉链：Vector((0,apples), (1,oranges), (2,pears))
mkString：[apples, oranges, pears]
toArray：[Ljava.lang.String;@6be46e8f

******List类的高阶方法******
map：List(1, 2, 3, 4)
flatMap：List(2, 4, 6, 7, 8)
partition：(List(1, 2, 3),List(0))
takeWhile：List(1, 2, 4)
forAll：false
左折叠：apples oranges pears
右折叠：oranges pears apples
排序：List(0, 1, 2, 3)

******List对象的方法******
range：List(0, 2, 4, 6, 8)
fill：List(hi, hi, hi, hi, hi)
tabulate：List(0, 1, 4, 9, 16)
 */
