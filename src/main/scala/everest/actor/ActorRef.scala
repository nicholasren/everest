package everest.actor

trait ActorRef {
  def path: String

  def !(message: Any)(implicit sender: ActorRef = Actor.noSender): Unit
}
