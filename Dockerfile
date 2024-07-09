FROM openjdk:17
ENV SERVER_PORT=8080
ARG JAR_FILE=build/libs/app.jar
COPY ${JAR_FILE} ./app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "./app.jar", "--server.port=${SERVER_PORT}"]