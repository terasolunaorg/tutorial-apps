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
package org.terasoluna.gfw.tutorial.selenium;

import org.springframework.context.support.ApplicationObjectSupport;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerConfigurer extends ApplicationObjectSupport {

    private String propertyFileLocation;

    public void setPropertyFileLocation(String propertyFileLocation) {
        this.propertyFileLocation = propertyFileLocation;
    }

    public void setUp() {
        if (propertyFileLocation != null) {
            WebDriverManager.firefoxdriver().config().setProperties(
                    propertyFileLocation);
        }

        for (String activeProfile : getApplicationContext().getEnvironment()
                .getActiveProfiles()) {
            if ("chrome".equals(activeProfile)) {
                if (System.getenv("webdriver.chrome.driver") == null) {
                    WebDriverManager.chromedriver().setup();
                }
                return;
            } else if ("ie".equals(activeProfile)) {
                if (System.getenv("webdriver.ie.driver") == null) {
                    WebDriverManager.iedriver().setup();
                }
                return;
            } else if ("phantomJs".equals(activeProfile)) {
                return;
            }
        }

        // The default browser is Firefox
        if (System.getenv("webdriver.gecko.driver") == null) {
            WebDriverManager.firefoxdriver().setup();
        }
    }
}
