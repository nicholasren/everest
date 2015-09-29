package everest.actor

import java.util.concurrent.Executors

import scala.collection.mutable
import scala.reflect.ClassTag
import scala.util.{Success, Try}

class ActorSystem(val name: String) {

  def actorSelection(path: String): ActorRef = ???

  def accept(actor: Actor, message: Any)(implicit sender: ActorRef) = {
    pool.submit(new Runnable {
      override def run(): Unit = actor.receive(message)(sender)
    })
  }


  val actors = mutable.MutableList[ActorCell]()
  val pool = Executors.newFixedThreadPool(10)

  def actorOf[T <: Actor : ClassTag]: Try[ActorRef] = actorOf[T](defaultActorNameFor[T])


  def actorOf[T <: Actor: ClassTag](actorName: String): Try[ActorRef] = {
    val path = actorPathFor(actorName)
    val clazz: Class[_] = implicitly[ClassTag[T]].runtimeClass

    actors += new ActorCell(this, clazz)
    Success(new LocalActorRef(this, clazz, path))
  }

  private

  def actorPathFor(actorName: String): String = {
    s"akka://$name/$actorName"
  }

  def defaultActorNameFor[T: ClassTag]: String = {
    val clazz = implicitly[ClassTag[T]].runtimeClass
      s"${clazz.getSimpleName}-0"
  }

}
