package com.example.config

import akka.actor.ActorSystem
import com.example.actors.routes.ApiRouterActor
import com.example.actors.routes.PersonRoute

/**
 * Factory method for ActorSystemBean class
 */
object ActorSystemBean {
  def apply(): ActorSystemBean = new ActorSystemBean()
}

/**
 * Defines an actor system with the actors used by
 * the spray-person application
 */
class ActorSystemBean {

  implicit val system = ActorSystem("person")

  lazy val personRoute = system.actorOf(PersonRoute.props, "person-route")
  lazy val apiRouterActor = system.actorOf(ApiRouterActor.props(personRoute), "api-router")

}
