#!/bin/sh

echo "[SCRIPT] ------------------------------------------------------------------------"
echo "[SCRIPT] Executing maven build"
echo "[SCRIPT] ------------------------------------------------------------------------"
mvn clean install -DskipTests

echo "[SCRIPT] ------------------------------------------------------------------------"
echo "[SCRIPT] Start payara micro and deploy application"
echo "[SCRIPT] ------------------------------------------------------------------------"
java -jar payara-micro-5.183.jar --deploy target/tracking.war

exit 0