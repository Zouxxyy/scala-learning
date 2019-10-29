package com.zouxxyy.chap11

// 自定义值类型
// 必须继承AnyVal，且仅有一个参数，除了def不能有其它东西，不能重定义equals和hashCode
class Dollars(val amount: Int) extends AnyVal {

  override def toString: String = "$" + amount

}


object Dollars {
  def main(args: Array[String]): Unit = {
    val dollar = new Dollars(1000)
    println(dollar)
  }
}

/*
$1000
 */