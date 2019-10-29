package com.zouxxyy.chap12

import com.zouxxyy.chap06.Rational
import com.zouxxyy.chap10.Element
import com.zouxxyy.chap11.Dollars


// 组合Array[String]  继承Element
private class ArrayElement(val contents: Array[String]) extends Element

// scala字段和方法属于同一命名空间，可以用字段重写方法。 我服了
// 因此 字段名和方法名不能相等
