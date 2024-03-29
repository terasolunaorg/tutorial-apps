package com.example.securelogin.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.terasoluna.gfw.tutorial.selenium.DBLog;
import org.terasoluna.gfw.tutorial.selenium.DBLogAssertOperations;
import org.terasoluna.gfw.tutorial.selenium.DBLogCleaner;
import org.terasoluna.gfw.tutorial.selenium.PageSource;
import org.terasoluna.gfw.tutorial.selenium.ScreenCapture;
import org.terasoluna.gfw.tutorial.selenium.WebDriverCreator;
import org.terasoluna.gfw.tutorial.selenium.WebDriverManagerConfigurer;

/**
 * Bean definition to SeleniumContext configure .
 */
@Configuration
@EnableTransactionManagement
public class SeleniumContextConfig {

    /**
     * selenium.dbHost property.
     */
    @Value("${selenium.dbHost}")
    private String dbHost;

    /**
     * selenium.dbPort property.
     */
    @Value("${selenium.dbPort}")
    private String dbPort;

    /**
     * selenium.dbResetScript property.
     */
    @Value("${selenium.dbResetScript}")
    private String dbResetScript;

    /**
     * selenium.restOperations.connectTimeout property.
     */
    @Value("${selenium.restOperations.connectTimeout:-1}")
    private int connectTimeout;

    /**
     * selenium.restOperations.readTimeout property.
     */
    @Value("${selenium.restOperations.readTimeout:-1}")
    private String readTimeout;

    /**
     * Configure {@link PropertySourcesPlaceholderConfigurer} bean.
     * @param properties Path where the property file is located
     * @return Bean of configured {@link PropertySourcesPlaceholderConfigurer}
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(
            @Value("classpath*:META-INF/spring/*.properties") Resource... properties) {
        PropertySourcesPlaceholderConfigurer bean = new PropertySourcesPlaceholderConfigurer();
        bean.setLocations(properties);
        return bean;
    }

    /**
     * Configure the {@link DataSource} bean.
     * @return Bean of configured {@link BasicDataSource}
     */
    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource bean = new BasicDataSource();
        bean.setDriverClassName("org.h2.Driver");
        bean.setUrl("jdbc:h2:tcp://" + dbHost + ":" + dbPort
                + "/mem:secure-login-thymeleaf");
        bean.setUsername("sa");
        bean.setPassword("");
        bean.setDefaultAutoCommit(false);
        return bean;
    }

    /**
     * Configure the {@link TransactionManager} bean.
     * @return Bean of configured {@link DataSourceTransactionManager}
     */
    @Bean
    public TransactionManager transactionManager() {
        DataSourceTransactionManager bean = new DataSourceTransactionManager();
        bean.setDataSource(dataSource());
        return bean;
    }

    /**
     * Generate {@link ClassPathResource} bean.
     * @return Generated {@link ClassPathResource}
     */
    @Bean("dbResetScript")
    public ClassPathResource classPathResource() {
        return new ClassPathResource(dbResetScript);
    }

    /**
     * Generate {@link ResourceDatabasePopulator} bean.
     * @return Generated {@link ResourceDatabasePopulator}
     */
    @Bean("resourceDatabasePopulator")
    public ResourceDatabasePopulator resourceDatabasePopulator() {
        return new ResourceDatabasePopulator(classPathResource());
    }

    /**
     * Configure the {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("restOperations")
    public RestTemplate restOperations() {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(simpleClientHttpRequestFactory());
        return bean;
    }

    /**
     * Configure the {@link SimpleClientHttpRequestFactory} bean.
     * @return Bean of configured {@link SimpleClientHttpRequestFactory}
     */
    @Bean
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory bean = new SimpleClientHttpRequestFactory();
        bean.setConnectTimeout(connectTimeout);
        bean.setReadTimeout(connectTimeout);
        return bean;
    }

    /**
     * Configure the {@link DBLogAssertOperations}.
     * @return Bean of configured {@link DBLogAssertOperations}
     */
    @Bean(name = "dbLogAssertOperations")
    public DBLogAssertOperations dbLogAssertOperations() {
        DBLogAssertOperations bean = new DBLogAssertOperations(jdbcTemplate());
        return bean;
    }

    /**
     * Configure the {@link JdbcTemplate} bean.
     * @return Bean of configured {@link JdbcTemplate}
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate bean = new JdbcTemplate();
        bean.setDataSource(dataSource());
        bean.setFetchSize(100);
        return bean;
    }

    /**
     * Generate {@link ScreenCapture}.
     * @return Generated {@link ScreenCapture}
     */
    @Bean("screenCapture")
    public ScreenCapture screenCapture() {
        return new ScreenCapture();
    }

    /**
     * Configure the {@link DBLog} bean.
     * @return Bean of configured {@link DBLog}
     */
    @Bean("dbLog")
    public DBLog dbLog() {
        DBLog bean = new DBLog();
        bean.setDataSource(dataSource());
        return bean;
    }

    /**
     * Generate {@link PageSource} bean.
     * @return Generated {@link PageSource}
     */
    @Bean("pageSource")
    public PageSource pageSource() {
        return new PageSource();
    }

    /**
     * Configure the {@link DBLogCleaner} bean.
     * @return Bean of configured {@link DBLogCleaner}
     */
    @Bean("dbLogCleaner")
    public DBLogCleaner dbLogCleaner() {
        DBLogCleaner bean = new DBLogCleaner();
        bean.setDataSource(dataSource());
        return bean;
    }

    /**
     * Generate {@link WebDriverCreator} bean.
     * @return Generated {@link WebDriverCreator}
     */
    @Bean
    public WebDriverCreator webDriverCreator() {
        return new WebDriverCreator();
    }

    /**
     * Configure the {@link WebDriverManagerConfigurer} bean.
     * @return Bean of configured {@link WebDriverManagerConfigurer}
     */
    @Bean
    public WebDriverManagerConfigurer webDriverManagerConfigurer() {
        WebDriverManagerConfigurer bean = new WebDriverManagerConfigurer();
        bean.setPropertyFileLocation("wdm.properties");
        return bean;
    }


    /**
     * Configure {@link WebDriver} bean.
     * @return Bean of configured {@link FirefoxDriver}
     */
    @Bean("webDriver")
    @Scope("prototype")
    public WebDriver webDriver() {
        return new FirefoxDriver();
    }

}