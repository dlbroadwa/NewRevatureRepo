FROM tomcat:8.5-jdk8-openjdk
WORKDIR $CATALINA_HOME
ARG WAR_FILE=./target/*.war
COPY $WAR_FILE ./pet_store_app
EXPOSE 8080
CMD ["catalina.sh", "run"]