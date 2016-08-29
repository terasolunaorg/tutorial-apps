package org.terasoluna.securelogin.selenium.loginform.page.passwordreissue;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;

import static org.openqa.selenium.By.id;

public class CreateReissueInfoSuccessPage extends AbstractPageObject {

	{
		url = "/reissue/create?complete";
	}
	
	public CreateReissueInfoSuccessPage(
			WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		super(webDriverOperations, applicationContextURL);
 	}

	public String getSecret(){
		return webDriverOperations.getText(id("secret"));
	}
}
