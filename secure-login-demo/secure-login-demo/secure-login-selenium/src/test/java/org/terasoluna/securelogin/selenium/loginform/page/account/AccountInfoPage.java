package org.terasoluna.securelogin.selenium.loginform.page.account;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AuthenticationRequiredPage;
import org.terasoluna.securelogin.selenium.loginform.page.passwordchange.PasswordChangePage;

import static org.openqa.selenium.By.id;

public class AccountInfoPage extends AuthenticationRequiredPage {
	
	{
		url = "/accounts";
	}
	
	public AccountInfoPage(WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		super(webDriverOperations, applicationContextURL);
	}

	public PasswordChangePage goToPasswordChangePage() {
		webDriverOperations.click(id("changePassword"));
		waitDefaultInterval();
		return new PasswordChangePage(webDriverOperations,
				applicationContextUrl);
	}

}
