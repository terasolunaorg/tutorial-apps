package org.terasoluna.securelogin.selenium.loginform;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.terasoluna.securelogin.selenium.FunctionTestSupport;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;
import org.terasoluna.securelogin.selenium.loginform.page.login.LoginPage;
import org.terasoluna.securelogin.selenium.loginform.page.passwordchange.PasswordChangePage;
import org.terasoluna.securelogin.selenium.loginform.page.unlock.UnlockSuccessPage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountLockTest extends FunctionTestSupport {

	@Value("${security.lockingThreshold}")
	int lockingThreshold;

	@Value("${security.lockingDurationSeconds}")
	int lockingDurationSeconds;

	@Inject
	@Named("dataSource")
	DataSource dataSource;

	@Inject
	ResourceDatabasePopulator populator;

	@Before
	public void setup() throws ScriptException, SQLException {
		populator.populate(dataSource.getConnection());
	}

	/**
	 * <ul>
	 * <li>test : an account is locked by series of incorrect password attempts
	 * <li>test : account is unlocked when the certain time passes</li>
	 * </ul>
	 */
	@Test
	public void testAccountLock001() throws IOException, InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("an account is locked by series of incorrect password attempts"
						+ " and unlocked when the certain time passes");
		for (int i = 0; i < lockingThreshold + 1; i++) {
			page = ((LoginPage) page).loginFailure("demo", "hoge");
		}
		assertThat(((LoginPage) page).getLoginError(),
				is("User account is locked"));

		webDriverOperations.suspend(lockingDurationSeconds, TimeUnit.SECONDS);
		page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo");
		assertTrue(webDriverOperations.getCurrentUrl().endsWith(
				"/password?form"));

		page = ((PasswordChangePage) page)
				.changePasswordSuccess("demo", "Foo1", "Foo1").gotoTop()
				.logout();
	}

	/**
	 * <ul>
	 * <li>test : administrator can unlock accounts</li>
	 * </ul>
	 */
	@Test
	public void testAccountLock002() throws IOException, InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("administrator can unlock accounts");

		for (int i = 0; i < lockingThreshold + 1; i++) {
			page = ((LoginPage) page).loginFailure("demo", "hoge");
		}

		// unlock
		page = ((LoginPage) page).loginSuccessIntercepted("admin", "demo")
				.changePasswordSuccess("demo", "Bar1", "Bar1").gotoTop()
				.goToUnlockPage().unlockSuccess("demo");
		assertTrue(webDriverOperations.getCurrentUrl().endsWith(
				"unlock?complete"));

		// confirm the account is successfully unlocked
		page = ((UnlockSuccessPage) page).gotoTop().logout()
				.loginSuccessIntercepted("demo", "demo");
		assertTrue(webDriverOperations.getCurrentUrl().endsWith(
				"/password?form"));
		page = ((PasswordChangePage) page)
				.changePasswordSuccess("demo", "Foo1", "Foo1").gotoTop()
				.logout();
	}

}
