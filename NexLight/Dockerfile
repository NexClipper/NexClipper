FROM openjdk:8-jre-alpine
ENV LANG ko_KR.UTF-8
ENV LANGUAGE ko_KR:en
ENV LC_ALL ko_KR.UTF-8

MAINTAINER NexCloud

VOLUME /tmp

ADD ./target/NexClipper-0.0.1.war NexClipper.war
RUN sh -c 'touch /NexClipper.war'
RUN apk add --no-cache curl


ENV JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.131-3.b12.el7_3.x86_64/jre/
ENV PATH=$JAVA_HOME/bin:$PATH
  
ENV JAVA_OPTS=""
CMD exec java -jar -Djava.security.egd=file:/dev/./urandom -jar /NexClipper.war