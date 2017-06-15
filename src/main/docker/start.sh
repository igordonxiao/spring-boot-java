#! /bin/bash
wait-for-it.sh mypostgres:5600 -t 15
java -Djava.security.egd=file:/dev/./urandom -jar app.jar --spring.profiles.active=prod
