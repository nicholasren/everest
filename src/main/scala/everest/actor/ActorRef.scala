package everest.actor

trait ActorRef {
  def !(message: Any)(implicit sender: ActorRef = Actor.noSender): Unit
}
