package io.acme.api
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
class Auth {
  def routes: Route = pathPrefix("api" / "v1") {
    path("sample") {
      get {
        complete("SampleResponse")
      }
    } ~
      path("int" / IntNumber) { number =>
        get {
          complete(number.toString)
        }
      } ~
      path("regex" / """^[a-z0-9]+$""".r) { matched =>
        get {
          complete(matched)
        }
      }
  }
}
