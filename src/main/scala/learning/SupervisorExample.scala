package learning

import akka.actor.{Actor, ActorSystem, Props}

object SupervisorExample extends App {

  case class DivideNumbers(x:Int, y:Int)
  class SupervisorActor extends Actor {
    def receive() ={
      case s:String => println ("..")
      case DivideNumbers(x,y) => println (self + ": " + x/y)
    }
  }

  val actorSystem = ActorSystem("SupervisorSystem")
  val actor = actorSystem.actorOf(Props(new SupervisorActor()), "SupervisorActor")
  actor ! DivideNumbers(10,2)
}
