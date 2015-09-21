package everest.actor

object Main extends App {

  val system = new ActorSystem("hello")

  val echo = system.actorOf[Echo]

  echo ! "hello"

}


class Echo extends Actor {
  override def receive(msg: Any): Unit = {
    println("I received hello world")
  }
}