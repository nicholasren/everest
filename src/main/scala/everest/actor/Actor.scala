package everest.actor


object Actor {
  def noSender = null
}

trait Actor {
  def receive(msg: Any): Unit
}
