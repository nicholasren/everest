package everest.actor

class EchoActor extends Actor {

  override def receive(message: Any)(implicit sender: ActorRef): Unit = {
    sender ! message
  }

}
