FROM openjdk:11
FROM maven:3.6.3
COPY backend /home/app/backend
WORKDIR /home/app/backend
# RUN mvn clean install
CMD ["mvn", "spring-boot:run"]