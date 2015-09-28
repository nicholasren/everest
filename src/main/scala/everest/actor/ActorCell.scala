package everest.actor

class ActorCell(val system: ActorSystem, val clazz: Class[_]) {

  val actor = instantiate(clazz).asInstanceOf[Actor]

  def sendMessage(message: Any)(sender: ActorRef) = {
    system.accept(actor, message)(sender);
  }


  private

  def instantiate[T](clazz: Class[T]): T = try clazz.newInstance catch {
    case iae: IllegalAccessException ⇒
      val ctor = clazz.getDeclaredConstructor()
      ctor.setAccessible(true)
      ctor.newInstance()
  }
}
