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
package com.example.session.selenium.session;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Value;
import org.terasoluna.gfw.tutorial.selenium.FunctionTestSupport;

public class SessionTutorialTest extends FunctionTestSupport {

    @Value("${selenium.applicationContextUrl}/loginForm")
    String baseUrl;

    @Test
    public void testInitialLogin() {

        // login
        {
            inputFieldAccessor.overrideValue(By.id("email"), "a@b.com", driver);
            inputFieldAccessor.overrideValue(By.id("password"), "demo", driver);
            driver.findElement(By.id("login")).click();
        }

        // check initial value
        {
            assertThat(driver.findElement(By.id("Kokoro")).getText(), is(
                    "Kokoro"));
            assertThat(driver.findElement(By.xpath("//tbody/tr[3]/td[1]"))
                    .getText(), is("〔Ame ni mo Makezu〕"));
            assertThat(driver.findElement(By.xpath("//tbody/tr[4]/td[1]"))
                    .getText(), is("Run, Melos!"));
            assertThat(driver.findElement(By.id("userName")).getText(), is(
                    "xxx"));

        }
    }

    @Test
    public void testCreateAccount() {

        // move createAccount page
        {
            driver.findElement(By.id("createAccount")).click();
        }

        // input the form value and click confirm button
        {
            inputFieldAccessor.overrideValue(By.id("name"), "test", driver);
            inputFieldAccessor.overrideValue(By.id("email"), "test@xxx.co.jp",
                    driver);
            inputFieldAccessor.overrideValue(By.id("password"), "password",
                    driver);
            inputFieldAccessor.overrideValue(By.id("confirmPassword"),
                    "password", driver);
            inputFieldAccessor.overrideValue(By.id("birthday"), "2016-01-01",
                    driver);
            inputFieldAccessor.overrideValue(By.id("zip"), "1234567", driver);
            inputFieldAccessor.overrideValue(By.id("address"), "Nagoya",
                    driver);
            driver.findElement(By.id("confirm")).click();
        }

        // check the update value
        {
            assertThat(driver.findElement(By.id("name")).getText(), is("test"));
            assertThat(driver.findElement(By.id("email")).getText(), is(
                    "test@xxx.co.jp"));
            assertThat(driver.findElement(By.id("birthday")).getText(), is(
                    "2016-01-01"));
            assertThat(driver.findElement(By.id("zip")).getText(), is(
                    "1234567"));
            assertThat(driver.findElement(By.id("address")).getText(), is(
                    "Nagoya"));
        }

        // click back button
        {
            driver.findElement(By.id("back")).click();
        }

        // check that the entered value remains
        {
            assertThat(inputFieldAccessor.getValue(By.id("name"), driver), is(
                    "test"));
            assertThat(inputFieldAccessor.getValue(By.id("email"), driver), is(
                    "test@xxx.co.jp"));
            assertThat(inputFieldAccessor.getValue(By.id("password"), driver),
                    is(""));
            assertThat(inputFieldAccessor.getValue(By.id("confirmPassword"),
                    driver), is(""));
            assertThat(inputFieldAccessor.getValue(By.id("birthday"), driver),
                    is("2016-01-01"));
            assertThat(inputFieldAccessor.getValue(By.id("zip"), driver), is(
                    "1234567"));
            assertThat(inputFieldAccessor.getValue(By.id("address"), driver),
                    is("Nagoya"));
        }

        // click confirm button
        {
            inputFieldAccessor.overrideValue(By.id("password"), "password",
                    driver);
            inputFieldAccessor.overrideValue(By.id("confirmPassword"),
                    "password", driver);
            driver.findElement(By.id("confirm")).click();
        }

        // click create button
        {
            driver.findElement(By.id("create")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("name")).getText(), is("test"));
            assertThat(driver.findElement(By.id("email")).getText(), is(
                    "test@xxx.co.jp"));
            assertThat(driver.findElement(By.id("birthday")).getText(), is(
                    "2016-01-01"));
            assertThat(driver.findElement(By.id("zip")).getText(), is(
                    "1234567"));
            assertThat(driver.findElement(By.id("address")).getText(), is(
                    "Nagoya"));
        }

        // click Login page button
        {
            driver.findElement(By.id("home")).click();
        }

        // login
        {
            inputFieldAccessor.overrideValue(By.id("email"), "test@xxx.co.jp",
                    driver);
            inputFieldAccessor.overrideValue(By.id("password"), "password",
                    driver);
            driver.findElement(By.id("login")).click();
        }

        // check initial value
        {
            assertThat(driver.findElement(By.id("Kokoro")).getText(), is(
                    "Kokoro"));
            assertThat(driver.findElement(By.xpath("//tbody/tr[3]/td[1]"))
                    .getText(), is("〔Ame ni mo Makezu〕"));
            assertThat(driver.findElement(By.xpath("//tbody/tr[4]/td[1]"))
                    .getText(), is("Run, Melos!"));
            assertThat(driver.findElement(By.id("userName")).getText(), is(
                    "test"));

        }
    }

    @Test
    public void testShowGoods() {

        // login
        testInitialLogin();

        // click the item name
        {
            driver.findElement(By.id("Kokoro")).click();
        }

        // check the display
        {
            assertThat(driver.findElement(By.id("name")).getText(), is(
                    "Kokoro"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("price")).getText(), anyOf(is(
                    "¥ 900"), is("¥900")));
            assertThat(driver.findElement(By.id("description")).getText(), is(
                    "Souseki Natsume wrote this book"));
        }

        // click home button
        {
            driver.findElement(By.id("home")).click();
        }

        // update the category
        {
            new Select(driver.findElement(By.id("categoryId")))
                    .selectByVisibleText("music");
            driver.findElement(By.id("update")).click();
        }

        // display
        {
            assertThat(driver.findElement(By.xpath("//tbody/tr[2]/td[1]"))
                    .getText(), is("Symphony No. 5 in C minor (Fate)"));
            assertThat(driver.findElement(By.xpath("//tbody/tr[3]/td[1]"))
                    .getText(), is("Eine kleine Nachtmusik"));
            assertThat(driver.findElement(By.xpath("//tbody/tr[4]/td[1]"))
                    .getText(), is("Swan Lake"));
        }

        // click the item name
        {
            driver.findElement(By.linkText("Swan Lake")).click();
        }

        // check the display
        {
            assertThat(driver.findElement(By.id("name")).getText(), is(
                    "Swan Lake"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("price")).getText(), anyOf(is(
                    "¥ 900"), is("¥900")));
            assertThat(driver.findElement(By.id("description")).getText(), is(
                    "Tchaikovsky composed this music"));
        }

    }

    @After
    public void tearDown() {

        // click logout button
        {
            driver.findElement(By.id("logout")).click();
        }

    }

}
