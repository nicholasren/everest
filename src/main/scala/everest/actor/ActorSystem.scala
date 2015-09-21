package everest.actor

import scala.collection.mutable
import scala.reflect.ClassTag

class ActorSystem(val name: String) {

  val actors = mutable.MutableList[ActorCell]()

  def actorOf[T <: Actor : ClassTag]: ActorRef = {
    val clazz: Class[_] = implicitly[ClassTag[T]].runtimeClass

    actors += new ActorCell(clazz)
    new LocalActorRef(this, clazz)
  }


  //  private
  //  def instantiate[T](clazz: Class[T]): T = try clazz.newInstance catch {
  //    case iae: IllegalAccessException ⇒
  //      val ctor = clazz.getDeclaredConstructor()
  //      ctor.setAccessible(true)
  //      ctor.newInstance()
  //  }
}
