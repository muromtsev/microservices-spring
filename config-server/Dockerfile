# 1 Базовый образ, содержащий среду Java времени выполнения
FROM openjdk:17.0.1-jdk-slim as build

LABEL author="Ilya Muromtsev"
# Файл jar приложения
ARG JAR_FILE
# Добавить файл jar приложения в контейнер
COPY ${JAR_FILE} app.jar
# распаковывает jar файл в файловую систему образа
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# 2
FROM openjdk:17.0.1-jdk-slim
# Добавить том, ссылающийся на каталог /tmp
VOLUME /tmp
# Скопировать распакованное приложение в новый контейнер
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
# запустить приложение
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.muromtsev.employee.ConfigServerApplication"]