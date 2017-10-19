/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
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
package org.terasoluna.securelogin.selenium.loginform.page.passwordreissue;

import org.terasoluna.gfw.tutorial.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;

import static org.openqa.selenium.By.id;

public class PasswordReissuePage extends AbstractPageObject {

    public PasswordReissuePage(WebDriverOperations webDriverOperations,
            String applicationContextURL, String reissueUrl) {
        super(webDriverOperations, applicationContextURL);
        url = reissueUrl;
    }

    private void reissue(String secret, String newPassword,
            String confirmNewPassword) {
        webDriverOperations.overrideText(id("secret"), secret);
        webDriverOperations.overrideText(id("newPassword"), newPassword);
        webDriverOperations.overrideText(id("confirmNewPassword"),
                confirmNewPassword);
        webDriverOperations.click(id("submit"));
        waitDefaultInterval();
    }

    public PasswordReissueSuccessPage reissueSuccess(String secret,
            String newPassword, String confirmNewPassword) {
        reissue(secret, newPassword, confirmNewPassword);
        return new PasswordReissueSuccessPage(webDriverOperations, applicationContextUrl);
    }

    public PasswordReissuePage reissueFailure(String secret, String newPassword,
            String confirmNewPassword) {
        reissue(secret, newPassword, confirmNewPassword);
        return this;
    }

    @Override
    public PasswordReissuePage open() {
        webDriverOperations.displayPage(url);
        waitDefaultInterval();
        return this;
    }

    @Override
    public AbstractPageObject openWithDescription(String description) {
        if (url.contains("?")) {
            webDriverOperations.displayPage(url + "&testdescription="
                    + description);
        } else {
            webDriverOperations.displayPage(url + "?testdescription="
                    + description);
        }
        waitDefaultInterval();
        return this;
    }
}
