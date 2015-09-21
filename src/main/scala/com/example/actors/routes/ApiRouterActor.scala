package com.example.actors.routes

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorLogging
import akka.actor.Props
import spray.routing.HttpService

/**
 * Factory method for Props configuration files for actors
 */
object ApiRouterActor {
  def props(personRoute: ActorRef): Props = Props(new ApiRouterActor(personRoute))
}

/**
 * Routes the incoming request.  If the route begins with "api" the request is passed
 * along to the matching spray routing actor (if there's a match)
 *
 * Other routes are assumed to be static resources and are served from the resource
 * directory on the classpath.  getFromResourceDirectory takes the remainder of the path
 * so a route like "index.html" is completed with the classpath resource "dist/index.html"
 * or returns a 404 if it's not found.
 *
 * To run the front end app in dev mode change "dist" to "app"
 */
class ApiRouterActor(personRoute: ActorRef) extends Actor
  with HttpService
  with ActorLogging {

  def actorRefFactory = context
  def receive = runRoute {
    compressResponseIfRequested() {
      pathPrefix("data")  { ctx => personRoute ! ctx } ~
        pathPrefix("html") { get { getFromResourceDirectory("html") } } ~
        pathPrefix("css") { get { getFromResourceDirectory("css") } } ~
        pathPrefix("js") { get { getFromResourceDirectory("js") } }
    }

  }

}
