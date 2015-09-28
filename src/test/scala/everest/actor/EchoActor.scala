package everest.actor

class EchoActor extends Actor {

  override def receive(message: Any)(implicit sender: ActorRef): Unit = {
      println(sender)
      sender ! message
  }

}
