package com.example.akka.http

import akka.http.scaladsl.server.{Directives, Route}

object HelloWorldService extends Directives {
  def hello: Route =
    path("hello") {
      get {
        complete("hello")
      }
    }
}
