FROM java:8

RUN mkdir /app
WORKDIR /app
COPY build/libs/jungle-eureka.jar /app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/jungle-eureka.jar", "--spring.profiles.active=docker"]
EXPOSE 8761