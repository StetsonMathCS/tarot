docker build /t stetson_server_local .
docker run --name c_stetson_server_local -p 80:8080 -v /home/kevin/logs:/usr/local/tomcat/logs stetson_server_local

