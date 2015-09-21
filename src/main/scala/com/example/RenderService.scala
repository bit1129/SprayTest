package com.example

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

import scala.concurrent.duration._
import akka.io.Tcp
import akka.actor._
import spray.http._
import HttpMethods._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class RenderServiceActor extends Actor with RenderService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = {
    case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
      runRoute(myRoute)
    case HttpRequest(GET, Uri.Path(""), _, _, _) =>
      runRoute(myRoute)
    case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
      sender ! HttpResponse(entity = "PONG!")
    case _ =>
      sender ! HttpResponse(entity = "Unknown Requeest!")
  }
}


// this trait defines our service behavior independently from the service actor
trait RenderService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to
                  <i>spray-routing</i>
                  on
                  <i>Jetty</i>
                  !!!</h1>
              </body>
            </html>
          }
        }
      }
    }
}