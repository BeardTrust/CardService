FROM openjdk:11
LABEL maintainer="Matthew.Crowell@Smoothstack.com"
RUN adduser --system --group cardservice
USER cardservice:cardservice
ADD target/cardservice-0.0.1-SNAPSHOT.jar cardservice.jar
EXPOSE 7779
ENTRYPOINT ["java", "-jar", "cardservice.jar", "--spring.profiles.active=dev"]