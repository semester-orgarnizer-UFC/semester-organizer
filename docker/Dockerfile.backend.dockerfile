FROM openjdk:11
FROM maven:3.6.3
WORKDIR /home/app/backend
CMD ["mvn", "spring-boot:run"]
COPY backend /home/app/backend