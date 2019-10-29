package com.zouxxyy.chap10

import Element.elem


/**
 * 用于构建和渲染二维布局元素的类库
 */

abstract class Element {

  def contents: Array[String]

  // 行数
  def height: Int = contents.length

  // 第一行的长度，如果没有就是0
  def width: Int = if (height == 0) 0 else contents(0).length

  // 以上都是无参方法。
  // 无参方法 表明你只是访问某个属性；   用()写法时，一般表明，方法里面有一些有趣的操作。
  // 可以发现它和字段很像。 字段更快，但耗内存
  // 无参数方法另一好处是，你用起来压根不知道用的是方法还是直接取字段。 所以类设计者可以随便改


  def above(that: Element): Element = {
      val this1 = this widen that.width
      val that1 = that widen this.width
      elem(this1.contents ++ that1.contents)
    }



  def beside(that: Element): Element = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for ((line1, line2) <- this1.contents zip that1.contents)
        yield line1 + line2
    )
  }


  def widen(w: Int): Element =
    if(w <= width ) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }

  def heighten(h: Int): Element =
    if(h <= height ) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      val bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }



  override def toString: String = contents mkString "\n"
}

// 在伴生类中定义工厂对象
object Element{

  def elem(contents: Array[String]): Element =
    new ArrayElement(contents) // 可以访问private的类。。

  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)

  def elem(line: String): Element =
    new LineElement(line)

}
