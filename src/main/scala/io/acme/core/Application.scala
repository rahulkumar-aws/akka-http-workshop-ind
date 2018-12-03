package io.acme.core

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContextExecutor, Future}
import io.acme.api.Api

object Application extends App {
  def start(): Future[Http.ServerBinding] = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val routes = (new Api).routes

    val bindingFuture = Http().bindAndHandle(routes, "0.0.0.0", 8000).recoverWith {
      case _ => sys.exit(1)
    }

    sys.addShutdownHook {
      bindingFuture.map(_.unbind())
    }

    bindingFuture
  }

  start()
}
