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


# 2、O/R Mapping 实践


# 3、NoSQL 实践


# 4、数据访问进阶


http://localhost:8080/actuator/beans


# 参考资料

- Java 编程思想
- [面向对象编程三大特性 ------ 封装、继承、多态](http://blog.csdn.net/jianyuerensheng/article/details/51602015)





