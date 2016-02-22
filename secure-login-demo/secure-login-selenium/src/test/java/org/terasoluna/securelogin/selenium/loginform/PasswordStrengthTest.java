package org.terasoluna.securelogin.selenium.loginform;

import java.io.IOException;
import java.sql.SQLException;

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

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PasswordStrengthTest extends FunctionTestSupport {

	@Value("${security.passwordMinimumLength}")
	int passwordMinimumLength;

	@Inject
	@Named("dataSource")
	private DataSource dataSource;

	@Inject
	ResourceDatabasePopulator populator;

	@Before
	public void setup() throws ScriptException, SQLException {
		populator.populate(dataSource.getConnection());
	}

	/**
	 * <ul>
	 * <li>test : password strength check(insufficient length)</li>
	 * </ul>
	 */
	@Test
	public void testPasswordStrength001() throws IOException, InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("password strength check(insufficient length)");

		// password strength check : insufficient length
		page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo")
				.changePasswordFailure("demo", "Fo1", "Fo1");
		assertThat(((PasswordChangePage) page).getNewPasswordError(),
				containsString("Password must be at least "
						+ passwordMinimumLength + " characters in length."));

		page = ((PasswordChangePage) page)
				.changePasswordSuccess("demo", "Foo1", "Foo1").gotoTop()
				.logout();
	}

	/**
	 * <ul>
	 * <li>test : password strength check(characteristic condition is
	 * unsatisfied)</li>
	 * </ul>
	 */
	@Test
	public void testPasswordStrength002() throws IOException, InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("password strength check(characteristic condition is"
						+ "unsatisfied)");

		// password strength check : characteristic condition is unsatisfied
		page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo")
				.changePasswordFailure("demo", "FooBar", "FooBar");
		assertThat(
				((PasswordChangePage) page).getNewPasswordError(),
				containsString("Password matches 2 of 4 character rules, but 3 are required."));

		page = ((PasswordChangePage) page)
				.changePasswordSuccess("demo", "Foo1", "Foo1").gotoTop()
				.logout();
	}

	/**
	 * <ul>
	 * <li>test : password strength check(contains username)</li>
	 * </ul>
	 */
	@Test
	public void testPasswordStrength003() throws IOException, InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("password strength check(contains username)");

		// password strength check : contains username
		page = ((LoginPage) page).loginSuccessIntercepted("demo", "demo")
				.changePasswordFailure("demo", "demoFoo1", "demoFoo1");
		assertThat(((PasswordChangePage) page).getNewPasswordError(),
				containsString("Password contains the user id demo."));

		page = ((PasswordChangePage) page)
				.changePasswordSuccess("demo", "Foo1", "Foo1").gotoTop()
				.logout();
	}
}
