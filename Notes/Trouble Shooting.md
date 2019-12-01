TROUBLE
====================
## 1.Intellij IDEA Cannot Resolve Symbol ‘XXX’
- JDK 版本，设置 JDK 和 Maven 的JDK版本；
- maven 配置国内镜像；
- 是否下载依赖，没有下载可以 maven 项目使用 install；
- 个别包没下载到可以前往 https://mvnrepository.com 查看是否有这个版本的jar，在看看是否和其他的 jar 存在依赖关系，加载顺序的问题；
- file -> invalidate caches 清除缓存。

## 2.spring boot pom 文件中各版本号的区别和联系，如：version 2.2.0.M6 、2.1.1.RELEASE 、2.2.0.RC1等

[SpringBoot与SpringCloud的版本对应详细版](https://www.cnblogs.com/zhuwenjoyce/p/10261079.html)

## 3.docker 中启动 mongo 失败，问题排查：
- 执行：``docker run --name mongo -p 27017:27017 -v ~/docker-data/mongo:/data/db -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin -d mongo``

- 执行：``docker ps `` 找不到对应的 mongo 容器

- 执行：``docker ps -a`` 发现 mongo 容器的状态是 Exited

- 执行：``docker logs mongo`` 发现错误日志：
WiredTiger error (17) [1575191875:212498][1:0x7f4cfeba1b00], connection: __posix_open_file, 669: /data/db/WiredTiger.wt: handle-open: open: File exists Raw: [1575191875:212498][1:0x7f4cfeba1b00], connection: __posix_open_file, 669: /data/db/WiredTiger.wt: handle-open: open: File exists
...

- 删除 mongo 容器：``docker container rm mongo`` 

- 执行：``docker run --name mongo -p 27017:27017  -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin -d mongo`` 












