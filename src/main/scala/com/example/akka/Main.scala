package com.example.akka

import com.example.akka.http.WebServer
import com.typesafe.config.{Config, ConfigFactory}
import io.kontainers.micrometer.akka.AkkaMetricRegistry
import io.micrometer.core.instrument.Clock
import io.micrometer.core.instrument.binder.jvm.{ClassLoaderMetrics, JvmGcMetrics, JvmMemoryMetrics, JvmThreadMetrics}
import io.micrometer.core.instrument.binder.logging.LogbackMetrics
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
  
  new JvmMemoryMetrics().bindTo(meterRegistry)
  new JvmGcMetrics().bindTo(meterRegistry)
  new JvmThreadMetrics().bindTo(meterRegistry)
  new ClassLoaderMetrics().bindTo(meterRegistry)
  new LogbackMetrics().bindTo(meterRegistry)

  WebServer.start()
}

