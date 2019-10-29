package com.zouxxyy

// 包对象  里面可以存整个包都能用的方法
package object chap13 {

  def showFruit(fruit: Fruit): Unit = {
    import fruit._   // 任何地方都可以import
    println(name + "s are " + color)
  }

}
