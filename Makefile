SHELL := /bin/bash

test:
	@ ./mvnw test
package:
	@ ./mvnw clean package -DskipTests
build-image:
	@ docker build -f ./src/main/docker/Dockerfile.jvm -t $(IMAGE_NAME) .
run: build-image
	@ docker run -i --rm -p 8080:8080 $(IMAGE_TAG)
stop:
	@ docker stop IMAGE_ID=$$(docker image inspect $(IMAGE_TAG) -f {{.Id}})

docker-login:
	@ echo "$(GITHUB_TOKEN)" | docker login docker.pkg.github.com -u $(USERNAME) --password-stdin
registry-login:
	@ docker login --username=_ --password=$$(heroku auth:token) registry.heroku.com

push-image:
	@ docker tag $(IMAGE_NAME) docker.pkg.github.com/$(DOCKER_IMAGE_NAME)
	@ docker push docker.pkg.github.com/$(DOCKER_IMAGE_NAME)

tag-image-registry:
	@ docker tag docker.pkg.github.com/$(DOCKER_IMAGE_NAME):latest registry.heroku.com/$(DOCKER_IMAGE_NAME)/web

push-image-registry: tag-image-registry
	@ docker push registry.heroku.com/$(DOCKER_IMAGE_NAME)/web

deploy:
	@ make deploy_on_heroku IMAGE_ID=$$(docker image inspect registry.heroku.com/$(DOCKER_IMAGE_NAME)/web -f {{.Id}})

deploy_on_heroku:
	@ curl -X PATCH \
            -H "Authorization: Bearer $$(heroku auth:token)" \
            -H "Content-Type: application/json" \
            -H "Accept:application/vnd.heroku+json; version=3.docker-releases" \
            -d '{ "updates": [{"type": "web",  "docker_image": "$(IMAGE_ID)"}] }' \
            https://api.heroku.com/apps/$(REPO_NAME)/formation