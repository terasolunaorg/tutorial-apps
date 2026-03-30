/*
 * Copyright(c) 2024 NTT DATA Group Corporation. Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.todo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.terasoluna.gfw.tutorial.selenium.PageSource;
import org.terasoluna.gfw.tutorial.selenium.ScreenCapture;
import org.terasoluna.gfw.tutorial.selenium.webdrivers.ChromeDriverFactoryBean;
import org.terasoluna.gfw.tutorial.selenium.webdrivers.EdgeDriverFactoryBean;
import org.terasoluna.gfw.tutorial.selenium.webdrivers.FirefoxDriverFactoryBean;

/**
 * Bean definition to SeleniumContext configure .
 */
@Configuration
@EnableTransactionManagement
public class SeleniumContextConfig {

    /**
     * selenium.headless property.
     */
    @Value("${selenium.headless}")
    private boolean headless;

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
     * Configure {@link ScreenCapture} bean.
     * @return Bean of configured {@link ScreenCapture}
     */
    @Bean("screenCapture")
    public ScreenCapture screenCapture() {
        return new ScreenCapture();
    }

    /**
     * Configure {@link PageSource} bean.
     * @return Bean of configured {@link PageSource}
     */
    @Bean("pageSource")
    public PageSource pageSource() {
        return new PageSource();
    }

    /**
     * Configure {@link FirefoxDriverFactoryBean} bean.
     * @return Bean of configured {@link FirefoxDriverFactoryBean}
     */
    @Bean("webDriver")
    @Profile({"firefox", "default"})
    @Scope("prototype")
    public FirefoxDriverFactoryBean firefoxDriverFactoryBean() {
        FirefoxDriverFactoryBean bean = new FirefoxDriverFactoryBean();
        bean.setPropertyFileLocation("wdm.properties");
        bean.setHeadless(this.headless);
        return bean;
    }

    /**
     * Configure {@link EdgeDriverFactoryBean} bean.
     * @return Bean of configured {@link EdgeDriverFactoryBean}
     */
    @Bean("webDriver")
    @Profile("edge")
    @Scope("prototype")
    public EdgeDriverFactoryBean edgeDriverFactoryBean() {
        EdgeDriverFactoryBean bean = new EdgeDriverFactoryBean();
        bean.setPropertyFileLocation("wdm.properties");
        bean.setHeadless(this.headless);
        return bean;
    }

    /**
     * Configure {@link ChromeDriverFactoryBean} bean.
     * @return Bean of configured {@link ChromeDriverFactoryBean}
     */
    @Bean("webDriver")
    @Profile("chrome")
    @Scope("prototype")
    public ChromeDriverFactoryBean chromeDriverFactoryBean() {
        ChromeDriverFactoryBean bean = new ChromeDriverFactoryBean();
        bean.setPropertyFileLocation("wdm.properties");
        bean.setHeadless(this.headless);
        return bean;
    }

}
