FROM openjdk:11

RUN mkdir /app

ADD ./build/dist/server.tar /app
COPY ./build/dist/website /app/web
WORKDIR /app

ENV WEB_DIR /app/web

CMD /app/server/bin/server