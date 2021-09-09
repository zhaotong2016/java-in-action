# tcc-in-action


#微服务实例和服务治理中心的关系
##Eureka客户端的请求类型包括注册
##续约
##下线

#服务治理中心
##相互复制,保证高可用和高性能
##服务剔除,当微服务实例出现网络故障、内存溢出或者宕机而导致服务不能正常工作，Eureka服务将无效的服务实例剔除出去，Eureka Server在启动时，会创建一个定时任务，在默认的情况下，每间隔60秒就会更新一次微服务实例的清单，只要发现有超过90秒没有完成续约的实例，就会将其剔除出去
##自我保护


#微服务之间的相互调用

##服务获取,启动微服务实例的时候，它就会以一个时间间隔（默认是30秒）向Eureka服务治理中心发送REST风格请求，获取一份只读的服务实例清单，跟着进行缓存
##服务调用,




`
eureka:
  client:
    region: China
    avaliability-zones: shenzheng    
`
###通过Region和Zone概念的设计，可以将机房设置在不同的地区，从而解决距离问题和各个地区业务的差异，进一步提高微服务系统的响应能力和灵活性。


`
@EnableDiscoveryClient
   DiscoveryClient 接口
        CompositeDiscoveryClient
        EurekaDiscoveryClient       
        NoopDiscoveryClient
        SimpleDiscoveryClient
   EurekaClientConfig接口
`



#Springboot
    脚手架

##发展历史
    servlet - ejb- struts - springmvc -> springboot

##启动流程
##自动装配 
    @EnableAutoConfiguration
        org.springframework.boot.autoconfigure.EnableAutoConfiguration
        org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#getCandidateConfigurations
        org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#getSpringFactoriesLoaderFactoryClass
        spring-boot-autoconfigure-2.1.1.RELEASE.jar /METE-INF/spring-factories => EnableAutoConfiguration

#声明Bean的方式
    1、xml
    2、注解  
    3、java config

#Bean生命周期
##Bean执行流程
    解析定义信息BeanDefinitionReader=> 
    Bean信息定义BeanDefinition =>
        BeanFactoryPostProcessor(扩展)=> 
    BeanFatory容器(反射) => 
        BeanPostProcessor(扩展) =》
    Bean实例化 => 
    Bean初始化 =>
    context.getBean(xx.class)
    Bean销毁 =>
##Bean源码解析
    public    org.springframework.context.support.AbstractApplicationContext#refresh
    
    创建bean工厂
    protected org.springframework.context.support.AbstractApplicationContext#obtainFreshBeanFactory=>
    protected org.springframework.context.support.AbstractRefreshableApplicationContext#refreshBeanFactory
    
    protected org.springframework.context.support.AbstractApplicationContext#finishBeanFactoryInitialization
    public    org.springframework.beans.factory.support.DefaultListableBeanFactory#preInstantiateSingletons
    public    org.springframework.beans.factory.support.AbstractBeanFactory#getBean(java.lang.String)
    
    DefaultSingletonBeanRegistry.getSingleton(java.lang.String)
    DefaultSingletonBeanRegistry.getSingleton(java.lang.String, org.springframework.beans.factory.ObjectFactory<?>)
    
    protected org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean
    1、创建Bean
    protected org.springframework.beans.factory.support.AbstractBeanFactory#createBean
    protected org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean
    2、属性填充
    protected org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#populateBean
    3、初始化
    org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)
        前置处理
        org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization
        调用初始化方法
        org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods
        后置处理
        org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization
    4、添加到单例池中
        
#PostProcessor 后置 处理器 增强器

    1、BeanFactoryPostProcessor
        --PlaceholderConfigurerSupport ${jdbc.url.xx}
        对当前定义的Bean信息进行扩展扩展
    
    2、BeanPostProcessor 增强
   


#BeanFactory和FactoryBean 
    都是用来生成对象的，当生成一个复杂唯一的对象时，使用FactoryBean生成特殊对象，不用工厂bean （openfegin）

##BeanFactory
    生成一堆对象的模板
##FactoryBean 
    生成独特 复杂属性的对象 例如： MapperFactoryBean 


#Environment

    监听器 观察者模式


#Spring 扩展点

##方式一、使用 @Import ImportBeanDefinitionRegistrar @MapperScan spring实现

    Mybatis-spring扩展方式
    1、@MapperScan MapperScannerRegistrar
    2、MapperFactoryBean 实现 FactoryBean接口 getObject() 方法 流程
        1、org.mybatis.spring.mapper.MapperFactoryBean#getObject()
        2、org.apache.ibatis.session.defaults.DefaultSqlSession#getMapper(java.lang.Class)
        3、org.apache.ibatis.session.defaults.DefaultSqlSession#getMapper(java.lang.Class)
        4、org.apache.ibatis.session.Configuration#getMapper(java.lang.Class, org.apache.ibatis.session.SqlSession)
        5、org.apache.ibatis.binding.MapperRegistry#getMapper(java.lang.Class, org.apache.ibatis.session.SqlSession)
        6、org.apache.ibatis.binding.MapperProxyFactory#newInstance(org.apache.ibatis.session.SqlSession)  使用动态代理


    Feign
    @EnableFeignClients =>@Import(FeignClientsRegistrar.class) 
    FeignClientsRegistrar 
##方式二 使用BeanDefinitionRegistryPostProcessor    @Mapper springboot实现方式     

ApplicationListener 

BeanFactoryPostProcessor 
    注册bean ConfigurationClassPostProcessor 解析@Bean @CompontScan @ImportResource 自定装配

BeanPostProcessor 
    aop hystrix 
    
    
SmartLifeCycle


spring ioc容器
循环依赖

1、死循环问题
2、AOP代理

获取bean->bean工厂（ObjectFactory）-> bean单例池(SingletonBeanRegistry)

getBean -> 实例化 -> 属性填充 -> 初始化 -> 存入 bean单例池

二级缓存解决死循环问题，无法解决AOP问题


三级缓存proxy$a
com.hunter.pipeline.proxy$b




##MySQL
InnonDB

磁盘->内存

行结构

	

页结构


savepoint

保存点

修改隔离级别
mysql > set session transaction isolation level read uncommitted

查询隔离级别
mysql > select @@tx_isolation

1、read uncommitted 一个事务可以读到其他事务还没有提交的数据，会出现脏读

2、read commintted  一个事务只能读到另外一个事务已提交的数据，该数据多次被其他事务修改并提交，该事务能读到最新的数据，会出现不可重复读，幻读
	1、不可重复读：开启两个事务，一个事务对数据多次修改多次提交，另外一个事务 多次查询 出现结果不一样
	2、幻读：开启两个事务，第一个事务对数据范围进行查询，第二个事务对数据新增数据并提交，第一个事务再次查询 则数据和第一次查询数据不一样
3、可重复读
mysql > set session transaction isolation level repeatable read  会出现幻读
	 两个事务，第一个事务对数据查询，第二个事务对数据修改并提交，第一个事务再次查询和第一次查询一样



格式

列名 是否必须 占用空间 描述

row_id 唯一标识一条记录
transaction_id 事务id
roll_pointer 回滚指针