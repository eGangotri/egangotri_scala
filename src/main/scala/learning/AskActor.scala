package learning

import akka.actor.{Actor, ActorSystem, Props}
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern._
import scala.concurrent.ExecutionContext.Implicits.global

case object AskName
case class NameResponse(name:String)

object AskActor extends App{

  class AskActorClass(val name:String) extends Actor {
    def receive = {
      case AskName => println("AskName") ;sender ! NameResponse("ss")
    }
  }

  val system = ActorSystem("AskActorSystem")
  val askActor = system.actorOf(Props(new AskActorClass("Pat")), "AskActor")
  implicit val timeout = Timeout(1.seconds)
  val answer = askActor ? AskName
  answer.foreach(println)
  system.terminate
}
