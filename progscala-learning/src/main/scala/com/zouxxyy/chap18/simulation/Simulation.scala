package com.zouxxyy.chap18.simulation

abstract class Simulation {

  type Action = () => Unit

  case class WorkItem(time: Int, action: Action)  // 工作项

  private var curtime = 0
  def currentTime: Int = curtime

  private var agenda: List[WorkItem] = List()  // 日程

  private def insert(ag: List[WorkItem], item: WorkItem): List[WorkItem] = {
    if (ag.isEmpty || item.time < ag.head.time) item :: ag
    else ag.head :: insert(ag.tail, item)
  }

  def afterDelay(delay: Int)(block: => Unit) {
    val item = WorkItem(currentTime + delay, () => block)
    agenda = insert(agenda, item)
  }

  private def next() {
    (agenda: @unchecked) match {
      case item :: rest =>
        agenda = rest
        curtime = item.time
        item.action()
    }
  }

  def run() {
    afterDelay(0) {
      println("*** simulation started, time = "+ currentTime + " ***")
    }
    while (agenda.nonEmpty) next()
  }
}
