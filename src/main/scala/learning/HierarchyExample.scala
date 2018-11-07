package learning

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
object HierarchyExample extends App {

  case object CreateChild
  case object SignalChildren
  case object PrintSignal

  class ParentActor extends Actor {
    private var number = 0
    private val children = collection.mutable.Buffer[ActorRef]()
    def receive = {

      case CreateChild =>
        children+= context.actorOf(Props[ChildActor], "ChildActor" + number)
        number += 1
        println(number)
        println(self)

      case SignalChildren =>
        println(self)
        children.foreach(_ ! PrintSignal)
    }
  }

  class ChildActor extends Actor {
    def receive = {
      case PrintSignal => println("signal: " + self)
    }
  }

  val system = ActorSystem("HierarcyActorSystem")
  val parentActor = system.actorOf(Props(new ParentActor()), "ParentActor")
  val childActor = system.actorOf(Props(new ChildActor()), "ChildActor")

  parentActor ! CreateChild
  parentActor ! CreateChild
  parentActor ! SignalChildren
  childActor ! PrintSignal
  childActor ! PrintSignal

}
