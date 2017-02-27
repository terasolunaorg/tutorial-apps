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
package org.terasoluna.securelogin.selenium.loginform.page.login;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;
import org.terasoluna.securelogin.selenium.loginform.page.passwordchange.PasswordChangePage;
import org.terasoluna.securelogin.selenium.loginform.page.passwordreissue.CreateReissueInfoPage;
import org.terasoluna.securelogin.selenium.loginform.page.welcome.TopPage;

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
		return new PasswordChangePage(webDriverOperations,
				applicationContextUrl);
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

	public CreateReissueInfoPage goToCreateReissueInfoPage() {
		webDriverOperations.click(id("forgotten"));
		waitDefaultInterval();
		return new CreateReissueInfoPage(webDriverOperations,
				applicationContextUrl);
	}

}
