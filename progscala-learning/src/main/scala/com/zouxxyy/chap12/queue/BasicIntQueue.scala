package com.zouxxyy.chap12.queue

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {

  private val buf = new ArrayBuffer[Int]

  override def get(): Int = buf.remove(0)

  override def put(x: Int): Unit = { buf += x }

}

object BasicIntQueue {

  def main(args: Array[String]): Unit = {

    val queue1 = new BasicIntQueue with Doubling

    queue1.put(10)

    println(queue1.get())


    val queue2 = new BasicIntQueue with Incrementing with Filtering

    // 先调用最右边的trait的put，因为它调用了super.put，于是调用左侧的trait的put
    queue2.put(-1)
    queue2.put(0)
    queue2.put(1)

    println(queue2.get())
    println(queue2.get())

    println("\n顺序测试：")
    val queue3 = new BasicIntQueue with Incrementing with Doubling

    queue3.put(5)
    println(queue3.get()) // 2x + 1

    val queue4 = new BasicIntQueue with Doubling with Incrementing

    queue4.put(5)
    println(queue4.get()) // (x + 1) * 2

  }
}

/*
20
1
2

顺序测试：
11
12
 */