# Usar uma imagem base mais leve do OpenJDK 21
FROM openjdk:21-jdk-slim AS builder

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos do projeto para o contêiner
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle
COPY gradlew /app/
COPY src /app/src

# Dar permissão de execução ao wrapper
RUN chmod +x gradlew

# Construir o projeto usando o Gradle Wrapper
RUN ./gradlew build --no-daemon --exclude-task test

# Usar uma imagem base do OpenJDK 21 mais leve para o ambiente de produção
FROM eclipse-temurin:21-jre-alpine

# Copiar o JAR construído do estágio de construção
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# Definir o diretório de trabalho
WORKDIR /app


# Expor a porta na qual a aplicação irá rodar
EXPOSE 8000

# Configurar o comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]

