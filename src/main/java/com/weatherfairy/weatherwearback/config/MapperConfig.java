package com.weatherfairy.weatherwearback.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.weatherfairy.weatherwearback" , annotationClass = Mapper.class)
public class MapperConfig {

    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, Resource[] mapperLocations, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        Resource[] arrResource = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*Mapper.xml");

        return sqlSessionFactory(dataSource, arrResource, applicationContext);
    }

    @Bean("SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}