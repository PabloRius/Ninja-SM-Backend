FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
COPY resources/products.csv resources/products.csv
ENTRYPOINT ["java","-jar","/app.jar"]