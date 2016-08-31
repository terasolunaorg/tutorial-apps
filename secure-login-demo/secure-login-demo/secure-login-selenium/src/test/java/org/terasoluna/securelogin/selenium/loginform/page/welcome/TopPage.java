package org.terasoluna.securelogin.selenium.loginform.page.welcome;

import org.springframework.util.StringUtils;
import org.terasoluna.securelogin.selenium.WebDriverOperations;
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
