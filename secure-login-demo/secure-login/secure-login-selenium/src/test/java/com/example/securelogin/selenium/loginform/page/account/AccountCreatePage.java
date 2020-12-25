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
package com.example.securelogin.selenium.loginform.page.account;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.terasoluna.gfw.tutorial.selenium.WebDriverOperations;
import com.example.securelogin.selenium.loginform.page.AbstractPageObject;

import static org.openqa.selenium.By.id;

public class AccountCreatePage extends AbstractPageObject {

    ResourceLoader resourceLoader;

    {
        url = "/accounts/create?form";
    }

    public AccountCreatePage(WebDriverOperations webDriverOperations,
            String applicationContextURL, ResourceLoader resourceLoader) {
        super(webDriverOperations, applicationContextURL);
        this.resourceLoader = resourceLoader;
    }

    public AccountCreatePage inputValidationFailure(String username,
            String firstName, String lastName, String email,
            String confirmEmail, String url, String image,
            String profile) throws IOException {
        webDriverOperations.overrideText(id("username"), username);
        webDriverOperations.overrideText(id("firstName"), firstName);
        webDriverOperations.overrideText(id("lastName"), lastName);
        webDriverOperations.overrideText(id("email"), email);
        webDriverOperations.overrideText(id("confirmEmail"), confirmEmail);
        webDriverOperations.overrideText(id("url"), url);
        webDriverOperations.overrideText(id("profile"), profile);
        Resource resource = resourceLoader.getResource(image);
        webDriverOperations.referUploadFile(id("image"), resource.getFile());
        webDriverOperations.click(id("confirm"));
        waitDefaultInterval();
        return this;
    }

    public String getUsernameError() {
        return webDriverOperations.getText(id("username.errors"));
    }

    public String getImageError() {
        return webDriverOperations.getText(id("image.errors"));
    }

    public String getUrlError() {
        return webDriverOperations.getText(id("url.errors"));
    }

    public String getEmailError() {
        return webDriverOperations.getText(id("email.errors"));
    }

}
