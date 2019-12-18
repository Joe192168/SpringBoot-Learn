1、上传war包 

2、创建并编辑”Dockerfile”，内容如下：

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
3、生成新的镜像

docker build -t docker:v1 .

4、启动新的镜像

docker run -d -p 8080:8080 docker:v1

5、查看已启动镜像 
可以加上参数-itd后台启动tomcat，用以下方式查看已启动的镜像
docker ps

6、查看tomcat中的项目 
可以使用如下命令进入tomcat镜像：

docker exec -it ******* /bin/bash #******是容器id（CONTAINER_ID）

当我们进入镜像后，想对某些参数进行修改时，一般需要安装文本编辑器，以vim为例： 
由于下载地址是海外地址，下载速度非常慢而且可能出现中断，所以做如下配置：

mv /etc/apt/sources.list /etc/apt/sources.list.bak
echo "deb http://mirrors.163.com/debian/ jessie main non-free contrib" >/etc/apt/sources.list
echo "deb http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib" >>/etc/apt/sources.list
echo "deb-src http://mirrors.163.com/debian/ jessie main non-free contrib" >>/etc/apt/sources.list
echo "deb-src http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib" >>/etc/apt/sources.list

然后更新 
apt update 
现在就可以安装我们需要的软件了，例如vim 
apt install vim

7、输入ip和端口进行查看

8、可以查看指定容器的log

docker logs -f 容器id

9、部署web项目后也许会出现docker容器与宿主、docker 容器与tomcat 应用打印日志时间和我们当前时间相差8个小时，可以添加如下配置：

①启动容器时，将系统时间挂载到容器内，可以解决docker容器与宿主时间不一致问题，如下所示： 
添加参数

-v /etc/localtime:/etc/localtime:ro

完整命令如下示例：

sudo docker run -d --name button-api -p 8080:8080 -v /etc/localtime:/etc/localtime:ro button-api:v1

②docker 容器与tomcat 应用打印日志时间不一致 
启动tomcat容器后进入tomcat的”bin”目录，修改catalina.sh文件 
在注释结束的第一行添加如下内容：

JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF8 -Duser.timezone=GMT+08"

修改保存后重启docker容器就可以了。 
重启方法： 
“docker ps”查询容器id，使用如下命令重启：

docker restart 容器id
