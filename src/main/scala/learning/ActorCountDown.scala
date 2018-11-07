package learning

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case class StartCounting(i:Int, actorRef:ActorRef)
case class CountDown(i:Int)

object ActorCountDown extends App {

  class CountDownActor extends Actor{
    def receive = {
      case StartCounting(x,other) =>
        println(x)
        other ! CountDown(x-1)
      case CountDown(i) =>
        println(self)
        if(i > 0){
          println (i)
         sender ! CountDown(i-1)
        }
        else context.system.terminate
    }
  }

  val system = ActorSystem("ActCountDownSystem")
  val countDownActor = system.actorOf(Props(new CountDownActor), "CountDownActor1")
  val countDownActor2 = system.actorOf(Props(new CountDownActor), "CountDownActor2")
  countDownActor ! StartCounting(10,countDownActor2)
}
