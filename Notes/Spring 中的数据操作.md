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

## 1.3、连接池 HikariCP
HikariCP 官网： https://github.com/brettwooldridge/HikariCP

### HikariCP 为什什么快：
- 1.字节码级别优化（很多⽅方法通过 JavaAssist ⽣生成） 
- 2.⼤量小优化 
    - ⽤用 FastStatementList 代替 ArrayList
    - 无锁集合 ConcurrentBag
    - 代理理类的优化（如，⽤用 invokestatic 代替了了 invokevirtual）

## 1.4、连接池 Druid
Alibaba Druid 官网： https://github.com/alibaba/druid/wiki/Druid%E8%BF%9E%E6%8E%A5%E6%B1%A0%E4%BB%8B%E7%BB%8D

### 实⽤用的功能：
- 详细的监控
- ExceptionSorter，针对主流数据库的返回码都有⽀支持
- SQL 防注⼊入
- 内置加密配置
- 众多扩展点，⽅便进⾏行定制

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

## 1.5、通过 Spring JDBC 访问数据库

### spring-jdbc 操作类：
- core，JdbcTemplate 等相关核⼼心接⼝和类
- datasource，数据源相关的辅助类
- object，将基本的 JDBC 操作封装成对象
- support，错误码等其他辅助⼯工具

### 常⽤用的 Bean 注解：
- @Component
- @Repository
- @Service
- @Controller
    - @RestController

### 简单的 JDBC 操作
#### JdbcTemplate
- query
- queryForObject
- queryForList
- update
- execute
- batchUpdate
    - BatchPreparedStatementSetter

#### NamedParameterJdbcTemplate 
- batchUpdate
    - SqlParameterSourceUtils.createBatch

## 1.6、Spring 的事物抽象

### 什么是事物？
> 事务是逻辑上的一组操作，要么都执行，要么都不执行。

### 事物的特性（ACID）
- **原子性**： 事务是最小的执行单位，不允许分割；事务的原子性确保动作要么全部完成，要么完全不起作用。
- **一致性**： 执行事务前后，数据保持一致。
- **隔离性**： 并发访问数据库时，一个用户的事物不被其他事物所干扰，各并发事务之间数据库是独立的。
- **持久性**: 一个事务被提交之后。它对数据库中数据的改变是持久的，即使数据库发生故障也不应该对其有任何影响。

### Spring事务管理接口
事务管理，就是按照给定的事务规则来执行提交或者回滚操作。

- ``PlatformTransactionManager`` : 平台事务管理器
- ``TransactionDefinition``： 事务定义信息(事务隔离级别、传播行为、超时、只读、回滚规则)
- ``TransactionStatus``： 事务运行状态

### 相关平台的事物实现
<div align="center"> <img src="../Assets/images/02.spring-transaction.png" width="480px"> </div><br>

### 事务隔离特性
| 隔离性  | 值  | 脏读 | 不可重复读  | 幻读 |
| :--:    | :--: | :--: | :--: | :--: |
|ISOLATION_READ_UNCOMMITTED |  1  | Y | Y | Y |
|ISOLATION_READ_COMMITTED   |  2  | N | Y | Y |
|ISOLATION_REPEATABLE_READ  |  3  | N | N | Y |
|ISOLATION_SERIALIZABLE     |  4  | N | N | N |

### 事务传播特性 

| 传播性   | 值   | 描述 |
| :--:    | :--: | :--: |
|PROPAGATION_REQUIRED     |  0  | 当前有事务就用当前的，没有就⽤用新的 |
|PROPAGATION_SUPPORTS     |  1  | 事务可有可无，不是必须的 |
|PROPAGATION_MANDATORY    |  2  | 当前一定要有事务，不然就抛异常   |
|PROPAGATION_REQUIRES_NEW |  3  | 无论是否有事务，都起个新的事务 |
|PROPAGATION_NOT_SUPPORTED| 4   | 不支持事务，按非事务方式运⾏ |
|PROPAGATION_NEVER        | 5   | 不支持事务，如果有事务则抛异常 |
|PROPAGATION_NESTED       | 6   | 当前有事务就在当前事务里再起⼀一个事务|

### 编程式事务
- TransactionTemplate 
    - TransactionCallback
    - TransactionCallbackWithoutResult

- PlatformTransactionManager 
    - 可以传入TransactionDeﬁnition进行定义

### 申明式事务
- 开启事务注解的⽅方式 ：
    - @EnableTransactionManagement
    - \<tx:annotation-driven/>

- 一些配置:
    - proxyTargetClass
    - mode
    - order

- @Transactional 
    - transactionManager
    - propagation
    - isolation
    - timeout
    - readOnly
    - 怎么判断回滚

## 1.7、Spring 的 JDBC 异常抽象
Spring 会将数据操作的异常转换为 DataAccessException，⽆无论使⽤用何种数据访问⽅方式，都能使⽤用⼀一样的异常。

### JDBC 异常类型
<div align="center"> <img src="../Assets/images/02.DataAccessException.png" width="480px"> </div><br>

### Spring是怎么认识那些错误码的
- 通过 SQLErrorCodeSQLExceptionTranslator 解析错误码 
- ErrorCode 定义 
    - org/springframework/jdbc/support/sql-error-codes.xml Spring自带错误码
    - Classpath 下的 sql-error-codes.xml 用户自定义错误码

### 定制错误码解析逻辑
```Test
<bean id="H2" class="org.springframework.jdbc.support.SQLErrorCodes">
        <property name="badSqlGrammarCodes">
            <value>42000,42001,42101,42102,42111,42112,42121,42122,42132</value>
        </property>
        <property name="duplicateKeyCodes">
            <value>23001,23505</value>
        </property>
        <property name="dataIntegrityViolationCodes">
            <value>22001,22003,22012,22018,22025,23000,23002,23003,23502,23503,23506,23507,23513</value>
        </property>
        <property name="dataAccessResourceFailureCodes">
            <value>90046,90100,90117,90121,90126</value>
        </property>
        <property name="cannotAcquireLockCodes">
            <value>50200</value>
        </property>
        <property name="customTranslations">
            <bean class="org.springframework.jdbc.support.CustomSQLErrorCodesTranslation">
                <property name="errorCodes" value="23001,23505" />
                <property name="exceptionClass"
                          value="com.spring.errorcode.CustomDuplicatedKeyException" />
            </bean>
        </property>
    </bean>
```

# 2、O/R Mapping 实践

## 2.1.认识 Spring Data JPA

### 对象与关系不匹配
|     | Object   | RDBMS |
| :--:     | :--: | :--: |
| 粒度     |  类      | 表   |
| 继承     |  有      | 没有 |
| 唯一性   |  a == b  | 主键 |
| 关联     |  引用    | 外键 |
| 数据访问 |  逐级访问 | 表于表之间的连接 |

### JPA 简介

JPA (``Java Persistence API``) ，Java持久层API，是JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中的持久化模型。
JPA 为对象关系映射提供了一种基于 POJO 的持久化模型 ：

**JPA 包括以下3方面的内容**：

- 一套 API 标准。在 javax.persistence 的包下面，用来操作实体对象，执行 CRUD 操作，框架在后台替代我们完成所有的事情，开发者从烦琐的 JDBC 和 SQL 代码中解脱出来。

- 面向对象的查询语言：Java Persistence QueryLanguage（JPQL）。通过面向对象而非面向数据库的查询语言查询数据，避免程序的SQL语句紧密耦合

- ORM（object/relational metadata）元数据的映射。JPA 支持 XML 和 JDK5.0注 解两种元数据的形式，元数据描述对象和表之间的映射关系，框架据此将实体对象持久化到数据库表中。

### Spring Data 
Spring Data 提供一个大家熟悉的、一致的、基于Spring的数据访问编程模型，同时仍然保留底层数据存储的特殊特性。它可以轻松地让开发者使用数据访问技术，包括关系数据库、非关系数据库（NoSQL）和基于云的数据服务。

Spring Data JPA 是 Spring Data 项目中的一个模块，可以理解为 JPA 规范的再次封装抽象。

# 3、NoSQL 实践


# 4、数据访问进阶



# 参考资料
- [Spring Framework Documentation](https://docs.spring.io/spring/docs/current/spring-framework-reference/)
- [Spring Cloud 中文网](https://www.springcloud.cc/)
- [spring事务管理(详解和实例)](https://www.cnblogs.com/yixianyixian/p/8372832.html)





