package everest.actor

import scala.collection.mutable

class Inbox {

  val messages = mutable.MutableList()

  def receive(): Any = messages.head

}
