docker rm -f nginx-VGTimeImitation
docker run --name nginx-VGTimeImitation -v $(pwd)"/nginx.conf:/etc/nginx/nginx.conf:ro" --rm -d -p 10119:80 nginx
