package org.terasoluna.securelogin.selenium.loginform.page.unlock;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AuthenticationRequiredPage;

import static org.openqa.selenium.By.id;

public class UnlockPage extends AuthenticationRequiredPage {

	{
		url = "/unlock?form";
	}
	
	public UnlockPage(WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		super(webDriverOperations, applicationContextURL);
	}

	private void unlock(String username){
		webDriverOperations.overrideText(id("username"), username);
		webDriverOperations.click(id("submit"));
		waitDefaultInterval();
	}
	
	public UnlockPage unlockFailure(String username){
		unlock(username);
		return this;
	}
	
	public UnlockSuccessPage unlockSuccess(String username){
		unlock(username);
		return new UnlockSuccessPage(webDriverOperations, applicationContextUrl);
	}
	
}
