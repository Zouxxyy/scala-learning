package com.zouxxyy.chap04

// 可以直接运行，神奇
object AppSummer extends App {

  for (season <- List("fall", "winter", "spring"))
    println(season + ": " + ChecksumAccumulator.calculate(season))

}
