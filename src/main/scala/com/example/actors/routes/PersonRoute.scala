package com.example.actors.routes

import com.example.data.PerfResultCollector
import com.example.services.PersonService
import akka.actor.Props
import spray.http.{HttpResponse, StatusCodes}
import spray.routing.HttpService
import spray.httpx.SprayJsonSupport
import akka.actor.Actor

/**
* Factory method for Props configuration files for actors
*/
object PersonRoute {
  def props: Props = Props(new PersonRoute())
}

/**
 * Actor that handles requests that begin with "person"
 */
class PersonRoute() extends Actor with PersonRouteTrait {
  def actorRefFactory = context
  def receive = runRoute(personRoute)
}

/**
 * Separate routing logic in an HttpService trait so that the
 * routing logic can be tested outside of an actor system in specs/mockito tests
 */
trait PersonRouteTrait extends HttpService with SprayJsonSupport{

  private val personService = PersonService
//  val log = LoggerFactory.getLogger(classOf[PersonRouteTrait])

  val personRoute = {
    get {
      pathEnd {
        complete {
          println("Hitting Get All Persons")
          HttpResponse(entity=PerfResultCollector.getDataAsJson())
        }
      }
    }


  }

}
