package com.zouxxyy.chap10

import Element.elem


object TestElement {

  def main(args: Array[String]): Unit = {

    println("ArrayElement测试：")
    println(elem(Array("zzz", "xxx", "yyy")))

    println("\nUniformElement测试：")
    println(elem('-', 10, 4))

    println("\nLineElement测试：")
    println(elem("hello world"))

    println("\nabove测试：")
    println(elem(Array("aaa", "bb", "cccc")) above elem(Array("dd", "eeeeee", "ff", "ggg")))

    println("\nbeside测试：")
    println(elem(Array("aaa", "bb", "cccc")) beside  elem(Array("dd", "eeeeee", "ff", "ggg")))

  }
}

/*
ArrayElement测试：
zzz
xxx
yyy

UniformElement测试：
----------
----------
----------
----------

LineElement测试：
hello world

above测试：
aaa
bb
cccc
dd
eeeeee
ff
ggg

beside测试：
aaadd
bbeeeeee
ccccff
   ggg
 */
