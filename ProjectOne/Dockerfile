FROM tomcat:8.5-jdk8-openjdk
WORKDIR $CATALINA_HOME
ENV Project1_UName postgres
ENV Project1_UPassword Cavalier93!
ENV Project1_URL jdbc:postgresql://database-1.cdr3hmlqxdcv.us-east-2.rds.amazonaws.com:5432/project1
ARG WAR_FILE=./target/*.war
COPY $WAR_FILE ./webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]