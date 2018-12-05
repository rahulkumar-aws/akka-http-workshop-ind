package io.acme.core

import io.acme.models.Todo
import io.acme.repository.TodoRepository

import scala.concurrent.Future

trait TodoMocks{

  class FailingRepository extends TodoRepository {
    override def all(): Future[Seq[Todo]] = Future.failed(new Exception("mocked exception"))

    override def done(): Future[Seq[Todo]] = Future.failed(new Exception("mocked exception"))

    override def pending(): Future[Seq[Todo]] = Future.failed(new Exception("mocked exception"))
  }
}
