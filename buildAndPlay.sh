#!/bin/sh
./mvnw clean package;
java -jar target/proxx-game-0.0.1-SNAPSHOT.jar;
