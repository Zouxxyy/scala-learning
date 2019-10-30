package com.zouxxyy.chap15

/**
 * 样例类
 */

// sealed 密封类：只能在同一个文件中定义子类
sealed abstract class Expr

// 参数不用写val，它自动是val
case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr


object Expr {

  // 选择器 match {case xxx}
  // 构造方法模式匹配
  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e   // 双重取否
    case BinOp("+", e, Number(0)) => e  // 加0
    case BinOp("+", e, Number(1)) => e  // 加1
    case _ => expr
  }

  def main(args: Array[String]): Unit = {

    // 样例类构造对象不用new
    val v = Var("x")

    val op = BinOp("+", Number(1.0), Var("x"))  // 说实话，省略了 new 反而不好理解

    // 样例类自然实现toString, hashCode, equals 方法
    println(op.right == v)

    // 用样例类的copy，复制指定修改部分的新实例
    val op1 = op.copy(operator = "-")

    println("op1 = " + op1)

    val result = simplifyTop(BinOp("+", Var("这特么想干嘛？"), Number(0)))

    println(result)


    println("\n模式解析：")
    val BinOp(op2, left2, right2) = op
    println(op2 + left2 + right2)

  }

}

/*
true
op1 = BinOp(-,Number(1.0),Var(x))
Var(这特么想干嘛？)

模式解析：
+Number(1.0)Var(x)
 */