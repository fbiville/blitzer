#!/bin/bash
export MAVEN_OPTS="$MAVEN_OPTS -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
mvn clean install jetty:run
