package org.terasoluna.securelogin.selenium.loginform.page.unlock;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AuthenticationRequiredPage;

public class UnlockSuccessPage extends AuthenticationRequiredPage {

	{
		url = "/unlock?complete";
	}
	
	public UnlockSuccessPage(WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		super(webDriverOperations, applicationContextURL);
	}

}
