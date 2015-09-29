package everest.actor

import scala.collection.mutable

class Inbox extends ActorRef {

  def path = "akka://system/inbox"

  val messages = mutable.MutableList[Any]()

  def receive(): Any = {
    Thread.sleep(2000)
    messages.head
  }

  override def !(message: Any)(implicit sender: ActorRef): Unit = {
    messages += message
    println("received: " + messages.size)
  }
}
