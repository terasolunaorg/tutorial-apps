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
package org.terasoluna.securelogin.selenium.loginform.page.welcome;

import org.springframework.util.StringUtils;
import org.terasoluna.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AuthenticationRequiredPage;
import org.terasoluna.securelogin.selenium.loginform.page.account.AccountInfoPage;
import org.terasoluna.securelogin.selenium.loginform.page.login.LoginPage;
import org.terasoluna.securelogin.selenium.loginform.page.unlock.UnlockPage;

import static org.openqa.selenium.By.id;

public class TopPage extends AuthenticationRequiredPage {

	{
		url = "/";
	}
	
	public TopPage(WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		super(webDriverOperations, applicationContextURL);
	}

	public AccountInfoPage goToAccountInfoPage() {
		webDriverOperations.click(id("info"));
		waitDefaultInterval();
		return new AccountInfoPage(webDriverOperations, applicationContextUrl);
	}
	
	public String getExpiredMessage() {
		return webDriverOperations.getText(id("expiredMessage"));
	}
	
	public String getLastLogin() {
		return webDriverOperations.getText(id("lastLogin"));
	}
	
	public boolean isExpiredMessageShown(){
		return StringUtils.hasText(getExpiredMessage());
	}

	public UnlockPage goToUnlockPage() {
		webDriverOperations.click(id("unlock"));
		waitDefaultInterval();
		return new UnlockPage(webDriverOperations, applicationContextUrl);
	}
	
	public LoginPage logout(){
		webDriverOperations.click(id("logout"));
		waitDefaultInterval();
		return new LoginPage(webDriverOperations, applicationContextUrl);
	}
}
