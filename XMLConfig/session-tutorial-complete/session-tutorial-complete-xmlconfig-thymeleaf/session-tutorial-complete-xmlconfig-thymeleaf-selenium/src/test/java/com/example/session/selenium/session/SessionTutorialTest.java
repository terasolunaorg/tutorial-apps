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
        defaultLogin();

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

        // move creatAccount page
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

        // check update value
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

        // check update value
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
        defaultLogin();

        // click the item name
        {
            driver.findElement(By.id("Kokoro")).click();
        }

        // check the display page
        {
            assertThat(driver.findElement(By.id("name")).getText()).isEqualTo("Kokoro");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("price")).getText()).isIn("¥ 900", "¥900");
            assertThat(driver.findElement(By.id("description")).getText())
                    .isEqualTo("Souseki Natsume wrote this book");
        }

        // click the home button
        {
            driver.findElement(By.id("home")).click();
        }

        // update category
        {
            new Select(driver.findElement(By.id("categoryId"))).selectByVisibleText("music");
            driver.findElement(By.id("update")).click();
        }

        // check the display page
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
        // check page
        {
            assertThat(driver.findElement(By.id("name")).getText()).isEqualTo("Swan Lake");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("price")).getText()).isIn("¥ 900", "¥900");
            assertThat(driver.findElement(By.id("description")).getText())
                    .isEqualTo("Tchaikovsky composed this music");
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
            assertThat(inputFieldAccessor.getValue(By.id("name"), driver)).isEqualTo("xxx");
            assertThat(inputFieldAccessor.getValue(By.id("email"), driver)).isEqualTo("a@b.com");
            assertThat(inputFieldAccessor.getValue(By.id("birthday"), driver))
                    .isEqualTo("2015-08-01");
            assertThat(inputFieldAccessor.getValue(By.id("zip"), driver)).isEqualTo("1111111");
            assertThat(inputFieldAccessor.getValue(By.id("address"), driver)).isEqualTo("Tokyo");
        }

        // click the next button
        {
            inputFieldAccessor.overrideValue(By.id("name"), "test", driver);
            inputFieldAccessor.overrideValue(By.id("address"), "Osaka", driver);
            driver.findElement(By.id("next")).click();
        }

        // check initial value
        {
            assertThat(inputFieldAccessor.getValue(By.id("cardNumber"), driver))
                    .isEqualTo("1111111111111111");
            assertThat(inputFieldAccessor.getValue(By.id("cardExpirationDate"), driver))
                    .isEqualTo("2015-08");
            assertThat(inputFieldAccessor.getValue(By.id("cardSecurityCode"), driver))
                    .isEqualTo("111");
        }

        // click the buck button
        {
            driver.findElement(By.id("back")).click();
        }

        // check that the entered value remains
        {
            assertThat(inputFieldAccessor.getValue(By.id("name"), driver)).isEqualTo("test");
            assertThat(inputFieldAccessor.getValue(By.id("email"), driver)).isEqualTo("a@b.com");
            assertThat(inputFieldAccessor.getValue(By.id("birthday"), driver))
                    .isEqualTo("2015-08-01");
            assertThat(inputFieldAccessor.getValue(By.id("zip"), driver)).isEqualTo("1111111");
            assertThat(inputFieldAccessor.getValue(By.id("address"), driver)).isEqualTo("Osaka");
        }

        // click the next button
        {
            driver.findElement(By.id("next")).click();
        }

        // click the confirm button
        {
            inputFieldAccessor.overrideValue(By.id("cardNumber"), "1234567890123456", driver);
            driver.findElement(By.id("confirm")).click();
        }

        // check the input value
        {
            assertThat(driver.findElement(By.id("name")).getText()).isEqualTo("test");
            assertThat(driver.findElement(By.id("email")).getText()).isEqualTo("a@b.com");
            assertThat(driver.findElement(By.id("birthday")).getText()).isEqualTo("2015-08-01");
            assertThat(driver.findElement(By.id("zip")).getText()).isEqualTo("1111111");
            assertThat(driver.findElement(By.id("address")).getText()).isEqualTo("Osaka");
            assertThat(driver.findElement(By.id("cardNumber")).getText())
                    .isEqualTo("****-****-****-3456");
            assertThat(driver.findElement(By.id("cardExpirationDate")).getText())
                    .isEqualTo("2015-08");
            assertThat(driver.findElement(By.id("cardSecurityCode")).getText()).isEqualTo("111");
        }

        // click the buck button
        {
            driver.findElement(By.id("back")).click();
        }

        // check that the entered value remains
        {
            assertThat(inputFieldAccessor.getValue(By.id("cardNumber"), driver))
                    .isEqualTo("1234567890123456");
            assertThat(inputFieldAccessor.getValue(By.id("cardExpirationDate"), driver))
                    .isEqualTo("2015-08");
            assertThat(inputFieldAccessor.getValue(By.id("cardSecurityCode"), driver))
                    .isEqualTo("111");
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
            assertThat(driver.findElement(By.id("name")).getText()).isEqualTo("test");
            assertThat(driver.findElement(By.id("email")).getText()).isEqualTo("a@b.com");
            assertThat(driver.findElement(By.id("birthday")).getText()).isEqualTo("2015-08-01");
            assertThat(driver.findElement(By.id("zip")).getText()).isEqualTo("1111111");
            assertThat(driver.findElement(By.id("address")).getText()).isEqualTo("Osaka");
            assertThat(driver.findElement(By.id("cardNumber")).getText())
                    .isEqualTo("****-****-****-3456");
            assertThat(driver.findElement(By.id("cardExpirationDate")).getText())
                    .isEqualTo("2015-08");
            assertThat(driver.findElement(By.id("cardSecurityCode")).getText()).isEqualTo("111");
        }

        // click the home button
        {
            driver.findElement(By.id("home")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("userName")).getText()).isEqualTo("test");
        }

        // click the Account Update button
        {
            driver.findElement(By.id("updateAccount")).click();
        }

        // click the next button
        {
            inputFieldAccessor.overrideValue(By.id("name"), "aaaaaa", driver);
            inputFieldAccessor.overrideValue(By.id("address"), "Nagoya", driver);
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
            assertThat(inputFieldAccessor.getValue(By.id("name"), driver)).isEqualTo("test");
            assertThat(inputFieldAccessor.getValue(By.id("email"), driver)).isEqualTo("a@b.com");
            assertThat(inputFieldAccessor.getValue(By.id("birthday"), driver))
                    .isEqualTo("2015-08-01");
            assertThat(inputFieldAccessor.getValue(By.id("zip"), driver)).isEqualTo("1111111");
            assertThat(inputFieldAccessor.getValue(By.id("address"), driver)).isEqualTo("Osaka");
        }

        // Re-update the updated value to its original value
        {
            inputFieldAccessor.overrideValue(By.id("name"), "xxx", driver);
            inputFieldAccessor.overrideValue(By.id("address"), "Tokyo", driver);
            driver.findElement(By.id("next")).click();

            inputFieldAccessor.overrideValue(By.id("cardNumber"), "1111111111111111", driver);
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
            assertThat(driver.findElement(By.id("itemName0")).getText()).isEqualTo("Kokoro");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText()).isIn("¥ 900", "¥900");
            assertThat(driver.findElement(By.id("itemQuantity0")).getText()).isEqualTo("2");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText()).isIn("¥ 1,800", "¥1,800");
        }

        // click the addCart button
        {
            inputFieldAccessor.overrideValue(By.id("quantity1"), "1", driver);
            driver.findElement(By.id("add1")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("itemName0")).getText()).isEqualTo("Kokoro");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText()).isIn("¥ 900", "¥900");
            assertThat(driver.findElement(By.id("itemQuantity0")).getText()).isEqualTo("2");
            assertThat(driver.findElement(By.id("itemName1")).getText())
                    .isEqualTo("〔Ame ni mo Makezu〕");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice1")).getText()).isIn("¥ 800", "¥800");
            assertThat(driver.findElement(By.id("itemQuantity1")).getText()).isEqualTo("1");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText()).isIn("¥ 2,600", "¥2,600");
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
            assertThat(driver.findElement(By.id("itemName0")).getText()).isEqualTo("Kokoro");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText()).isIn("¥ 900", "¥900");
            assertThat(driver.findElement(By.id("itemQuantity0")).getText()).isEqualTo("2");
            assertThat(driver.findElement(By.id("itemName1")).getText())
                    .isEqualTo("〔Ame ni mo Makezu〕");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice1")).getText()).isIn("¥ 800", "¥800");
            assertThat(driver.findElement(By.id("itemQuantity1")).getText()).isEqualTo("1");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText()).isIn("¥ 2,600", "¥2,600");
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
            assertThat(driver.findElement(By.id("itemName0")).getText()).isEqualTo("Kokoro");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText()).isIn("¥ 900", "¥900");
            assertThat(driver.findElement(By.id("itemQuantity0")).getText()).isEqualTo("2");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText()).isIn("¥ 1,800", "¥1,800");
        }

        // Click the remove button without checking deletion
        {
            driver.findElement(By.id("remove")).click();
        }

        // check error message
        {
            assertThat(driver.findElement(By.id("removedItemsIds.errors")).getText())
                    .isEqualTo("空要素は許可されていません");
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
            assertThat(driver.findElement(By.id("itemName0")).getText()).isEqualTo("Kokoro");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText()).isIn("¥ 900", "¥900");
            assertThat(driver.findElement(By.id("itemQuantity0")).getText()).isEqualTo("2");
            assertThat(driver.findElement(By.id("itemName1")).getText())
                    .isEqualTo("〔Ame ni mo Makezu〕");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice1")).getText()).isIn("¥ 800", "¥800");
            assertThat(driver.findElement(By.id("itemQuantity1")).getText()).isEqualTo("1");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText()).isIn("¥ 2,600", "¥2,600");

            assertThat(driver.findElement(By.id("email")).getText()).isEqualTo("a@b.com");

        }

        // click confirm your order button
        {
            driver.findElement(By.id("order")).click();
        }

        // check update value
        {
            assertThat(driver.findElement(By.id("orderNumber")).getText().length()).isEqualTo(36);

            assertThat(driver.findElement(By.id("itemName0")).getText()).isEqualTo("Kokoro");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice0")).getText()).isIn("¥ 900", "¥900");
            assertThat(driver.findElement(By.id("itemQuantity0")).getText()).isEqualTo("2");
            assertThat(driver.findElement(By.id("itemName1")).getText())
                    .isEqualTo("〔Ame ni mo Makezu〕");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("itemPrice1")).getText()).isIn("¥ 800", "¥800");
            assertThat(driver.findElement(By.id("itemQuantity1")).getText()).isEqualTo("1");
            // differs depending on browser locale.
            assertThat(driver.findElement(By.id("totalPrice")).getText()).isIn("¥ 2,600", "¥2,600");
        }

    }

    private void defaultLogin() {
        inputFieldAccessor.overrideValue(By.id("email"), "a@b.com", driver);
        inputFieldAccessor.overrideValue(By.id("password"), "demo", driver);
        driver.findElement(By.id("login")).click();
    }

    @AfterEach
    public void tearDown() {

        // click logout button
        {
            driver.findElement(By.id("logout")).click();
        }

    }

}
