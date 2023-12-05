FROM gradle:jdk17 AS builder
COPY . .
RUN gradle build

FROM openjdk:17
WORKDIR /code
COPY --from=builder /home/gradle/build/libs/*.jar /code/app.jar

# Añade un comando de entrada para ejecutar el JAR
CMD ["java", "-jar", "app.jar"]