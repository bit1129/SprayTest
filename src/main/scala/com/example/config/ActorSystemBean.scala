package com.example.config

import akka.actor.ActorSystem
import com.example.actors.routes.PerfResultRouterActor
import com.example.actors.routes.DataRouter

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

  implicit val system = ActorSystem("sql-perf-render")

  lazy val sqlPerfDataRouterActor = system.actorOf(DataRouter.props, "sql-perf-data-router")
  lazy val sqlPerfRouterActor = system.actorOf(PerfResultRouterActor.props(sqlPerfDataRouterActor), "sql-perf-router")

}
