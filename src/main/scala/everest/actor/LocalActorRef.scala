package everest.actor


class LocalActorRef(val system: ActorSystem, val clazz: Class[_], val path: String) extends ActorRef {

  override def !(message: Any)(implicit sender: ActorRef = Actor.noSender): Unit = {
    actorCell.sendMessage(message)(sender)
  }

  def actorCell(): ActorCell = {
    return new ActorCell(system, clazz)
  }

}
