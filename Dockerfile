FROM openjdk:8
ADD target/book-poq-0.0.1-SNAPSHOT.jar /app/book-poq.jar
EXPOSE 8080
CMD ["java", "-Xmx512m", "-jar", "/app/book-poq.jar"]
