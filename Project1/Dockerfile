FROM tomcat:9-jdk8-openjdk

ENV CONNECTION_URL=jdbc:postgresql://akind-database.cby99r2xyn8t.us-east-2.rds.amazonaws.com:5432/postgres
ENV CONNECTION_USERNAME=joshuabrewer
ENV CONNECTION_PASSWORD=joshuabrewer

WORKDIR $CATALINA_HOME

ARG WAR_FILE=./target/*.war

COPY $WAR_FILE ./webapps

EXPOSE 8080

CMD ["catalina.sh", "run"]