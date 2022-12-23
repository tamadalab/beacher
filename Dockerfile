FROM alpine:3.10.1 AS base

RUN    apk --no-cache add openjdk11=11.0.4_p4-r1 \
    && rm -rf /var/cache/apk/* \
    && /usr/lib/jvm/java-11-openjdk/bin/jlink \
       --module-path /usr/lib/jvm/java-11-openjdk/jmods \
       --compress=2 \
      --add-modules java.base,java.logging,java.xml \
      --no-header-files \
      --no-man-pages \
      --output /opt/openjdk-11-minimal

FROM alpine:3.10.1

ARG beacher_version="1.0.0"

LABEL maintainer="Tamadalab" \
      version="${beacher_version}" \
      description="A tool for detecting build tools of the projects. "

COPY --from=base /opt/openjdk-11-minimal /opt/openjdk-11-minimal

RUN    adduser -D ninerules \
    && apk --no-cache add --virtual .builddeps curl unzip \
    && curl -s -L -O "https://github.com/tamadalab/beacher/releases/download/v${beacher_version}/beacher-${beacher_version}.tar.gz" \
    && tar -zxvf "beacher-${beacher_version}.tar.gz"        \
    && mv "beacher-${beacher_version}" /opt                 \
    && ln -s "/opt/beacher-${beacher_version}" /opt/beacher  \
    && rm "beacher-${beacher_version}.tar.gz"              \
    && apk del --purge .builddeps

ENV JAVA_HOME="/opt/openjdk-11-minimal"
ENV PATH="$PATH:$JAVA_HOME/bin"
ENV HOME="/home/beacher"

WORKDIR /home/beacher
USER    beacher

ENTRYPOINT [ "java", "-jar", "./beacher-1.0.0.jar" ]