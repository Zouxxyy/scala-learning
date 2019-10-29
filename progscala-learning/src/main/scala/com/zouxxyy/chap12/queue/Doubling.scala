package com.zouxxyy.chap12.queue

trait Doubling extends IntQueue {


  abstract override def put(x: Int): Unit = { super.put(2 * x) } // 动态绑定


}
