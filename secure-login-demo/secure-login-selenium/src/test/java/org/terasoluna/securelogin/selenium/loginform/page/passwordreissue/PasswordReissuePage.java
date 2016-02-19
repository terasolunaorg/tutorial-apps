package org.terasoluna.securelogin.selenium.loginform.page.passwordreissue;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;

import static org.openqa.selenium.By.id;

public class PasswordReissuePage extends AbstractPageObject {

	public PasswordReissuePage(WebDriverOperations webDriverOperations,
			String applicationContextURL, String reissueUrl) {
		super(webDriverOperations, applicationContextURL);
		url = reissueUrl;
	}

	private void reissue(String secret, String newPassword,
			String confirmNewPassword) {
		webDriverOperations.overrideText(id("secret"), secret);
		webDriverOperations.overrideText(id("newPassword"), newPassword);
		webDriverOperations.overrideText(id("confirmNewPassword"),
				confirmNewPassword);
		webDriverOperations.click(id("submit"));
		waitDefaultInterval();
	}

	public PasswordReissueSuccessPage reissueSuccess(String secret,
			String newPassword, String confirmNewPassword) {
		reissue(secret, newPassword, confirmNewPassword);
		return new PasswordReissueSuccessPage(webDriverOperations,
				applicationContextUrl);
	}

	public PasswordReissuePage reissueFailure(String secret,
			String newPassword, String confirmNewPassword) {
		reissue(secret, newPassword, confirmNewPassword);
		return this;
	}

	@Override
	public PasswordReissuePage open() {
		webDriverOperations.displayPage(url);
		waitDefaultInterval();
		return this;
	}

	@Override
	public AbstractPageObject openWithDescription(String description) {
		if (url.contains("?")) {
			webDriverOperations.displayPage(url + "&testdescription="
					+ description);
		} else {
			webDriverOperations.displayPage(url + "?testdescription="
					+ description);
		}
		waitDefaultInterval();
		return this;
	}
}
