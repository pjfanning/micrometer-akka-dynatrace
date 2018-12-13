package com.example.akka

import com.example.akka.http.WebServer
import com.typesafe.config.{Config, ConfigFactory}
import io.kontainers.micrometer.akka.AkkaMetricRegistry
import io.micrometer.core.instrument.Clock
import io.micrometer.dynatrace.{DynatraceConfig, DynatraceMeterRegistry}

object Main extends App {
  implicit class RichConfig(val underlying: Config) extends AnyVal {
    def getOptionalString(path: String): Option[String] = if (underlying.hasPath(path)) {
      Some(underlying.getString(path))
    } else {
      None
    }
  }
  val dconfig = new DynatraceConfig {
    val config = ConfigFactory.defaultApplication()
    override def get(key: String): String = config.getOptionalString(key).getOrElse(null)
  }
  val meterRegistry = new DynatraceMeterRegistry(dconfig, Clock.SYSTEM)
  AkkaMetricRegistry.setRegistry(meterRegistry)
  WebServer.start()
}

