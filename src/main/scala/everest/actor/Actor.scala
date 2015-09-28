package everest.actor


object Actor {
  def noSender = new Inbox()
}

trait Actor {
  def receive(msg: Any)(implicit sender: ActorRef = Actor.noSender): Unit
}
