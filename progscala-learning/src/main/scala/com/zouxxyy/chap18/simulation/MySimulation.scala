package com.zouxxyy.chap18.simulation

object MySimulation extends CircuitSimulation {

  override def InverterDelay: Int = 1

  override def AndGateDelay: Int = 3

  override def OrGateDelay: Int = 5

  def main(args: Array[String]): Unit = {

  }

  // 4根线
  val input1, input2, sum, carry = new Wire

  probe("sum", sum)

  probe("carry", carry)

  halfAdder(input1, input2, sum, carry)

  input1.setSignal(true)

  run()

  input2.setSignal(true)

  run()

}

/*
sum 0 new-value = false
carry 0 new-value = false
*** simulation started, time = 0 ***
sum 8 new-value = true
*** simulation started, time = 8 ***
carry 11 new-value = true
sum 15 new-value = false
 */