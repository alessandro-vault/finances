FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN ./gradlew build

COPY src ./src

CMD ["./gradlew", "bootRun"]
