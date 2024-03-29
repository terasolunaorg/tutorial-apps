/*
 * Copyright(c) 2024 NTT DATA Group Corporation. Copyright(c) 2013 NTT Corporation.
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
package com.example.securelogin.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientExtensionConfig {

    @Value("${selenium.restOperations.connectTimeout:-1}")
    private int connectTimeout;

    @Value("${selenium.restOperations.readTimeout:-1}")
    private int readTimeout;

    @Bean("restOperations")
    public RestTemplate restTemplate(
            @Qualifier("clientHttpRequestFactory") SimpleClientHttpRequestFactory simpleClientHttpRequestFactory) {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(simpleClientHttpRequestFactory);
        return bean;
    }

    @Bean("clientHttpRequestFactory")
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory bean = new SimpleClientHttpRequestFactory();
        bean.setConnectTimeout(connectTimeout);
        bean.setReadTimeout(readTimeout);
        // bean.setConnectTimeout(Duration.ofMillis(connectTimeout));
        // bean.setReadTimeout(Duration.ofMillis(readTimeout));
        return bean;
    }

}
