package everest.actor

import java.util.concurrent.Executors

import everest.actor.exceptions.DuplicatedActorNameException

import scala.collection.mutable
import scala.reflect.ClassTag
import scala.util.{Failure, Success, Try}

class ActorSystem(val name: String) {

  def actorSelection(path: String): ActorRef = ???

  def accept(actor: Actor, message: Any)(implicit sender: ActorRef) = {
    pool.submit(new Runnable {
      override def run(): Unit = actor.receive(message)(sender)
    })
  }

  val actorRefs = mutable.MutableList[ActorRef]()

  val pool = Executors.newFixedThreadPool(10)

  def actorOf[T <: Actor : ClassTag](): ActorRef = actorOf[T](defaultActorNameFor[T])

  def actorOf[T <: Actor : ClassTag](actorName: String): ActorRef = {
    val path = actorPathFor(actorName)

    if (isDuplicated(path)) {
      throw new DuplicatedActorNameException(actorName)
    } else {
      val localRef: LocalActorRef = localActorRefWith[T](path)
      actorRefs += localRef
      localRef
    }
  }

  def terminate = {
    actorRefs.clear
  }

  private

  def localActorRefWith[T <: Actor : ClassTag](path: String): LocalActorRef = {
    val clazz: Class[_] = implicitly[ClassTag[T]].runtimeClass
    val localRef: LocalActorRef = new LocalActorRef(this, clazz, path)
    localRef
  }

  def actorPathFor(actorName: String): String = {
    s"akka://$name/$actorName"
  }

  def defaultActorNameFor[T: ClassTag]: String = {
    val clazz = implicitly[ClassTag[T]].runtimeClass
    s"${clazz.getSimpleName}-${System.currentTimeMillis()}"
  }

  def isDuplicated(path: String): Boolean = actorRefs.exists{ _.path == path }

}
