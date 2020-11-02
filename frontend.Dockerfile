FROM nginx:alpine
WORKDIR /usr/share/nginx/html
COPY dist/* /usr/share/nginx/html
COPY conf/app-nginx.conf /etc/nginx/conf.d/default.conf
