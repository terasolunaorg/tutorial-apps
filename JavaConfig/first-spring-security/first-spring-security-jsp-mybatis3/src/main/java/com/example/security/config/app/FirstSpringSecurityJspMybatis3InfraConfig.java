package com.example.security.config.app;

import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.security.config.app.mybatis.MybatisConfig;

/**
 * Bean definitions for infrastructure layer.
 */
@Configuration
@MapperScan("com.example.security.domain.repository")
@Import({FirstSpringSecurityJspMybatis3EnvConfig.class})
public class FirstSpringSecurityJspMybatis3InfraConfig {

    /**
     * Configure {@link SqlSessionFactoryBean} bean.
     * @param dataSource DataSource
     * @see com.example.security.config.app.FirstSpringSecurityJspMybatis3EnvConfig#dataSource()
     * @return Bean of configured {@link SqlSessionFactoryBean}
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(MybatisConfig.configuration());
        return bean;
    }
}
