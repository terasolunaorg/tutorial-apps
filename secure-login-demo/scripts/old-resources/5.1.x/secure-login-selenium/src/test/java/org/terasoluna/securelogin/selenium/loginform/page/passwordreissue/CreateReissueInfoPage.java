package org.terasoluna.securelogin.selenium.loginform.page.passwordreissue;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;

import static org.openqa.selenium.By.id;

public class CreateReissueInfoPage extends AbstractPageObject {

	{
		url = "/reissue/create?form";
	}
	
	public CreateReissueInfoPage(WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		super(webDriverOperations, applicationContextURL);
	}

	public CreateReissueInfoSuccessPage makeReissueInfo(String username) {
		webDriverOperations.overrideText(id("username"), username);
		webDriverOperations.click(id("submit"));
		waitDefaultInterval();
		return new CreateReissueInfoSuccessPage(webDriverOperations,
				applicationContextUrl);
	}

}
