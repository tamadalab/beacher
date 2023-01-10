FROM --platform=linux/x86_64 openjdk:17-jdk-alpine

ARG beacher_version="1.0.0"

LABEL maintainer="Tamadalab" \
      version="${beacher_version}" \
      description="A tool for detecting build tools of the projects. "

COPY ./app/build/libs/beacher-1.0.0.jar /home/beacher/beacher.jar

RUN adduser beacher

ENV JAVA_HOME="/opt/openjdk-17"
ENV PATH="$PATH:$JAVA_HOME/bin"
ENV HOME="/home/beacher"

WORKDIR /home/beacher
USER    beacher

ENTRYPOINT [ "java", "-jar", "beacher.jar"]