# Stetson University, 2018 (school project)
# Course planning, Tarot
# Maintainer: Kevin Riedl

FROM tomcat:8.0.20-jre8

LABEL version="1.0.0" \ 
	university.name="Stetson University" \
	university.location="Florida, Deland" \
	maintainer="Kevin Riedl" \
	maintainer.mail="kevin.riedl.privat@gmail.com" \
	last_change="09.27.2018" \
	maintainer.github="https://github.com/wsdt"


# Possible improvement: Wget in cmd, so new war fetched on container start! (but container stops, why?)
RUN rm -rf /usr/local/tomcat/webapps/ROOT && rm -rf /usr/local/tomcat/conf/server.xml
ADD https://github.com/wsdt/tarot/raw/master/target/spring/ROOT.war /usr/local/tomcat/webapps/ROOT.war
ADD https://github.com/wsdt/tarot/raw/master/server.xml /usr/local/tomcat/conf/server.xml

