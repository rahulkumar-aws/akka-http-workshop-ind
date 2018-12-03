package io.acme.core

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext

object Application extends App {
  def start(): Unit = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContext = system.dispatcher
    val routes = complete("Running ...")
    val bindingFuture = Http().bindAndHandle(routes, "localhost", 6942).recoverWith {
      case _ => sys.exit(1)
    }

    sys.addShutdownHook{
      bindingFuture.map(_.unbind())
    }
  }
  start()
}
