From jetty:9.3.23-jre8

ADD demo-admin.war /var/lib/jetty/webapps/
CMD unzip /var/lib/jetty/webapps/demo-admin.war -d /var/lib/jetty/webapps/ROOT \
    && rm -rf demo-admin.war
ADD start.ini /var/lib/jetty/

