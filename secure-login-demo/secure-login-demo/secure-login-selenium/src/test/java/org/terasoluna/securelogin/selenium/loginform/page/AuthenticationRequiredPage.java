package org.terasoluna.securelogin.selenium.loginform.page;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.welcome.TopPage;

public abstract class AuthenticationRequiredPage extends AbstractPageObject {

	public AuthenticationRequiredPage(WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		super(webDriverOperations, applicationContextURL);
	}

	public TopPage gotoTop() {
		webDriverOperations.displayPage(applicationContextUrl);
		waitDefaultInterval();
		return new TopPage(webDriverOperations, applicationContextUrl);
	}

}
