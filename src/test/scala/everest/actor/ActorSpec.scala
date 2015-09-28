package everest.actor

import org.scalatest.{Matchers, WordSpecLike}

class ActorSpec extends WordSpecLike with Matchers {

  var system: ActorSystem = new ActorSystem("actor-system")

  def inbox() = new Inbox()

  implicit val i = inbox()


  "a actor" must {
    val echo = system.actorOf[EchoActor]

    "be created by ActorSystem" in {
      assert(echo != null)
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
