# micrometer-akka-dynatrace

This sample demonstrates how to add [Micrometer-Akka](https://github.com/kontainers/micrometer-akka) metrics to your application.

[Micrometer Metrics](http://micrometer.io/) compares itself to `SLF4J, but for metrics`.

All you need to do is add a dependency on the micrometer-akka jar, add some configuration to your [application.conf](https://github.com/pjfanning/micrometer-akka-dynatrace/blob/master/src/main/resources/application.conf) and to enable aspectjweaver.

The application.conf requires your dynatrace configs (API key and URI).

This sample uses [micrometer-registry-dynatrace](https://micrometer.io/docs/registry/dynatrace#_configuring) to have the metrics pushed to Dynatrace.
This is setup in [Main.scala](https://github.com/pjfanning/micrometer-akka-dynatrace/blob/master/src/main/scala/com/example/akka/Main.scala).

```sbt clean run```

This sample uses [sbt-javaagent](https://github.com/sbt/sbt-javaagent) to enable aspectjweaver.
This plugin basically adds this to the java runtime command.

```-javaagent:/path/to/aspectjweaver-1.9.2.jar```

If you want to use micrometer-akka with your own application, ensure that you startup script adds something like this:

```bash
JAVA_AGENT="-javaagent:$BASE/lib/aspectjweaver-1.9.2.jar"
$JAVA_HOME/bin/java $JAVA_AGENT -cp $CP $JVM_OPTS $CLASS_NAME
```

To query the hello endpoint, use:

```curl http://localhost:12345/hello```

The metrics are simple akka metrics and calling the hello endpoint generates some traffic to monitor.

