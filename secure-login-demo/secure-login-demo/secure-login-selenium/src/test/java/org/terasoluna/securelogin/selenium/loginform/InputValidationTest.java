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
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.terasoluna.securelogin.selenium.FunctionTestSupport;
import org.terasoluna.securelogin.selenium.loginform.page.AbstractPageObject;
import org.terasoluna.securelogin.selenium.loginform.page.account.AccountCreatePage;
import org.terasoluna.securelogin.selenium.loginform.page.login.LoginPage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InputValidationTest extends FunctionTestSupport {

	@Inject
	ResourceLoader resourceLoader;

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
	 * <li>test : go to error page if the request parameter contains prohibited
	 * charactor</li>
	 * </ul>
	 */
	@Test
	public void testInputValidation001() throws IOException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("go to error page if the request parameter"
						+ " contains prohibited charactor");

		page = ((LoginPage) page).goToAccountCreatePage(resourceLoader)
				.inputValidationFailure("user&name", "firstName", "lastName",
						"test@domainexample.co.jp", "test@domainexample.co.jp",
						"http://test-secure-login.jp",
						"classpath:database/testdata/demo.png", "test user");
		assertThat(webDriverOperations.getTitle(),
				is("Invalid Character Error!"));
	}

	/**
	 * <ul>
	 * <li>test : go to error page if the file name contains prohibited
	 * charactor</li>
	 * </ul>
	 */
	@Test
	public void testInputValidation002() throws IOException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("go to error page if the file name"
						+ " contains prohibited charactor");

		page = ((LoginPage) page).goToAccountCreatePage(resourceLoader)
				.inputValidationFailure("username", "firstName", "lastName",
						"test@domainexample.co.jp", "test@domainexample.co.jp",
						"http://test-secure-login.jp",
						"classpath:database/testdata/demo;.png", "test user");
		assertThat(webDriverOperations.getTitle(),
				is("Invalid Character Error!"));
	}

	/**
	 * <ul>
	 * <li>test : control characters must not be contained in username</li>
	 * </ul>
	 */
	@Test
	public void testInputValidation003() throws IOException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("control characters must not be contained in username");

		page = ((LoginPage) page).goToAccountCreatePage(resourceLoader)
				.inputValidationFailure("user	name", "firstName", "lastName",
						"test@domainexample.co.jp", "test@domainexample.co.jp",
						"http://test-secure-login.jp",
						"classpath:database/testdata/demo.png", "test user");
		assertThat(((AccountCreatePage) page).getUsernameError(),
				containsString("Control characters are not allowed."));
	}

	/**
	 * <ul>
	 * <li>test : the upload files' extensions are restricted</li>
	 * </ul>
	 */
	@Test
	public void testInputValidation004() throws IOException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("the upload files' extensions are restricted");

		page = ((LoginPage) page).goToAccountCreatePage(resourceLoader)
				.inputValidationFailure("username", "firstName", "lastName",
						"test@domainexample.co.jp", "test@domainexample.co.jp",
						"http://test-secure-login.jp",
						"classpath:database/testdata/demo.txt", "test user");
		assertThat(((AccountCreatePage) page).getImageError(),
				containsString("The file extension is not allowed."));
	}

	/**
	 * <ul>
	 * <li>test : the upload file's name has to match specific pattern</li>
	 * </ul>
	 */
	@Test
	public void testInputValidation005() throws IOException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("the upload file's name has to match specific pattern");

		page = ((LoginPage) page).goToAccountCreatePage(resourceLoader)
				.inputValidationFailure("username", "firstName", "lastName",
						"test@domainexample.co.jp", "test@domainexample.co.jp",
						"http://test-secure-login.jp",
						"classpath:database/testdata/secure.login.demo.png",
						"test user");
		assertThat(((AccountCreatePage) page).getImageError(),
				containsString("The file name is not allowed."));
	}

	/**
	 * <ul>
	 * <li>test : the domains of the URLs are restricted</li>
	 * </ul>
	 */
	@Test
	public void testInputValidation006() throws IOException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("the domains of the URLs are restricted");

		page = ((LoginPage) page).goToAccountCreatePage(resourceLoader)
				.inputValidationFailure("username", "firstName", "lastName",
						"test@domainexample.co.jp", "test@domainexample.co.jp",
						"http://test-secure-login.org",
						"classpath:database/testdata/demo.png", "test user");
		assertThat(((AccountCreatePage) page).getUrlError(),
				containsString("This domain is not allowed."));
	}

	/**
	 * <ul>
	 * <li>test : the domains of the E-mail addresses are restricted</li>
	 * </ul>
	 */
	@Test
	public void testInputValidation007() throws IOException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("the domains of the E-mail addresses are restricted");

		page = ((LoginPage) page).goToAccountCreatePage(resourceLoader)
				.inputValidationFailure("username", "firstName", "lastName",
						"test@secure.login.org", "test@secure.login.org",
						"http://test-secure-login.jp",
						"classpath:database/testdata/demo.png", "test user");
		assertThat(((AccountCreatePage) page).getEmailError(),
				containsString("This domain is not allowed."));
	}
}
