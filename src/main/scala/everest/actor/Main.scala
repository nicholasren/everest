package everest.actor

object Main extends App {

  val system = new ActorSystem("hello")

  val echo = system.actorOf[Echo]

  for( i <- 1 to 10000) {
    echo ! s"hello world $i"
  }

}


class Echo extends Actor {
  override def receive(msg: Any)(implicit sender: ActorRef): Unit = {
    println(msg)
  }
}