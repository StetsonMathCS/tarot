docker stop c_stetson_server_local
docker rm c_stetson_server_local
docker image rm stetson_local_server
docker build -f Dockerfile_Bailey -t stetson_local_server .
docker run --name c_stetson_server_local -p 80:8080 -v /Users/baileygranam/Documents/CSCI321/Tarot/target/spring.war:/usr/local/tomcat/webapps/ROOT.war stetson_local_server

