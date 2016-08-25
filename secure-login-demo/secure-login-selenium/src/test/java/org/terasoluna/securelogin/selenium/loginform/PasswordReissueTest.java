package org.terasoluna.securelogin.selenium.loginform;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import org.terasoluna.securelogin.selenium.loginform.page.mail.ReceivedMailPage;
import org.terasoluna.securelogin.selenium.loginform.page.passwordreissue.CreateReissueInfoSuccessPage;
import org.terasoluna.securelogin.selenium.loginform.page.passwordreissue.PasswordReissuePage;
import org.terasoluna.securelogin.selenium.loginform.page.welcome.TopPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PasswordReissueTest extends FunctionTestSupport {

	@Value("${security.tokenValidityThreshold}")
	int tokenValidityThreshold;

	@Value("${security.tokenLifeTimeSeconds}")
	int tokenLifeTimeSeconds;

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
	 * <li>test : secret phrase is shown to screen</li>
	 * <li>test : the URL of "password reissue" page is sent by E-mail</li>
	 * </ul>
	 */
	@Test
	public void testPasswordReissue001() throws IOException, InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("secret phrase is shown to screen"
						+ " and the URL of password reissue page is sent by E-mail");

		// generate information for reissue password and show the secret phrase
		page = ((LoginPage) page).goToCreateReissueInfoPage().makeReissueInfo(
				"demo");
		assertTrue(!((CreateReissueInfoSuccessPage) page).getSecret().isEmpty());
		String secret = ((CreateReissueInfoSuccessPage) page).getSecret();

		// confirm that the URL for reissue password has sent by E-mail
		page = new ReceivedMailPage(webDriverOperations, applicationContextUrl).open();
		String mailText = ((ReceivedMailPage)page).getLatestMailText();
		assertTrue(mailText.indexOf("http") >= 0);
		String url = mailText.substring(mailText.indexOf("http"));

		// access "password reissue" page
		page = new PasswordReissuePage(webDriverOperations,
				applicationContextUrl, url).open();

		// reissue password
		page = ((PasswordReissuePage) page).reissueSuccess(secret, "Hoge1",
				"Hoge1");
		assertTrue(webDriverOperations.getCurrentUrl().endsWith(
				"/resetpassword?complete"));

		// after reissue password, the URL has been invalidated
		page = new PasswordReissuePage(webDriverOperations,
				applicationContextUrl, url).open();
		assertThat(webDriverOperations.getTitle(),
				is("Resource Not Found Error!"));

		// confirm that new password has been set
		page = ((LoginPage) new LoginPage(webDriverOperations,
				applicationContextUrl).open()).loginSuccess("demo", "Hoge1")
				.gotoTop();
		assertTrue(webDriverOperations.getCurrentUrl().endsWith(
				contextName + "/"));

		page = ((TopPage) page).logout();
	}

	/**
	 * <ul>
	 * <li>test : secret phrase is generated at random</li>
	 * <li>test : the URL for reissue password contains token which generated at
	 * random</li>
	 * </ul>
	 */
	@Test
	public void testPasswordReissue002() throws IOException, InterruptedException {
		List<String> urls = new ArrayList<String>();
		List<String> secrets = new ArrayList<String>();

		AbstractPageObject page;

		// generate information for reissue password repeatedly
		int n = 5;
		for (int i = 0; i < n; i++) {
			page = ((LoginPage) new LoginPage(webDriverOperations,
					applicationContextUrl)
					.openWithDescription("secret phrase and URL is generated at random"))
					.goToCreateReissueInfoPage().makeReissueInfo("demo");
			String secret = ((CreateReissueInfoSuccessPage) page).getSecret();
			page = new ReceivedMailPage(webDriverOperations, applicationContextUrl).open();
			String mailText = ((ReceivedMailPage)page).getLatestMailText();
			String url = mailText.substring(mailText.indexOf("http"));
			// confirm that the URL is different from any other one
			assertThat(urls, not(hasItem(url)));
			urls.add(url);
			// confirm that secret phrase is different from any other one
			assertThat(secrets, not(hasItem(secret)));
			secrets.add(secret);
		}
	}

	/**
	 * <ul>
	 * <li>test : the URL is invalidated by series of incorrect password
	 * attempts</li>
	 * </ul>
	 */
	@Test
	public void testPasswordReissue003() throws IOException, InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("the URL is invalidated by series of incorrect password attempts");

		page = ((LoginPage) page).goToCreateReissueInfoPage().makeReissueInfo(
				"demo");
		page = new ReceivedMailPage(webDriverOperations, applicationContextUrl).open();
		String mailText = ((ReceivedMailPage)page).getLatestMailText();
		String url = mailText.substring(mailText.indexOf("http"));

		// confirm that the URL is invalidated by series of incorrect password
		// attempts
		page = new PasswordReissuePage(webDriverOperations,
				applicationContextUrl, url).open();
		for (int i = 0; i < tokenValidityThreshold; i++) {
			page = ((PasswordReissuePage) page).reissueFailure("aaaaa",
					"Hoge2", "Hoge2");
		}
		assertThat(webDriverOperations.getTitle(),
				is("Business Error!"));
	}

	/**
	 * <ul>
	 * <li>test : the URL is invalidated by passage of time</li>
	 * </ul>
	 */
	@Test
	public void testPasswordReissue004() throws IOException, InterruptedException {
		AbstractPageObject page = new LoginPage(webDriverOperations,
				applicationContextUrl)
				.openWithDescription("the URL is invalidated by passage of time");

		page = ((LoginPage) page).goToCreateReissueInfoPage().makeReissueInfo(
				"demo");
		page = new ReceivedMailPage(webDriverOperations, applicationContextUrl).open();
		String mailText = ((ReceivedMailPage)page).getLatestMailText();
		String url = mailText.substring(mailText.indexOf("http"));

		webDriverOperations.suspend(tokenLifeTimeSeconds, TimeUnit.SECONDS);

		// confirm that the URL is invalidated by passage of time
		page = new PasswordReissuePage(webDriverOperations,
				applicationContextUrl, url).open();
		assertThat(webDriverOperations.getTitle(),
				anyOf(is("Resource Not Found Error!"), is("Business Error!")));
	}
}
