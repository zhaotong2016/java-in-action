package com.hunter.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//@MapperScan("com.hunter.com.hunter.demo.com.hunter.demo.mapper")
@Configuration
@ComponentScan("com.hunter.demo")
@EnableTransactionManagement //开启事务支持
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.hunter.demo.mapper")
//@Import(MyImportBeanDefinitionRegister.class) 使用@MyMapperScan 自定义MyMapperScan中引入
//@MyMapperScan //使用自定义注解 替换 @Import(MyImportBeanDefinitionRegister.class)
public class AppConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public DataSource dataSource(){
        String url ="jdbc:mysql://192.169.8.128:13306/db249?characterEncoding=UTF-8";
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url,
                "root","123456");
        return dataSource;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 扩展点 @Import ImportBeanDefinitionRegistrar
     * Mybatis-spring扩展方式
     * 1、@MapperScan MapperScannerRegistrar
     * 2、MapperFactoryBean 实现 FactoryBean接口
     *      getObject() 方法 流程
     *          1、org.mybatis.spring.mapper.MapperFactoryBean#getObject()
     *          2、org.apache.ibatis.session.defaults.DefaultSqlSession#getMapper(java.lang.Class)
     *          3、org.apache.ibatis.session.defaults.DefaultSqlSession#getMapper(java.lang.Class)
     *          4、org.apache.ibatis.session.Configuration#getMapper(java.lang.Class, org.apache.ibatis.session.SqlSession)
     *          5、org.apache.ibatis.binding.MapperRegistry#getMapper(java.lang.Class, org.apache.ibatis.session.SqlSession)
     *          6、org.apache.ibatis.binding.MapperProxyFactory#newInstance(org.apache.ibatis.session.SqlSession)  使用动态代理
     */
    /*@Bean
    public MapperFactoryBean<UserMapper> userMapper() throws Exception {
        MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactory());
        return factoryBean;
    }*/
}
