FROM eclipse-temurin:21-jdk-jammy
LABEL authors="neeraj"

WORKDIR /app

COPY gradle gradle
COPY src src
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradlew .
COPY gradle/wrapper gradle/wrapper
COPY gradlew.bat gradlew.bat

RUN ./gradlew build --no-daemon --stacktrace

FROM eclipse-temurin:21-jdk-jammy

COPY build/libs/occupancy-planning-0.0.1-SNAPSHOT.jar oc_planning.jar

ENTRYPOINT ["java", "-DAZURE_OPENAI_API_KEY=${AOKEY}", "-DOPENAI_KEY=${OKEY}", "-DVERGESENSE_API_KEY=${VKEY}", "-jar", "oc_planning.jar"]