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

### 数据源配置
- 1.直接配置 DruidDataSource 
- 2.通过 druid-spring-boot-starter 配置 spring.datasource.druid.*
```test
spring.output.ansi.enabled=ALWAYS

spring.datasource.url=jdbc:h2:mem:foo
spring.datasource.username=sa
## 密码加密 
spring.datasource.password=n/z7PyA5cvcXvs8px8FVmBVpaRyNsvJb3X7YfS38DJrIg25EbZaZGvH4aHcnc97Om0islpCAPc3MqsGvsrxVJw==
spring.datasource.druid.connection-properties=config.decrypt=true;config.decrypt.key=${public-key}
spring.datasource.druid.filter.config.enabled=true

spring.datasource.druid.initial-size=5
spring.datasource.druid.max-active=5
spring.datasource.druid.min-idle=5
## Filter 配置
spring.datasource.druid.filters=conn,config,stat,slf4j

spring.datasource.druid.test-on-borrow=true
spring.datasource.druid.test-on-return=true
spring.datasource.druid.test-while-idle=true

## SQL 防注⼊
spring.datasource.druid.ﬁlter.wall.enabled=true
spring.datasource.druid.ﬁlter.wall.db-type=h2
spring.datasource.druid.ﬁlter.wall.conﬁg.delete-allow=false
spring.datasource.druid.ﬁlter.wall.conﬁg.drop-table-allow=false

public-key=MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALS8ng1XvgHrdOgm4pxrnUdt3sXtu/E8My9KzX8sXlz+mXRZQCop7NVQLne25pXHtZoDYuMh3bzoGj6v5HvvAQ8CAwEAAQ==
```

### Druid Filter
- ⽤用于定制连接池操作的各种环节
- 可以继承 FilterEventAdapter 以便便⽅方便便地实现 Filter
- 修改 META-INF/druid-ﬁlter.properties 增加 Filter 配置

### 连接池选择时的考量量点
- 可靠性
- 性能
- 功能
- 可运维性 
- 可扩展性
- 其他

## 2.2、连接池 Alibaba Druid


# 3、NoSQL 实践


# 4、数据访问进阶


http://localhost:8080/actuator/beans


# 参考资料

- Java 编程思想
- [面向对象编程三大特性 ------ 封装、继承、多态](http://blog.csdn.net/jianyuerensheng/article/details/51602015)





