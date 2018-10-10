docker pull wsdt/stetson_tarot:latest
docker stop stetson_server
docker rm stetson_server
docker run --name stetson_server -p 80:8080 wsdt/stetson_tarot:latest
