FROM bellsoft/liberica-openjdk-alpine:11
VOLUME /tmp
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/myapp
#ADD ../build/libs/demo-0.0.1-SNAPSHOT.jar /usr/local/myapp/
#ADD ../build/libs/*.jar /usr/local/myapp/
ADD ./build/libs/*.jar /usr/local/myapp/myapp.jar
ADD run.sh run.sh
RUN chmod +x run.sh
RUN chmod +x /usr/local/myapp/myapp.jar
CMD ./run.sh
