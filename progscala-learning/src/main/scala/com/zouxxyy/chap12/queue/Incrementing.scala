package com.zouxxyy.chap12.queue

trait Incrementing extends IntQueue {
  abstract override def put(x: Int): Unit = { super.put(1 + x) }
}
