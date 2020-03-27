package com.wbst.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.wbst.mapper.visitorMapper",sqlSessionFactoryRef = "visitorSqlSessionFactory")
public class VisitorDataSourceConfig {



    @Bean(name = "visitorDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.visitor")
    public DataSource getDateSource2() {
        return DataSourceBuilder.create().build();
    }




    @Bean(name = "visitorSqlSessionFactory")
    public SqlSessionFactory test2SqlSessionFactory(@Qualifier("visitorDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/visitorMapper/*.xml"));
        return bean.getObject();
    }




    @Bean(name = "visitorTransactionManager")
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("visitorDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }





    @Bean("visitorSqlSessionTemplate")
    public SqlSessionTemplate test2sqlsessiontemplate(
            @Qualifier("visitorSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }

}
