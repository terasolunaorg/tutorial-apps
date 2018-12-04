/*
 * Copyright (C) 2013-2018 NTT DATA Corporation
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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.JavascriptExecutor;

public class WaitWebDriverEventListener extends WebDriverEventListenerAdapter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Wait<WebDriver> wait;

    @Value("${selenium.webDriverWait}")
    protected long webDriverWait;

    @Value("${selenium.webDriverSleepWait}")
    protected long webDriverSleepWait;

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        try {
            wait = new WebDriverWait(driver, webDriverWait, webDriverSleepWait);
            wait.until(
                    (ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                            .executeScript("return document.readyState").equals(
                                    "loading"));
            wait.until(
                    (ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                            .executeScript("return document.readyState").equals(
                                    "complete"));
        } catch (TimeoutException e) {
            logger.warn("loading is not done");
        }
    }

}
