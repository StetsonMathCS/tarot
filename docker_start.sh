docker pull wsdt/tarot:latest
docker stop stetson_server
docker rm stetson_server
docker run --name stetson_server -v /var/logs/tarot:/usr/local/tomcat/logs --network="host" wsdt/tarot:latest
