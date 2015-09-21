package com.example.config

import akka.io.IO
import spray.can.Http
import ActorSystemBean._

/**
 * Gets an actor system from the ActorSystemBean and initializes
 * a stand alone spray-can http server with it.
 *
 * @see [[com.example.actors.config.ActorSystemBean]]
 */
object Boot extends App {

  val services = ActorSystemBean()
  implicit val system = services.system
  val service = services.apiRouterActor

  IO(Http) ! Http.Bind(service, interface = "localhost", port = 8080)

}
