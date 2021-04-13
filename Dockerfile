FROM openjdk:11

RUN mkdir /app

ADD ./djserver/build/distributions/djserver.tar /app
COPY ./djweb/build/distributions/* /app/web/
WORKDIR /app

ENV WEB_DIR /app/web

CMD /app/djserver/bin/djserver
