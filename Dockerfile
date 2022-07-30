FROM openjdk:11-jdk
EXPOSE 8080
ADD ./build/libs/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=local
CMD ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]