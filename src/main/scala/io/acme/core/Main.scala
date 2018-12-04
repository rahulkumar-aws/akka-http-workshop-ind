package io.acme.core

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import io.acme.models.Todo
import io.acme.repository.InMemoryTodoRepository

import scala.concurrent.Await
import scala.util.{Failure, Success}

object Main extends App{

  val host = "0.0.0.0"
  val port = 9000


  implicit val system:ActorSystem = ActorSystem(name="todoapi")
  implicit val materialize: ActorMaterializer = ActorMaterializer()

  import system.dispatcher

  val todoRepository = new InMemoryTodoRepository(Seq(
    Todo("1", "Buy egg","Buy a dozen", false),
    Todo("2", "Buy Milk","low fat milk", false),
    Todo("3", "Clean room","clean living room & hall", false),
    Todo("4", "Post mail","Add ticket of $5", false),
    Todo("5", "Feed Fish","Only put 10-20 amount", false)
  ))
  val router = new TodoRouter(todoRepository)
  val server = new Server(router, host, port)
  val binding = server.bind()

  binding.onComplete{
    case Success(_) => println("Succsess")
    case Failure(exception) => println(s"Failed: ${exception.getMessage}")
  }

  import scala.concurrent.duration._

  Await.result(binding, 3.seconds)

}
