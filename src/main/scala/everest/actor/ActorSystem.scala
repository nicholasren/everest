package everest.actor

import java.util.concurrent.Executors

import scala.collection.mutable
import scala.reflect.ClassTag

class ActorSystem(val name: String) {
  def accept(actor: Actor, message: Any)(implicit sender: ActorRef) = {
    pool.submit(new Runnable {
      override def run(): Unit = actor.receive(message)(sender)
    })
  }


  val actors = mutable.MutableList[ActorCell]()
  val pool = Executors.newFixedThreadPool(10);

  def actorOf[T <: Actor : ClassTag]: ActorRef = {
    val clazz: Class[_] = implicitly[ClassTag[T]].runtimeClass

    actors += new ActorCell(this, clazz)

    new LocalActorRef(this, clazz)
  }
}
