package io.acme.core

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directives, Route}
import io.acme.repository.TodoRepository

import scala.util.{Failure, Success}

trait Router {
def route: Route
}

class TodoRouter(todoRepository: TodoRepository) extends Router with Directives with TodoDirectives {
   import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
   import io.circe.generic.auto._
  override def route: Route = pathPrefix("todos") {
    pathEndOrSingleSlash {
      get {
        handleWithGeneric(todoRepository.all()) { todos =>
          complete(todos)
        }
      }
    } ~ path("done") {
        get {
          handleWithGeneric(todoRepository.done()) { todos =>
            complete(todos)
          }
        }
      } ~ path ("pending") {
        get {
          handleWithGeneric(todoRepository.pending()) { todos =>
            complete(todos)
          }
        }
      }
  }
}
