SHELL := /bin/bash

test:
	@ ./mvnw test
package:
	@ ./mvnw clean package -DskipTests
build-image:
	@ docker build -f ./src/main/docker/Dockerfile.jvm -t $(IMAGE_TAG) .
run: build-image
	@ docker run -i --rm -p 8080:8080 $(IMAGE_TAG)
stop:
	@ docker stop IMAGE_ID=$$(docker image inspect $(IMAGE_TAG) -f {{.Id}})

docker-login:
	@ docker login -u $(USERNAME) -p $(DOCKER_TOKEN)

push-image: docker-login
	@ docker tag $(IMAGE_TAG) $(DOCKER_IMAGE_NAME):$(IMAGE_TAG)
	@ docker push $(DOCKER_IMAGE_NAME):$(IMAGE_TAG)

registry-login:
	@ docker login --username=_ --password=$$(heroku auth:token) registry.heroku.com

tag-image-registry:
	@ docker tag $(IMAGE_TAG) registry.heroku.com/$(HEROKU_IMAGE_NAME)/web

push-image-registry: tag-image-registry
	@ docker push registry.heroku.com/$(HEROKU_IMAGE_NAME)/web

deploy:
	@ make deploy_on_heroku IMAGE_ID=$$(docker image inspect registry.heroku.com/$(HEROKU_IMAGE_NAME)/web -f {{.Id}})

deploy_on_heroku:
	@ curl -X PATCH \
            -H "Authorization: Bearer $$(heroku auth:token)" \
            -H "Content-Type: application/json" \
            -H "Accept:application/vnd.heroku+json; version=3.docker-releases" \
            -d '{ "updates": [{"type": "web",  "docker_image": "$(IMAGE_ID)"}] }' \
            https://api.heroku.com/apps/$(HEROKU_IMAGE_NAME)/formation