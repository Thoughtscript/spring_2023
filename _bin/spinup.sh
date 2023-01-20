#!/usr/bin/env bash

# docker stop mailhog/mailhog && docker rm mailhog/mailhog && docker run -d -p 1025:1025 -p 8025:8025 --name smtp mailhog/mailhog &

# docker stop rabbitmq && docker rm rabbitmq && docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management &

# docker stop mongodb && docker rm mongodb && docker run -d -p 27017:27017 --name mongodb mongo:6.0.3 &

# docker stop redis && docker rm redis && docker run -d -p 6379:6379 --name redis redis:7.0.8 &

sleep 3.5 && cd ../_spring && mvn clean && mvn spring-boot:run &

sleep 5.5 && cd ../_react && npm i && npm run build-parcel-prod && npx serve &

wait