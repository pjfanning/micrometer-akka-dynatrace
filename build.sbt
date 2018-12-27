name := "micrometer-akka-dynatrace"

scalaVersion := "2.12.8"

resolvers += Resolver.sonatypeRepo("releases")

val akkaVersion = "2.5.19"
val akkaHttpVersion = "10.1.6"
val kontainersMicrometerVersion = "1.1.2"

enablePlugins(JavaAgent)
javaAgents += "org.aspectj" % "aspectjweaver" % "1.9.2" % "runtime"

libraryDependencies ++= Seq(
  "io.kontainers" %% "micrometer-akka" % "0.9.1",
  "io.kontainers" % "micrometer-registry-dynatrace" % kontainersMicrometerVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)
