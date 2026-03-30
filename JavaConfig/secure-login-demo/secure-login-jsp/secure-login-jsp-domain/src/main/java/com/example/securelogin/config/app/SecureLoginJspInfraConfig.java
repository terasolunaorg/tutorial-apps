package com.example.securelogin.config.app;

import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.securelogin.config.app.mybatis.MybatisConfig;
import com.icegreen.greenmail.spring.GreenMailBean;;

/**
 * Bean definitions for infrastructure layer.
 */
@Configuration
@MapperScan("com.example.securelogin.domain.repository")
@Import({SecureLoginJspEnvConfig.class})
public class SecureLoginJspInfraConfig {

    /**
     * Configure {@link SqlSessionFactoryBean} bean.
     * @param dataSource DataSource
     * @see com.example.securelogin.config.app.SecureLoginJspEnvConfig#dataSource()
     * @return Bean of configured {@link SqlSessionFactoryBean}
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(MybatisConfig.configuration());
        return bean;
    }

    /**
     * Configure {@link GreenMailBean} bean.
     * @return Bean of configured {@link GreenMailBean}
     */
    @Bean(name = "greenMailBean")
    public GreenMailBean greenMailBean() {
        return new GreenMailBean();
    }
}
