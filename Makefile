SHELL := /bin/bash

test:
	@ ./mvnw test
package:
	@ ./mvnw clean package -DskipTests
docker-image-build:
	@ docker build -f ./src/main/docker/Dockerfile.jvm -t guimsmendes/buckets-summary-producer .
run: docker-image-build
	@ docker run -i --rm -p 8080:8080 guimsmendes/buckets-summary-producer
stop:
	@ docker stop IMAGE_ID=$$(docker image inspect guimsmendes/buckets-summary-producer -f {{.Id}})
registry-login:
	@ docker login --username=_ --password=$$(heroku auth:token) registry.heroku.com

tag-image:
	@ docker tag guimsmendes/buckets-summary-producer:latest registry.heroku.com/buckets-summary-producer/web

push-image: tag-image
	@ docker push registry.heroku.com/buckets-summary-producer/web

deploy:
	@ make deploy_on_heroku IMAGE_ID=$$(docker image inspect registry.heroku.com/buckets-summary-producer/web -f {{.Id}})

deploy_on_heroku:
	@ curl -X PATCH \
            -H "Authorization: Bearer $$(heroku auth:token)" \
            -H "Content-Type: application/json" \
            -H "Accept:application/vnd.heroku+json; version=3.docker-releases" \
            -d '{ "updates": [{"type": "web",  "docker_image": "$(IMAGE_ID)"}] }' \
            https://api.heroku.com/apps/buckets-summary-producer/formation