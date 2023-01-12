FROM openjdk:17-slim-bullseye as builder

RUN jlink --module-path /opt/java/openjdk/jmods --compress=2 \
    --add-modules java.base,java.scripting,java.logging,java.desktop,java.sql,java.xml,jdk.zipfs \
    --no-header-files --no-man-pages --output /opt/minimaljre

FROM debian:bullseye-slim

ARG VERSION="1.0.0"

LABEL org.opencontainers.image.authors="Tamadalab" \
      org.opencontainers.image.url="https://github.com/tamadalab/beacher" \
      org.opencontainers.image.source="https://github.com/tamadalab/beacher/blob/Docker/Dockerfile" \
      org.opencontainers.image.version="${VERSION}"

RUN adduser --disabled-login --disabled-password --no-create-home beacher \
    && mkdir -p /opt/beacher/libs
COPY --from=builder /opt/minimaljre /opt/minijre
ADD ./app/build/libs /opt/beacher/libs
ENV BEACHER_HOME=/opt/beacher
ENV JAVA_HOME=/opt/minijre
ENV PATH=$JAVA_HOME/bin:$PATH
ENV HOME=/home/beacher
ENV BEACHER_VERSION=${VERSION}

WORKDIR /home/beacher
USER beacher

ENTRYPOINT [ "java", "-jar", "/opt/beacher/libs/beacher-1.0.0.jar" ]