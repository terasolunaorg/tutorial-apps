package org.terasoluna.securelogin.selenium.loginform.page.login;

import org.springframework.core.io.ResourceLoader;
import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;
import org.terasoluna.securelogin.selenium.loginform.page.account.AccountCreatePage;
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

	public AccountCreatePage goToAccountCreatePage(ResourceLoader resourceLoader) {
		webDriverOperations.click(id("create"));
		waitDefaultInterval();
		return new AccountCreatePage(webDriverOperations,
				applicationContextUrl, resourceLoader);
	}

	public CreateReissueInfoPage goToCreateReissueInfoPage() {
		webDriverOperations.click(id("forgotten"));
		waitDefaultInterval();
		return new CreateReissueInfoPage(webDriverOperations,
				applicationContextUrl);
	}

}
