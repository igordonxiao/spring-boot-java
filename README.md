## Deploy with DOCKER

## 1. Build app with Dockerfile

`mvn clean install -Dmaven.test.skip=true -P prod`

## 2. Run docker-compose

`cd src/main/docker`

`docker-compose up`

* if you want to delete containers, stop these containers first then execute command `docker-compose rm --all`