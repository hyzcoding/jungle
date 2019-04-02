FROM java:8

RUN mkdir /app
WORKDIR /app
COPY build/libs/jungle-user.jar /app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/jungle-user.jar", "--spring.profiles.active=docker"]
EXPOSE 9001