#你的 tomcat的镜像
FROM tomcat
#作者
MAINTAINER joe192168@sina.com
RUN rm -rf /usr/local/tomcat/webapps/*
#放置到tomcat的webapps目录下
COPY docker.war /usr/local/tomcat/webapps
#时区设置 – 通过Dockerfile
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone