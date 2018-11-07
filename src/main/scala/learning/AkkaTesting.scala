package learning

import akka.actor.{ActorSystem, Props}

object AkkaTesting extends App {

  object AkkaActor extends akka.actor.Actor {

    def receive = {
      case s: String => println(s"String $s")
      case i: Int => println(s"Int $i")
      case x:Any => println(s"Something else ${x.getClass()}" )
    }
  }

  val system = ActorSystem("ActorSystem")
  val actor = system.actorOf(Props( AkkaActor), "SimpleActor")
  println("before int")
  actor ! 100
  println("after int")
  actor ! "hi"
  println("before double")

  actor ! 20.33
  println("before boolean")

  actor ! true
  system.terminate()

}
