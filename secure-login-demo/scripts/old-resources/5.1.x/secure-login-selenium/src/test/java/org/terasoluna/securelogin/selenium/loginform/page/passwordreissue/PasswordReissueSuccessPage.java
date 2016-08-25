package org.terasoluna.securelogin.selenium.loginform.page.passwordreissue;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;

public class PasswordReissueSuccessPage extends AbstractPageObject {

	{
		url = "/reissue/resetpassword?complete";
	}
	
	public PasswordReissueSuccessPage(WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		super(webDriverOperations, applicationContextURL);
	}

}
