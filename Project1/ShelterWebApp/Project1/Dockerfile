# Start with the alpine linux tomcat image as a base
FROM tomcat:8.5-jdk8-openjdk

# Switch to the CATALINA_HOME directory
WORKDIR $CATALINA_HOME

# Create a reference to some file on the physical machine (optional)
ARG WAR_FILE=./target/*.war

# Copy the file from the physical machine to the webapps dire in the PWD
COPY $WAR_FILE ./webapps

# Expose Port 8080 as where the application will be accessed
EXPOSE 8080

# Execute to start Tomcat and finish the Image creation.
CMD ["catalina.sh", "run"]