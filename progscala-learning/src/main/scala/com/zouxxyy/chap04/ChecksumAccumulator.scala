package com.zouxxyy.chap04

import scala.collection.mutable

class ChecksumAccumulator {

  // 默认都是public
  private var sum = 0

  def add(b: Byte): Unit = { sum += b}
  def checksum(): Int = ~(sum & 0xFF) + 1 // 取反加1求补码

}

// 单例对象，是同名对象的伴生对象
object ChecksumAccumulator {

  private val cache = mutable.Map.empty[String, Int]

  /**
   * 计算字符串所有字符的校验和
   * @param s
   * @return
   */
  def calculate(s: String): Int = {
    // 如果计算过，直接返回值
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator // new的是上面那个，单例对象不能用new
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }
  }
}
