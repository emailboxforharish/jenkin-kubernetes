FROM openjdk:11
EXPOSE 8080
ADD target/spring-git-jenkin-docker.jar spring-git-jenkin-docker.jar
ENTRYPOINT ["java","-jar","/spring-git-jenkin-docker.jar"]