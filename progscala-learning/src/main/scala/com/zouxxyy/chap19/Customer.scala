package com.zouxxyy.chap19

/**
 * 主要是 对  trait Function[-S, +T] { def apply(x: S): T}   入参是逆变，返回是协变 的理解
 */

class Publication (val title: String)

class Book(title: String) extends Publication(title)

object Library {

  val books: Set[Book] = Set(new Book("Programming in Scala"), new Book("Walden"))

  def printBookList(info: Book => AnyRef): Unit = {
    for (book <- books) println(info(book))
  }
}

// trait Function[-S, +T] { def apply(x: S): T}   入参是逆变，返回是协变

// 永远记住 只有子类型可以隐性的转换为父类型！！！！

object Customer extends App {

  // 这里入参是Publication，返回是String
  def getTitle(p: Publication): String = p.title

  // 先分析现象：
  // Publication => String    传递给了    Book => AnyRef
  // 即由 Function1[Publication, String]  变成了  Function1[Book, AnyRef] 表面前者是子类，后者是父类
  // Publication 是 Book 的 父类，所以是 逆变（-S）                       （现象1）
  // String 是 AnyRef 的 子类，所以是 协变 (+T）                          （现象2）
  Library.printBookList(getTitle)

  // 分析原因：
  // 执行 info(x) 会变成 getTitle(y)
  // 即 由 x 强转成 y。 即 x 必是 y 的子类。                                (现象1的原因，x对应book，y对应Publication）
  // 执行getTitle(y)假设返回类型是 m。 而  info(book) 需要的返回类型是 n
  // 因此要由 m 强转成 n。 即 m 必是 n 的子类。                              (现象2的原因，m对应String，n对应AnyRef）


  // 如果你不想探究以上，可以直接记住定义函数字面量时 入参是父类，返回是子类

}


/*
Programming in Scala
Walden
 */