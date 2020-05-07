FROM tomcat:9-jdk8-openjdk

WORKDIR $CATALINA_HOME

ARG WAR_FILE=./target/*.war

COPY $WAR_FILE ./project1

EXPOSE 8080

CMD ["catalina.sh", "run"]