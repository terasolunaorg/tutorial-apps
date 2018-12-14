/*
 * Copyright (C) 2018 NTT DATA Corporation. Copyright (C) 2018 NTT Corporation.
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
package com.example.securelogin.selenium.loginform.page.login;

import org.springframework.core.io.ResourceLoader;
import org.terasoluna.gfw.tutorial.selenium.WebDriverOperations;
import com.example.securelogin.selenium.loginform.page.AbstractPageObject;
import com.example.securelogin.selenium.loginform.page.account.AccountCreatePage;
import com.example.securelogin.selenium.loginform.page.passwordchange.PasswordChangePage;
import com.example.securelogin.selenium.loginform.page.passwordreissue.CreateReissueInfoPage;
import com.example.securelogin.selenium.loginform.page.welcome.TopPage;

import static org.openqa.selenium.By.id;

public class LoginPage extends AbstractPageObject {

    {
        url = "/login";
    }

    public LoginPage(WebDriverOperations webDriverOperations,
            String applicationContextURL) {
        super(webDriverOperations, applicationContextURL);
    }

    private void login(String username, String password) {
        webDriverOperations.overrideText(id("username"), username);
        webDriverOperations.overrideText(id("password"), password);
        webDriverOperations.click(id("login"));
        waitDefaultInterval();
    }

    public PasswordChangePage loginSuccessIntercepted(String username,
            String password) {
        login(username, password);
        return new PasswordChangePage(webDriverOperations, applicationContextUrl);
    }

    public LoginPage loginFailure(String username, String password) {
        login(username, password);
        return this;
    }

    public TopPage loginSuccess(String username, String password) {
        login(username, password);
        return new TopPage(webDriverOperations, applicationContextUrl);
    }

    public String getLoginError() {
        return webDriverOperations.getText(id("loginError"));
    }

    public AccountCreatePage goToAccountCreatePage(
            ResourceLoader resourceLoader) {
        webDriverOperations.click(id("create"));
        waitDefaultInterval();
        return new AccountCreatePage(webDriverOperations, applicationContextUrl, resourceLoader);
    }

    public CreateReissueInfoPage goToCreateReissueInfoPage() {
        webDriverOperations.click(id("forgotten"));
        waitDefaultInterval();
        return new CreateReissueInfoPage(webDriverOperations, applicationContextUrl);
    }

}
