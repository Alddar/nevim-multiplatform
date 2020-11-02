FROM nginx:alpine
WORKDIR /usr/share/nginx/html
COPY frontend/dist/* /usr/share/nginx/html
COPY frontend/conf/app-nginx.conf /etc/nginx/conf.d/default.conf
