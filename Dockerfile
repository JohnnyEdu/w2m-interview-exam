FROM openjdk:11
ARG GRADLE_VERSION=gradle-7.5.1
RUN wget https://services.gradle.org/distributions/$GRADLE_VERSION-bin.zip -P /app
WORKDIR /app
RUN apt-get install -y unzip
RUN unzip $GRADLE_VERSION-bin.zip
RUN rm $GRADLE_VERSION-bin.zip

ENV GRADLE_HOME="/app/$GRADLE_VERSION"
ENV PATH="${PATH}:$GRADLE_HOME/bin"

RUN mkdir /usr/src/w2m-interview-exam
ADD . /usr/src/w2m-interview-exam
WORKDIR /usr/src/w2m-interview-exam
CMD ["gradle", "bootRun"]