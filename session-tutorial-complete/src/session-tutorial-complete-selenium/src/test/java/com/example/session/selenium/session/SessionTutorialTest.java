/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
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
import static org.junit.Assert.assertThat;

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
        defaultLogin();

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

        // move creatAccount page
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

        // click back button
        {
            driver.findElement(By.id("back")).click();
        }

        // check update value
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
        defaultLogin();

        // click the item name
        {
            driver.findElement(By.id("Kokoro")).click();
        }

        // check the display page
        {
            assertThat(driver.findElement(By.id("name")).getText(), is(
                    "Kokoro"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("price")).getText(), anyOf(is(
                    "¥ 900"), is("¥900")));
            assertThat(driver.findElement(By.id("description")).getText(), is(
                    "Souseki Natsume wrote this book"));
        }

        // click the home button
        {
            driver.findElement(By.id("home")).click();
        }

        // update category
        {
            new Select(driver.findElement(By.id("categoryId")))
                    .selectByVisibleText("music");
            driver.findElement(By.id("update")).click();
        }

        // check the display page
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
        // check page
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

    @Test
    public void testChangeAcountInfo() {

        // login
        defaultLogin();

        // click Account Update button
        {
            driver.findElement(By.id("updateAccount")).click();
        }

        // check the form default page
        {
            assertThat(inputFieldAccessor.getValue(By.id("name"), driver), is(
                    "xxx"));
            assertThat(inputFieldAccessor.getValue(By.id("email"), driver), is(
                    "a@b.com"));
            assertThat(inputFieldAccessor.getValue(By.id("birthday"), driver),
                    is("2015-08-01"));
            assertThat(inputFieldAccessor.getValue(By.id("zip"), driver), is(
                    "1111111"));
            assertThat(inputFieldAccessor.getValue(By.id("address"), driver),
                    is("Tokyo"));
        }

        // click the next button
        {
            inputFieldAccessor.overrideValue(By.id("name"), "test", driver);
            inputFieldAccessor.overrideValue(By.id("address"), "Osaka", driver);
            driver.findElement(By.id("next")).click();
        }

        // check initial value
        {
            assertThat(inputFieldAccessor.getValue(By.id("cardNumber"), driver),
                    is("1111111111111111"));
            assertThat(inputFieldAccessor.getValue(By.id("cardExpirationDate"),
                    driver), is("2015-08"));
            assertThat(inputFieldAccessor.getValue(By.id("cardSecurityCode"),
                    driver), is("111"));
        }

        // click the buck button
        {
            driver.findElement(By.id("back")).click();
        }

        // check that the entered value remains
        {
            assertThat(inputFieldAccessor.getValue(By.id("name"), driver), is(
                    "test"));
            assertThat(inputFieldAccessor.getValue(By.id("email"), driver), is(
                    "a@b.com"));
            assertThat(inputFieldAccessor.getValue(By.id("birthday"), driver),
                    is("2015-08-01"));
            assertThat(inputFieldAccessor.getValue(By.id("zip"), driver), is(
                    "1111111"));
            assertThat(inputFieldAccessor.getValue(By.id("address"), driver),
                    is("Osaka"));
        }

        // click the next button
        {
            driver.findElement(By.id("next")).click();
        }

        // click the confirm button
        {
            inputFieldAccessor.overrideValue(By.id("cardNumber"),
                    "1234567890123456", driver);
            driver.findElement(By.id("confirm")).click();
        }

        // check the input value
        {
            assertThat(driver.findElement(By.id("name")).getText(), is("test"));
            assertThat(driver.findElement(By.id("email")).getText(), is(
                    "a@b.com"));
            assertThat(driver.findElement(By.id("birthday")).getText(), is(
                    "2015-08-01"));
            assertThat(driver.findElement(By.id("zip")).getText(), is(
                    "1111111"));
            assertThat(driver.findElement(By.id("address")).getText(), is(
                    "Osaka"));
            assertThat(driver.findElement(By.id("cardNumber")).getText(), is(
                    "****-****-****-3456"));
            assertThat(driver.findElement(By.id("cardExpirationDate"))
                    .getText(), is("2015-08"));
            assertThat(driver.findElement(By.id("cardSecurityCode")).getText(),
                    is("111"));
        }

        // click the buck button
        {
            driver.findElement(By.id("back")).click();
        }

        // check that the entered value remains
        {
            assertThat(inputFieldAccessor.getValue(By.id("cardNumber"), driver),
                    is("1234567890123456"));
            assertThat(inputFieldAccessor.getValue(By.id("cardExpirationDate"),
                    driver), is("2015-08"));
            assertThat(inputFieldAccessor.getValue(By.id("cardSecurityCode"),
                    driver), is("111"));
        }

        // click the confirm button
        {
            driver.findElement(By.id("confirm")).click();
        }

        // click the update button
        {
            driver.findElement(By.id("update")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("name")).getText(), is("test"));
            assertThat(driver.findElement(By.id("email")).getText(), is(
                    "a@b.com"));
            assertThat(driver.findElement(By.id("birthday")).getText(), is(
                    "2015-08-01"));
            assertThat(driver.findElement(By.id("zip")).getText(), is(
                    "1111111"));
            assertThat(driver.findElement(By.id("address")).getText(), is(
                    "Osaka"));
            assertThat(driver.findElement(By.id("cardNumber")).getText(), is(
                    "****-****-****-3456"));
            assertThat(driver.findElement(By.id("cardExpirationDate"))
                    .getText(), is("2015-08"));
            assertThat(driver.findElement(By.id("cardSecurityCode")).getText(),
                    is("111"));
        }

        // click the home button
        {
            driver.findElement(By.id("home")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("userName")).getText(), is(
                    "test"));
        }

        // click the Account Update button
        {
            driver.findElement(By.id("updateAccount")).click();
        }

        // click the next button
        {
            inputFieldAccessor.overrideValue(By.id("name"), "aaaaaa", driver);
            inputFieldAccessor.overrideValue(By.id("address"), "Nagoya",
                    driver);
            driver.findElement(By.id("next")).click();
        }

        // click the home button
        {
            driver.findElement(By.id("home")).click();
        }

        // click the Account Update button
        {
            driver.findElement(By.id("updateAccount")).click();
        }

        // check update value
        {
            assertThat(inputFieldAccessor.getValue(By.id("name"), driver), is(
                    "test"));
            assertThat(inputFieldAccessor.getValue(By.id("email"), driver), is(
                    "a@b.com"));
            assertThat(inputFieldAccessor.getValue(By.id("birthday"), driver),
                    is("2015-08-01"));
            assertThat(inputFieldAccessor.getValue(By.id("zip"), driver), is(
                    "1111111"));
            assertThat(inputFieldAccessor.getValue(By.id("address"), driver),
                    is("Osaka"));
        }

        // Re-update the updated value to its original value
        {
            inputFieldAccessor.overrideValue(By.id("name"), "xxx", driver);
            inputFieldAccessor.overrideValue(By.id("address"), "Tokyo", driver);
            driver.findElement(By.id("next")).click();

            inputFieldAccessor.overrideValue(By.id("cardNumber"),
                    "1111111111111111", driver);
            driver.findElement(By.id("confirm")).click();

            driver.findElement(By.id("update")).click();

            driver.findElement(By.id("home")).click();
        }
    }

    @Test
    public void testAddCartItem() {

        // login
        defaultLogin();

        // click the addCart button
        {
            inputFieldAccessor.overrideValue(By.id("quantity0"), "2", driver);
            driver.findElement(By.id("add0")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("itemName0")).getText(), is(
                    "Kokoro"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText(), anyOf(
                    is("¥ 900"), is("¥900")));
            assertThat(driver.findElement(By.id("itemQuantity0")).getText(), is(
                    "2"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText(), anyOf(
                    is("¥ 1,800"), is("¥1,800")));
        }

        // click the addCart button
        {
            inputFieldAccessor.overrideValue(By.id("quantity1"), "1", driver);
            driver.findElement(By.id("add1")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("itemName0")).getText(), is(
                    "Kokoro"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText(), anyOf(
                    is("¥ 900"), is("¥900")));
            assertThat(driver.findElement(By.id("itemQuantity0")).getText(), is(
                    "2"));
            assertThat(driver.findElement(By.id("itemName1")).getText(), is(
                    "〔Ame ni mo Makezu〕"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice1")).getText(), anyOf(
                    is("¥ 800"), is("¥800")));
            assertThat(driver.findElement(By.id("itemQuantity1")).getText(), is(
                    "1"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText(), anyOf(
                    is("¥ 2,600"), is("¥2,600")));
        }

        // click the Account Update button
        {
            driver.findElement(By.id("updateAccount")).click();
        }

        // click the home button
        {
            driver.findElement(By.id("home")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("itemName0")).getText(), is(
                    "Kokoro"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText(), anyOf(
                    is("¥ 900"), is("¥900")));
            assertThat(driver.findElement(By.id("itemQuantity0")).getText(), is(
                    "2"));
            assertThat(driver.findElement(By.id("itemName1")).getText(), is(
                    "〔Ame ni mo Makezu〕"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice1")).getText(), anyOf(
                    is("¥ 800"), is("¥800")));
            assertThat(driver.findElement(By.id("itemQuantity1")).getText(), is(
                    "1"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText(), anyOf(
                    is("¥ 2,600"), is("¥2,600")));
        }
    }

    @Test
    public void testDeleteCartItem() {

        // login
        defaultLogin();

        // click the addCart button
        {
            inputFieldAccessor.overrideValue(By.id("quantity0"), "2", driver);
            driver.findElement(By.id("add0")).click();
        }

        // click the addCart button
        {
            inputFieldAccessor.overrideValue(By.id("quantity1"), "1", driver);
            driver.findElement(By.id("add1")).click();
        }

        // click the viewCart button
        {
            driver.findElement(By.id("viewCart")).click();
        }

        // check the second item and click the remove button
        {
            driver.findElement(By.id("removedItemsIds1")).click();
            driver.findElement(By.id("remove")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("itemName0")).getText(), is(
                    "Kokoro"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText(), anyOf(
                    is("¥ 900"), is("¥900")));
            assertThat(driver.findElement(By.id("itemQuantity0")).getText(), is(
                    "2"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText(), anyOf(
                    is("¥ 1,800"), is("¥1,800")));
        }

        // Click the remove button without checking deletion
        {
            driver.findElement(By.id("remove")).click();
        }

        // check error message
        {
            assertThat(driver.findElement(By.id("removedItemsIds.errors"))
                    .getText(), is("may not be empty"));
        }
    }

    @Test
    public void testOrderCartItem() {

        // login
        defaultLogin();

        // click the addCart button
        {
            inputFieldAccessor.overrideValue(By.id("quantity0"), "2", driver);
            driver.findElement(By.id("add0")).click();
        }

        // click the addCart button
        {
            inputFieldAccessor.overrideValue(By.id("quantity1"), "1", driver);
            driver.findElement(By.id("add1")).click();
        }

        // click the viewCart button
        {
            driver.findElement(By.id("viewCart")).click();
        }

        // click confirm your order button
        {
            driver.findElement(By.id("confirm")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("itemName0")).getText(), is(
                    "Kokoro"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText(), anyOf(
                    is("¥ 900"), is("¥900")));
            assertThat(driver.findElement(By.id("itemQuantity0")).getText(), is(
                    "2"));
            assertThat(driver.findElement(By.id("itemName1")).getText(), is(
                    "〔Ame ni mo Makezu〕"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice1")).getText(), anyOf(
                    is("¥ 800"), is("¥800")));
            assertThat(driver.findElement(By.id("itemQuantity1")).getText(), is(
                    "1"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText(), anyOf(
                    is("¥ 2,600"), is("¥2,600")));

            assertThat(driver.findElement(By.id("email")).getText(), is(
                    "a@b.com"));

        }

        // click confirm your order button
        {
            driver.findElement(By.id("order")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("orderNumber")).getText()
                    .length(), is(36));

            assertThat(driver.findElement(By.id("itemName0")).getText(), is(
                    "Kokoro"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText(), anyOf(
                    is("¥ 900"), is("¥900")));
            assertThat(driver.findElement(By.id("itemQuantity0")).getText(), is(
                    "2"));
            assertThat(driver.findElement(By.id("itemName1")).getText(), is(
                    "〔Ame ni mo Makezu〕"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice1")).getText(), anyOf(
                    is("¥ 800"), is("¥800")));
            assertThat(driver.findElement(By.id("itemQuantity1")).getText(), is(
                    "1"));
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText(), anyOf(
                    is("¥ 2,600"), is("¥2,600")));
        }

    }

    private void defaultLogin() {
        inputFieldAccessor.overrideValue(By.id("email"), "a@b.com", driver);
        inputFieldAccessor.overrideValue(By.id("password"), "demo", driver);
        driver.findElement(By.id("login")).click();
    }

    @After
    public void tearDown() {

        // click logout button
        {
            driver.findElement(By.id("logout")).click();
        }

    }

}
