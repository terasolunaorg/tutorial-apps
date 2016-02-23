package org.terasoluna.securelogin.selenium.loginform.page.passwordchange;

import org.terasoluna.securelogin.selenium.WebDriverOperations;
import org.terasoluna.securelogin.selenium.loginform.page.AuthenticationRequiredPage;

import static org.openqa.selenium.By.id;

public class PasswordChangePage extends AuthenticationRequiredPage {

	{
		url = "/password?form";
	}
	
	public PasswordChangePage(WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		super(webDriverOperations, applicationContextURL);
	}

	private void changePassword(String oldPassword, String newPassword,
			String confirmNewPassword) {
		webDriverOperations.overrideText(id("oldPassword"), oldPassword);
		webDriverOperations.overrideText(id("newPassword"), newPassword);
		webDriverOperations.overrideText(id("confirmNewPassword"),
				confirmNewPassword);
		webDriverOperations.click(id("submit"));
		waitDefaultInterval();
	}

	public PasswordChangePage changePasswordFailure(String oldPassword,
			String newPassword, String confirmNewPassword) {
		changePassword(oldPassword, newPassword, confirmNewPassword);
		return this;
	}

	public PasswordChangeSuccessPage changePasswordSuccess(String oldPassword,
			String newPassword, String confirmNewPassword) {
		changePassword(oldPassword, newPassword, confirmNewPassword);
		return new PasswordChangeSuccessPage(webDriverOperations,
				applicationContextUrl);
	}

	public PasswordChangePage gotoTopIntercepted() {
		gotoTop();
		return new PasswordChangePage(webDriverOperations,
				applicationContextUrl);
	}

	public String getNewPasswordError() {
		return webDriverOperations.getText(id("newPassword.errors"));
	}

}
