package everest.actor

import everest.actor.exceptions.DuplicatedActorNameException
import org.scalatest.{BeforeAndAfter, Matchers, WordSpecLike}

import scala.util.{Success, Failure}

class ActorCreationSpec extends WordSpecLike with Matchers with BeforeAndAfter {

  var system: ActorSystem = new ActorSystem("actor-system")

  def inbox() = new Inbox()

  implicit val i = inbox()

  after {
    system.terminate
  }


  "a actor" must {

    "be created by ActorSystem" in {
      val echo = system.actorOf[EchoActor]
      echo shouldBe a[Success[ActorRef]]
    }

    "be created by given name" in {
      val echo = system.actorOf[EchoActor]("echo-0")
      echo.path should ===("akka://actor-system/echo-0")
    }

    "not be able to created if duplicated name provided" in {
      val a1 = system.actorOf[EchoActor]("echo-0")
      val a2 = system.actorOf[EchoActor]("echo-0")
      a1 shouldBe a[Success[ActorRef]]
      a2 shouldBe a[Failure[DuplicatedActorNameException]]
    }

    "be associated with a path" in {
      val echo = system.actorOf[EchoActor]
      echo.path should fullyMatch regex("akka://actor-system/EchoActor-\\d+")
    }
  }

  "An Inbox" must {
    val echo = system.actorOf[EchoActor]

    "function as implicit sender" in {
      //#inbox
      implicit val i = inbox()
      echo ! "hello"
      i.receive() should ===("hello")
      //#inbox
    }
  }

}
