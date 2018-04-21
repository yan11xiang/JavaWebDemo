# JavaWebDemo

## 项目简介
本项目是本人用于测试的一个简单的 javaWeb 应用

项目启动 
> JavaWebDemo/demo-admin/src/test/java/com/cbrothercoder/demo/admin/JettyAdminTestJetty.java main()方法


## Docker
Docker image基于 [官方JETTY镜像](https://hub.docker.com/_/jetty/)9.3.23-jre8制作
### Docker 构建
> $ sh build.sh


### Docker启动工程
> docker run -d -p 8080:8080 webdemo
