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
package com.example.securelogin.selenium.loginform.page.passwordreissue;

import org.terasoluna.gfw.tutorial.selenium.WebDriverOperations;
import com.example.securelogin.selenium.loginform.page.AbstractPageObject;

import static org.openqa.selenium.By.id;

public class CreateReissueInfoPage extends AbstractPageObject {

    {
        url = "/reissue/create?form";
    }

    public CreateReissueInfoPage(WebDriverOperations webDriverOperations,
            String applicationContextURL) {
        super(webDriverOperations, applicationContextURL);
    }

    public CreateReissueInfoSuccessPage makeReissueInfo(String username) {
        webDriverOperations.overrideText(id("username"), username);
        webDriverOperations.click(id("submit"));
        waitDefaultInterval();
        return new CreateReissueInfoSuccessPage(webDriverOperations, applicationContextUrl);
    }

}
