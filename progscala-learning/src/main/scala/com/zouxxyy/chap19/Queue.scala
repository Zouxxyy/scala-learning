package com.zouxxyy.chap19

import com.zouxxyy.chap13.Fruit
import com.zouxxyy.chap13.Fruits._

// 因为主构造函数很挫， 所以将它私有
// leading和trailing类型是T并且作为可变入参数，必须将它们定为private[this]
class Queue[+T] private (private[this] var leading: List[T], private[this] var trailing: List[T]) {

  // 方法一：辅助构造函数
  def this(elems: T*) = this(elems.toList, Nil)

  private def mirror(): Unit =
    if (leading.isEmpty) {
        while (trailing.nonEmpty) {
          leading = trailing.head :: leading
          trailing = trailing.tail
        }
      }


  // 之所以可以避免调用head浪费，因为调用它时会改变leading，使它不为空
  def head: T = {
    mirror()
    leading.head
  }

  def tail: Queue[T] = {
    mirror()
    new Queue(leading.tail, trailing)
  }

  // 用+注解的类型参数，不能用于方法参数的类型
  // 以下方法的参数类型是T，所不能 class Queue[+T]
  //  def enqueue(x: T) =
  //    new Queue(leading, x :: trailing)


  // 使用下界进行改造
  // U 必须的 T 的父类 或者 T
  def enqueue[U >: T](x: U) =
    new Queue[U](leading, x :: trailing)

}

object Queue {

  // 方法二：工厂方法
  def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)

  def main(args: Array[String]): Unit = {


    // 使用 chap13 里的 Fruit 测试
    //
    //       Fruit
    //       /   \
    //    Apple  Orange


    //  Queue.apply(Apple, Apple) 返回 一个Queue[Apple]
    val appleQueue: Queue[Apple.type] = Queue.apply(Apple, Apple)

    //  调用enqueue[U >: T](x: U)，T 是 Apple，U 必须的 T 的父类，所以 Orange 转成 Fruit，即 U 是 Fruit
    //  最终返回 Queue[Fruit]
    val fruitQueue: Queue[Fruit] = appleQueue.enqueue(Orange)

    println(fruitQueue.tail.tail.head.name)

  }

}


/*
orange
 */