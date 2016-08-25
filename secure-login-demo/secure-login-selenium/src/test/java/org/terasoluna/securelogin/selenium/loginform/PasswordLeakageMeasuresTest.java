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
import org.terasoluna.securelogin.selenium.loginform.page.passwordchange.PasswordChangeSuccessPage;
import org.terasoluna.securelogin.selenium.loginform.page.welcome.TopPage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PasswordLeakageMeasuresTest extends FunctionTestSupport {

	@Value("${security.passwordLifeTimeSeconds}")
	int passwordLifeTimeSeconds;

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
	 * <li>test : force user to change password when first login</li>
	 * </ul>
	 */
	@Test
	public void testPasswordLeakageMeasures001() throws IOException,
			InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("force user to change password when first login");

		// confirm that it's redirected to "Change Password" page when first
		// login
		page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo");
		assertTrue(webDriverOperations.getCurrentUrl().endsWith(
				"/password?form"));

		// it cannot access other pages until change password
		page = ((PasswordChangePage) page).gotoTopIntercepted();
		assertTrue(webDriverOperations.getCurrentUrl().endsWith(
				"/password?form"));

		page = ((PasswordChangePage) page).changePasswordSuccess("demo",
				"Foo1", "Foo1");
		assertTrue(webDriverOperations.getCurrentUrl().endsWith(
				"/password?complete"));

		// it's enable to access to top page
		page = ((PasswordChangeSuccessPage) page).gotoTop();
		assertTrue(webDriverOperations.getCurrentUrl().endsWith(
				contextName + "/"));

		page = ((TopPage) page).logout();
	}

	/**
	 * <ul>
	 * <li>test : administrator must not reuse password in a short period
	 * </ul>
	 */
	@Test
	public void testPasswordLeakageMeasures002() throws IOException,
			InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("administrator must not reuse password in a short period");

		// login as administrator
		page = ((LoginPage) page)
				.loginSuccessIntercepted("admin", "demo")
				// set valid password var1
				.changePasswordSuccess("demo", "Bar1", "Bar1")
				// set valid password var2
				.gotoTop().goToAccountInfoPage().goToPasswordChangePage()
				.changePasswordSuccess("Bar1", "Bar2", "Bar2")
				// try to set valid password var1, again
				.gotoTop().goToAccountInfoPage().goToPasswordChangePage()
				.changePasswordFailure("Bar2", "Bar1", "Bar1");
		assertThat(((PasswordChangePage) page).getNewPasswordError(),
				is("Password matches one of 2 previous passwords."));

		page = ((PasswordChangePage) page).gotoTop().logout();
	}

	/**
	 * <ul>
	 * <li>test : show a message to urge users to change their passwords</li>
	 * </ul>
	 */
	@Test
	public void testPasswordLeakageMeasures003() throws IOException,
			InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("show a message to urge users to change their passwords");

		page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo")
				.changePasswordSuccess("demo", "Foo1", "Foo1").gotoTop()
				.logout();

		// confirm that the message to urge the user to change his password is
		// shown
		webDriverOperations.suspend(passwordLifeTimeSeconds, TimeUnit.SECONDS);
		page = ((LoginPage) page).loginSuccess("demo", "Foo1");
		assertThat(((TopPage) page).getExpiredMessage(),
				is("Your password has expired. Please change."));

		// the message disappears because of changing the password
		page = ((TopPage) page).goToAccountInfoPage().goToPasswordChangePage()
				.changePasswordSuccess("Foo1", "Bar1", "Bar1").gotoTop();
		assertTrue(!((TopPage) page).isExpiredMessageShown());

		page = ((TopPage) page).logout();
	}

	/**
	 * <ul>
	 * <li>test : show the last login time of the user</li>
	 * </ul>
	 */
	@Test
	public void testPasswordLeakageMeasures004() throws IOException,
			InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("show the last login time of the user");

		page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo")
				.changePasswordSuccess("demo", "Foo1", "Foo1").gotoTop()
				.logout().loginSuccess("demo", "Foo1");

		// confirm that the last login time of the user is shown
		assertTrue(((TopPage) page).getLastLogin().matches(
				"Last login date is [0-9]{4}-[0-9]{2}-[0-9]{2}.*"));

		page = ((TopPage) page).logout();
	}

	/**
	 * <ul>
	 * <li>test : force administrator to change password if it has been expired</li>
	 * </ul>
	 */
	@Test
	public void testPasswordLeakageMeasures005() throws IOException,
			InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("force administrator to change password if it has been expired");

		page = ((LoginPage) page).loginSuccessIntercepted("admin", "demo")
				.changePasswordSuccess("demo", "Bar1", "Bar1").gotoTop()
				.logout();

		// it's redirected to "Change Password" page because of expiration of
		// the password
		webDriverOperations.suspend(passwordLifeTimeSeconds, TimeUnit.SECONDS);
		page = ((LoginPage) page).loginSuccessIntercepted("admin", "Bar1");
		assertTrue(webDriverOperations.getCurrentUrl().endsWith(
				"/password?form"));
		page = ((PasswordChangePage) page)
				.changePasswordSuccess("Bar1", "Foo1", "Foo1").gotoTop()
				.logout();
	}

}
