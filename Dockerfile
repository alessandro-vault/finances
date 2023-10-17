FROM gradle:8.4.0-jdk17-focal as BUILD

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts /app/
COPY src /app/src

RUN gradle build --no-daemon

FROM amazoncorretto:17-alpine-jdk
WORKDIR /app

COPY --from=BUILD /app/build/libs/finances-1.jar .

EXPOSE 8080

CMD ["java", "-jar", "finances-1.jar"]
