docker pull wsdt/tarot:latest
docker stop stetson_server
docker rm stetson_server
docker run --name stetson_server -p 80:8080 -v /home/kevin/logs:/usr/local/tomcat/logs wsdt/tarot:latest
