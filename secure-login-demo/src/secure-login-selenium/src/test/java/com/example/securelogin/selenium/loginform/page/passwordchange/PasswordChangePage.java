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
package com.example.securelogin.selenium.loginform.page.passwordchange;

import org.terasoluna.gfw.tutorial.selenium.WebDriverOperations;
import com.example.securelogin.selenium.loginform.page.AuthenticationRequiredPage;

import static org.openqa.selenium.By.id;

public class PasswordChangePage extends AuthenticationRequiredPage {

    {
        url = "/password?form";
    }

    public PasswordChangePage(WebDriverOperations webDriverOperations,
            String applicationContextURL) {
        super(webDriverOperations, applicationContextURL);
    }

    private void changePassword(String oldPassword, String newPassword,
            String confirmNewPassword) {
        webDriverOperations.overrideText(id("oldPassword"), oldPassword);
        webDriverOperations.overrideText(id("newPassword"), newPassword);
        webDriverOperations.overrideText(id("confirmNewPassword"),
                confirmNewPassword);
        webDriverOperations.click(id("submit"));
        waitDefaultInterval();
    }

    public PasswordChangePage changePasswordFailure(String oldPassword,
            String newPassword, String confirmNewPassword) {
        changePassword(oldPassword, newPassword, confirmNewPassword);
        return this;
    }

    public PasswordChangeSuccessPage changePasswordSuccess(String oldPassword,
            String newPassword, String confirmNewPassword) {
        changePassword(oldPassword, newPassword, confirmNewPassword);
        return new PasswordChangeSuccessPage(webDriverOperations, applicationContextUrl);
    }

    public PasswordChangePage gotoTopIntercepted() {
        gotoTop();
        return new PasswordChangePage(webDriverOperations, applicationContextUrl);
    }

    public String getNewPasswordError() {
        return webDriverOperations.getText(id("newPassword.errors"));
    }

}
