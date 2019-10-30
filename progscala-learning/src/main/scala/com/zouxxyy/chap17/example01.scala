package com.zouxxyy.chap17

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object example01 {

  def main(args: Array[String]): Unit = {

    println("******序列******")

    // 数组 (高速访问)
    val fiveInts = new Array[Int](5)

    println(fiveInts.mkString(" "))

    // 列表缓冲
    val buf = new ListBuffer[Int]
    buf += 1
    buf += 2
    println(buf.toList)

    // 数组缓冲
    val arrayBuffer = new ArrayBuffer[Int]()
    arrayBuffer += 4
    arrayBuffer += 5
    println(arrayBuffer)

    println("\n******集和映射******")

    // 不可变
    val ints0 = Set(1, 2, 3)
    val ints1 = Set(1, 2, 3, 4, 5)
    println(ints0.getClass)  // 不可变 元素小于5个是 指定Set
    println(ints1.getClass)  // 不可变 元素大于等于5个是 HashSet；   Map同理

    // 可变
    val nums = mutable.Set.empty[Int] // 可变 是HashSet； Map同理
    println(nums.getClass)
    nums += 5                         // 可变集支持 +=
    nums ++= List(7, 9)
    println(nums)

    // 排好序
    val treeSet = mutable.TreeSet(1, 4, 5, 2, 7)
    println(treeSet)

    // 转换
    // list -> set
    val values = List("a", "b", "c")
    val valuesSet = Set[String]() ++ values
    println(valuesSet)

    // set -> list
    println(valuesSet.toList)

    // 不可变 -> 可变
    val muValuesSet = mutable.Set.empty ++= valuesSet   // 注意，由于是不可变，用的是++=
    println(muValuesSet.getClass)

    // 可变 -> 不可变
    val newValueSet = Set.empty ++ muValuesSet   // 注意是 ++
    println(newValueSet.getClass)

    println("\n******元组******")
    // 一个常见的应用场景: 函数返回多值，再用元组接收
    def longestWord(words: Array[String]) = {
      var word = words(0)
      var idx = 0
      for (i <- 1 until words.length)
        if (words(i).length > word.length) {
          word = words(i)
          idx = i
        }
      (word, idx)
    }

    val (word, idx) = longestWord(Array("zzzzzz", "z", "zzzzzzzz", "zzzz"))

    println(word + " " + idx)

  }
}


/*
******序列******
0 0 0 0 0
List(1, 2)
ArrayBuffer(4, 5)

******集和映射******
class scala.collection.immutable.Set$Set3
class scala.collection.immutable.HashSet$HashTrieSet
class scala.collection.mutable.HashSet
Set(9, 5, 7)
TreeSet(1, 2, 4, 5, 7)
Set(a, b, c)
List(a, b, c)
class scala.collection.mutable.HashSet
class scala.collection.immutable.Set$Set3

******元组******
zzzzzzzz 2
 */