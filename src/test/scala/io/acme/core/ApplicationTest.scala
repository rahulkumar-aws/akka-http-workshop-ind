package io.acme.core

import com.softwaremill.sttp._
import org.scalatest.{Matchers, WordSpec}
import scala.concurrent.ExecutionContext.Implicits.global

class ApplicationTest extends WordSpec with Matchers{
  "Service" should {
    "response on target port" in {
      implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()

      val bindingFuture = Application.start()

      sttp.get(uri"http://localhost:8000/status").send().code shouldBe 200

      bindingFuture.map(_.unbind())
    }
  }
}
