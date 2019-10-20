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









