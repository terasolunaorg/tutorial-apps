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

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
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
            assertThat(driver.findElement(By.id("Kokoro")).getText()).isEqualTo("Kokoro");
            assertThat(driver.findElement(By.xpath("//tbody/tr[3]/td[1]")).getText())
                    .isEqualTo("〔Ame ni mo Makezu〕");
            assertThat(driver.findElement(By.xpath("//tbody/tr[4]/td[1]")).getText())
                    .isEqualTo("Run, Melos!");
            assertThat(driver.findElement(By.id("userName")).getText()).isEqualTo("xxx");

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
            inputFieldAccessor.overrideValue(By.id("email"), "test@xxx.co.jp", driver);
            inputFieldAccessor.overrideValue(By.id("password"), "password", driver);
            inputFieldAccessor.overrideValue(By.id("confirmPassword"), "password", driver);
            inputFieldAccessor.overrideValue(By.id("birthday"), "2016-01-01", driver);
            inputFieldAccessor.overrideValue(By.id("zip"), "1234567", driver);
            inputFieldAccessor.overrideValue(By.id("address"), "Nagoya", driver);
            driver.findElement(By.id("confirm")).click();
        }

        // check the update value
        {
            assertThat(driver.findElement(By.id("name")).getText()).isEqualTo("test");
            assertThat(driver.findElement(By.id("email")).getText()).isEqualTo("test@xxx.co.jp");
            assertThat(driver.findElement(By.id("birthday")).getText()).isEqualTo("2016-01-01");
            assertThat(driver.findElement(By.id("zip")).getText()).isEqualTo("1234567");
            assertThat(driver.findElement(By.id("address")).getText()).isEqualTo("Nagoya");
        }

        // click back button
        {
            driver.findElement(By.id("back")).click();
        }

        // check that the entered value remains
        {
            assertThat(inputFieldAccessor.getValue(By.id("name"), driver)).isEqualTo("test");
            assertThat(inputFieldAccessor.getValue(By.id("email"), driver))
                    .isEqualTo("test@xxx.co.jp");
            assertThat(inputFieldAccessor.getValue(By.id("password"), driver)).isEmpty();
            assertThat(inputFieldAccessor.getValue(By.id("confirmPassword"), driver)).isEmpty();
            assertThat(inputFieldAccessor.getValue(By.id("birthday"), driver))
                    .isEqualTo("2016-01-01");
            assertThat(inputFieldAccessor.getValue(By.id("zip"), driver)).isEqualTo("1234567");
            assertThat(inputFieldAccessor.getValue(By.id("address"), driver)).isEqualTo("Nagoya");
        }

        // click confirm button
        {
            inputFieldAccessor.overrideValue(By.id("password"), "password", driver);
            inputFieldAccessor.overrideValue(By.id("confirmPassword"), "password", driver);
            driver.findElement(By.id("confirm")).click();
        }

        // click create button
        {
            driver.findElement(By.id("create")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("name")).getText()).isEqualTo("test");
            assertThat(driver.findElement(By.id("email")).getText()).isEqualTo("test@xxx.co.jp");
            assertThat(driver.findElement(By.id("birthday")).getText()).isEqualTo("2016-01-01");
            assertThat(driver.findElement(By.id("zip")).getText()).isEqualTo("1234567");
            assertThat(driver.findElement(By.id("address")).getText()).isEqualTo("Nagoya");
        }

        // click Login page button
        {
            driver.findElement(By.id("home")).click();
        }

        // login
        {
            inputFieldAccessor.overrideValue(By.id("email"), "test@xxx.co.jp", driver);
            inputFieldAccessor.overrideValue(By.id("password"), "password", driver);
            driver.findElement(By.id("login")).click();
        }

        // check initial value
        {
            assertThat(driver.findElement(By.id("Kokoro")).getText()).isEqualTo("Kokoro");
            assertThat(driver.findElement(By.xpath("//tbody/tr[3]/td[1]")).getText())
                    .isEqualTo("〔Ame ni mo Makezu〕");
            assertThat(driver.findElement(By.xpath("//tbody/tr[4]/td[1]")).getText())
                    .isEqualTo("Run, Melos!");
            assertThat(driver.findElement(By.id("userName")).getText()).isEqualTo("test");

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
            assertThat(driver.findElement(By.id("name")).getText()).isEqualTo("Kokoro");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("price")).getText()).isIn("¥ 900", "¥900");
            assertThat(driver.findElement(By.id("description")).getText())
                    .isEqualTo("Souseki Natsume wrote this book");
        }

        // click home button
        {
            driver.findElement(By.id("home")).click();
        }

        // update the category
        {
            new Select(driver.findElement(By.id("categoryId"))).selectByVisibleText("music");
            driver.findElement(By.id("update")).click();
        }

        // display
        {
            assertThat(driver.findElement(By.xpath("//tbody/tr[2]/td[1]")).getText())
                    .isEqualTo("Symphony No. 5 in C minor (Fate)");
            assertThat(driver.findElement(By.xpath("//tbody/tr[3]/td[1]")).getText())
                    .isEqualTo("Eine kleine Nachtmusik");
            assertThat(driver.findElement(By.xpath("//tbody/tr[4]/td[1]")).getText())
                    .isEqualTo("Swan Lake");
        }

        // click the item name
        {
            driver.findElement(By.linkText("Swan Lake")).click();
        }

        // check the display
        {
            assertThat(driver.findElement(By.id("name")).getText()).isEqualTo("Swan Lake");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("price")).getText()).isIn("¥ 900", "¥900");
            assertThat(driver.findElement(By.id("description")).getText())
                    .isEqualTo("Tchaikovsky composed this music");
        }

    }

    @AfterEach
    public void tearDown() {

        // click logout button
        {
            driver.findElement(By.id("logout")).click();
        }

    }

}
