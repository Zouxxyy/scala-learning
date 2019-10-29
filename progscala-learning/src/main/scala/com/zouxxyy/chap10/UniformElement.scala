package com.zouxxyy.chap10

// 可以发现scala类可以是private
private class UniformElement(
                      ch: Char,
                      override val width: Int,
                      override val height: Int
                    ) extends Element {

  private val line = ch.toString * width

  override def contents: Array[String] = Array.fill(height)(line)
}
