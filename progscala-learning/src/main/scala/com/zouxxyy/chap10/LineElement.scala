package com.zouxxyy.chap10

// 单行布局元素
private class LineElement(s: String) extends Element {


  override def width: Int = s.length

  override def height: Int = 1

  override def contents: Array[String] = Array(s)
}
