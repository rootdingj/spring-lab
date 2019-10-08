Spring 中的数据操作
====================
# 1、JDBC 必知必会

## 1.1、配置单数据源

### Spring Boot 配置数据源
- 使用 [Spring Initializr](https://start.spring.io/) 构建工程；

- 引入 H2数据库驱动，使用 H2数据库内存模式；

- 引入 JDBC 驱动，spring-boot-starter-jdbc；

- 获取 DataSource bean 信息；

-  也可通过 /acturator/beans 查看 Bean，如：http://localhost:8080/actuator/beans。

### Spring 配置数据源
- 数据源 ：DataSource,根据选择的连接池确定；

- 事物（可选）：PlatformTransactionManager（DataSourceTransactionManager） 、  TransactionTemplate；

- 操作（可选）：JdbcTemplate。

### Spring Boot 配置数据源做了哪些事情：
- DataSourceAutoConﬁguration ，配置 DataSource

- DataSourceTransactionManagerAutoConﬁguration ，配置 DataSourceTransactionManager

- JdbcTemplateAutoConﬁguration ， 配置 JdbcTemplate

## 1.2、配置多数据源

注意事项：
- 不同数据源的配置要分开
- 关注每次使用的数据源 ：
  - 有多个 DataSource 时系统如何判断
  - 对应的设施（事务、ORM 等）如何选择 DataSource

# 2、O/R Mapping 实践

## 2.1、连接池 HikariCP
HikariCP 官网： https://github.com/brettwooldridge/HikariCP

### HikariCP 为什什么快：
- 1.字节码级别优化（很多⽅方法通过 JavaAssist ⽣生成） 
- 2.⼤大量量⼩小改进 
    - ⽤用 FastStatementList 代替 ArrayList
    - 无锁集合 ConcurrentBag
    - 代理理类的优化（如，⽤用 invokestatic 代替了了 invokevirtual）

## 2.2、连接池 Druid
Alibaba Druid 官网： https://github.com/alibaba/druid/wiki/Druid%E8%BF%9E%E6%8E%A5%E6%B1%A0%E4%BB%8B%E7%BB%8D

### 实⽤用的功能：
- 详细的监控
- ExceptionSorter，针对主流数据库的返回码都有⽀支持
- SQL 防注⼊入
- 内置加密配置
- 众多扩展点，⽅方便便进⾏行行定制


### 在 Spring Boot 中的配置
- Spring Boot 2.x 中默认使用 HikariCP 连接池
- Spring Boot 1.x 中默认使⽤用 Tomcat 连接池，需要移除 tomcat-jdbc 依赖，spring.datasource.type=com.zaxxer.hikari.HikariDataSource 。

### 常⽤用 HikariCP 配置参数
- spring.datasource.hikari.maximumPoolSize=10
- spring.datasource.hikari.minimumIdle=10
- spring.datasource.hikari.idleTimeout=600000
- spring.datasource.hikari.connectionTimeout=30000
- spring.datasource.hikari.maxLifetime=1800000

其他配置参考官网

## 2.2、连接池 Alibaba Druid


# 3、NoSQL 实践


# 4、数据访问进阶


http://localhost:8080/actuator/beans


# 参考资料

- Java 编程思想
- [面向对象编程三大特性 ------ 封装、继承、多态](http://blog.csdn.net/jianyuerensheng/article/details/51602015)





