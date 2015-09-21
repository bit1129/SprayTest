package com.example.actors.routes

import com.example.data.{Results, PerfResultCollector}
import akka.actor.Props
import spray.http.{HttpResponse, StatusCodes}
import spray.routing.HttpService
import spray.httpx.SprayJsonSupport
import akka.actor.Actor

/**
* Factory method for Props configuration files for actors
*/
object DataRouter {
  def props: Props = Props(new DataRouter())
}

/**
 * Actor that handles requests that begin with "person"
 */
class DataRouter() extends Actor with DataRouterTrait {
  def actorRefFactory = context
  def receive = runRoute(sqlPerfDataRouter)
}

/**
 * Separate routing logic in an HttpService trait so that the
 * routing logic can be tested outside of an actor system in specs/mockito tests
 */
trait DataRouterTrait extends HttpService with SprayJsonSupport{

  val sqlPerfDataRouter = {
    get {
      pathEnd {
        complete {
          HttpResponse(entity=Results.get())
        }
      }
    }


  }

}
