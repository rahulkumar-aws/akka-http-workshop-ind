package io.acme.repository

import io.acme.models.Todo

import scala.concurrent.Future

trait TodoRepository {
  def all():Future[Seq[Todo]]
  def done():Future[Seq[Todo]]
  def pending():Future[Seq[Todo]]
}


