package io.acme.api
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route

class AuthTest extends ApiTest {

  "Auth API V1" should {

    "GET /api/v1/sample" in {
      Get("/api/v1/sample") ~> routes ~> check {
        status shouldBe StatusCodes.OK
        responseAs[String] shouldBe "SampleResponse"
      }
    }

    "GET /api/v1/int/256" in {
      Get("/api/v1/int/256") ~> routes ~> check {
        responseAs[String] shouldBe "256"
      }
    }
    "GET /api/v1/int/BELOWZERO" in {
      Get("/api/v1/int/-6") ~> Route.seal(routes) ~> check {
        status.isFailure() shouldBe true
      }
    }
    "GET /api/v1/int/0" in {
      Get("/api/v1/int/0") ~> routes ~> check {
        status.isSuccess() shouldBe true
      }
    }

    "GET /api/v1/regex/ALPHADIGITS" in {
      Get("/api/v1/regex/abc123eee") ~> routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
    "GET /api/v1/regex/NON-ALPHADIGITS" in {
      Get("/api/v1/regex/abc-123-eee") ~> Route.seal(routes) ~> check {
        status.isFailure() shouldBe true
      }
    }
  }
}
