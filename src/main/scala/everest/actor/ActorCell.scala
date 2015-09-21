package everest.actor

class ActorCell(val clazz: Class[_]) {

  val actor = clazz.newInstance().asInstanceOf[Actor]

  def sendMessage(message: Any)(sender: ActorRef) = {
    actor.receive(message)
  }
}
