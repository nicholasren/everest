package everest.actor

import org.scalatest.{Matchers, WordSpecLike}

class ActorLookupSpec extends WordSpecLike with Matchers {

  var system: ActorSystem = new ActorSystem("actor-system")

  def inbox() = new Inbox()

  implicit val i = inbox()


  "a actor system" must {

    "find actor by lookup their path" in {
      val echo = system.actorOf[EchoActor].get

      system.actorSelection(echo.path) should ===(echo)
    }

  }
}
