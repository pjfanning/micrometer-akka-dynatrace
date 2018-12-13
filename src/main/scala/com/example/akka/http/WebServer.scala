package com.example.akka.http

import akka.http.scaladsl.server.{HttpApp, Route}

object WebServer extends HttpApp {
  override def routes: Route = HelloWorldService.hello

  def start(): Unit = startServer("0.0.0.0", 12345)
}
