package io.acme.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class Api {
  def routes: Route = path("status") {
    get {
      complete(StatusCodes.OK)
    }
  } ~ (new Auth).routes
}
