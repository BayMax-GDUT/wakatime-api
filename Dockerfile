FROM anapsix/alpine-java:8_server-jre_unlimited
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ADD ["target/three-classess-supervision-api-0.0.1-SNAPSHOT.jar", "app.jar"]
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]