package com.zouxxyy.chap06

/**
 * 有理数
 * @param n
 * @param d
 */
class Rational(n: Int, d: Int) {

  // 前置条件
  require(d != 0)

  private val g = gcd(n.abs, d.abs)

  val numer: Int = n / g
  val denom: Int = d / g

  // 类定义体中，非字段或者非方法定义的代码，直接编译进主构造方法中。 注意是主构造方法才执行
  // println("Created " + numer + "/" + denom)

  // 辅助构造方法
  def this(n : Int) = this(n, 1) // 辅助构造方法必须调用同一个类的另一个构造方法

  // 重写toString
  override def toString: String = if (denom == 1) numer.toString else numer + "/" + denom

  // 计算最大公约数
  @scala.annotation.tailrec
  private def gcd(a: Int, b:Int): Int = if(b == 0) a else gcd(b, a % b)

  def add(another: Rational): Rational =
    new Rational(numer * another.denom + another.numer * denom, denom * another.denom)

  def + (another: Rational): Rational = add(another)

  def + (i: Int): Rational =
    new Rational(numer + i * denom, denom)

  def - (another: Rational): Rational =
    new Rational(numer * another.denom - another.numer * denom, denom * another.denom)

  def - (i: Int): Rational =
    new Rational(numer - i * denom, denom)

  def * (another: Rational): Rational =
    new Rational(numer * another.numer, denom * another.denom)

  def * (i: Int): Rational =
    new Rational(numer * i, denom)

  def / (another: Rational): Rational =
    new Rational(numer * another.denom, denom * another.numer)

  def / (i: Int): Rational =
    new Rational(numer, denom * i)

  def lessThan(another: Rational): Boolean =
    numer * another.denom < another.numer * denom

  def max(another: Rational): Rational =
    if (lessThan(another)) another else this

}
