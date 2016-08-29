package org.terasoluna.securelogin.selenium.loginform.page.passwordchange;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AuthenticationRequiredPage;

public class PasswordChangeSuccessPage extends AuthenticationRequiredPage {

	{
		url = "/password?complete";
	}
	
	public PasswordChangeSuccessPage(WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		super(webDriverOperations, applicationContextURL);
	}

}
