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
	@ echo "$(GITHUB_TOKEN)" | docker login docker.pkg.github.com -u $(CIRCLE_USERNAME) --password-stdin
registry-login:
	@ docker login --username=_ --password=$$(heroku auth:token) registry.heroku.com

push-image:
	@ echo $(IMAGE_NAME)
	@ docker push docker.pkg.github.com/$(IMAGE_NAME)

tag-image-registry:
	@ docker tag docker.pkg.github.com/$(IMAGE_NAME):latest $(REGISTRY_IMAGE_NAME)/web

push-image-registry: tag-image-registry
	@ docker push $(REGISTRY_IMAGE_NAME)/web

deploy:
	@ make deploy_on_heroku IMAGE_ID=$$(docker image inspect $(REGISTRY_IMAGE_NAME)/web -f {{.Id}})

deploy_on_heroku:
	@ curl -X PATCH \
            -H "Authorization: Bearer $$(heroku auth:token)" \
            -H "Content-Type: application/json" \
            -H "Accept:application/vnd.heroku+json; version=3.docker-releases" \
            -d '{ "updates": [{"type": "web",  "docker_image": "$(IMAGE_ID)"}] }' \
            https://api.heroku.com/apps/buckets-summary-producer/formation