FROM mysql:5.7

WORKDIR /docker-entrypoint-initdb.d
COPY docker/create-db.sql .

