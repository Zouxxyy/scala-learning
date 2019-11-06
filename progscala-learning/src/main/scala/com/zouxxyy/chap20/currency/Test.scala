package com.zouxxyy.chap20.currency

object Test {

  def main(args: Array[String]): Unit = {

    val res16: Japan.Yen = Japan.Yen.from(US.Dollar) * 100

    println(res16)

    val res17: Europe.Euro = Europe.Euro.from(res16)

    println(res17)

    val res18: US.Dollar = US.Dollar.from(res17)

    println(res18)

    val res19: US.Dollar = US.Dollar * 100 + res18

    println(res19)

  }
}

/*
12100 JPY
75.89 EUR
99.87 USD
199.87 USD
 */