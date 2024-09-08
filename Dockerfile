FROM tomcat:10.1.24-jre17

COPY build/libs/ROOT.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]