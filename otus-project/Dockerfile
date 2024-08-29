FROM gradle:8.5.0-jdk21
ENV GRADLE_USER_HOME /home/gradle/cache

# entity классы
WORKDIR /app/logistic-entity
COPY logistic-entity/build.gradle ./
COPY logistic-entity/settings.gradle ./
COPY logistic-entity/src ./src
RUN gradle publishToMavenLocal -i --stacktrace


# утилиты
WORKDIR /app/logistic-utils
COPY logistic-utils/build.gradle ./
COPY logistic-utils/settings.gradle ./
COPY logistic-utils/src ./src
RUN gradle publishToMavenLocal -i --stacktrace

WORKDIR /app/logistic-deps

COPY k8s/logistic-deps/build.gradle ./
COPY k8s/logistic-deps/settings.gradle ./
COPY k8s/logistic-deps/src ./src
RUN gradle bootJar -i --stacktrace