docker rm -f nginx-hospital
docker run --name nginx-hospital -v $(pwd)"/nginx.conf:/etc/nginx/nginx.conf:ro" --rm -d -p 10119:80 nginx
