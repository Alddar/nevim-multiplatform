FROM openjdk:8-alpine
WORKDIR /
ADD backend/build/libs/backend-*-all.jar application.jar
CMD java -jar application.jar