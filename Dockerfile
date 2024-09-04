FROM tomcat:10.1.24-jre17

WORKDIR /user/local/tomcat/webapps

COPY ROOT.war .