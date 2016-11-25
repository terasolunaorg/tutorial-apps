package com.example.session.selenium.session;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;

import com.example.session.selenium.FunctionTestSupport;

public class SessionTutorialTest extends FunctionTestSupport {

	private static WebDriver driver;

	@Value("${selenium.applicationContextUrl}/loginForm")
	String baseUrl;

	public SessionTutorialTest (){
		super.disableDefaultWebDriver();
	}
	
	@Before
	public void setUp() {

		if (driver == null) {
			driver = webDriverCreator.createLocaleSpecifiedDriver("");
		}
		super.setCurrentWebDriver(driver);
		
		// display login page
		{
			webDriverOperations.displayPage(baseUrl);
		}

	}

	@Test
	public void testInitialLogin() {

		// login
		{
			webDriverOperations.overrideText(By.id("email"),"a@b.com");
			webDriverOperations.overrideText(By.id("password"),"demo");
			webDriverOperations.click(By.id("login"));
		}

		// check initial value
		{
			assertThat(webDriverOperations.getText(By.id("Kokoro")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("〔Ame ni mo Makezu〕")),
					is("〔Ame ni mo Makezu〕"));
			assertThat(webDriverOperations.getText(By.id("Run, Melos!")),
					is("Run, Melos!"));
			assertThat(webDriverOperations.getText(By.id("userName")),
					is("xxx"));

		}
	}

	@Test
	public void testCreateAccount() {

		// move createAccount page
		{
			webDriverOperations.click(By.id("createAccount"));
		}

		// input the form value and click confirm button
		{
			webDriverOperations.overrideText(By.id("name"),"test");
			webDriverOperations.overrideText(By.id("email"),"test@xxx.co.jp");
			webDriverOperations.overrideText(By.id("password"),"password");
			webDriverOperations.overrideText(By.id("confirmPassword"),"password");
			webDriverOperations.overrideText(By.id("birthday"),"2016-01-01");
			webDriverOperations.overrideText(By.id("zip"),"1234567");
			webDriverOperations.overrideText(By.id("address"),"Nagoya");
			webDriverOperations.click(By.id("confirm"));
		}

		// check the update value
		{
			assertThat(webDriverOperations.getText(By.id("name")), is("test"));
			assertThat(webDriverOperations.getText(By.id("email")),
					is("test@xxx.co.jp"));
			assertThat(webDriverOperations.getText(By.id("birthday")),
					is("2016-01-01"));
			assertThat(webDriverOperations.getText(By.id("zip")),
					is("1234567"));
			assertThat(webDriverOperations.getText(By.id("address")),
					is("Nagoya"));
		}

		// click back button
		{
			webDriverOperations.click(By.id("back"));
		}

		// check that the entered value remains
		{
			assertThat(webDriverOperations.getInputFieldValue(By.id("name")),
					is("test"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("email")),
					is("test@xxx.co.jp"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("password")),
					is(""));
			assertThat(webDriverOperations.getInputFieldValue(By.id("confirmPassword")),
					is(""));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("birthday")),
					is("2016-01-01"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("zip")),
					is("1234567"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("address")),
					is("Nagoya"));
		}

		// click confirm button
		{
			webDriverOperations.overrideText(By.id("password"),"password");
			webDriverOperations.overrideText(By.id("confirmPassword"),"password");
			webDriverOperations.click(By.id("confirm"));
		}

		// click create button
		{
			webDriverOperations.click(By.id("create"));
		}

		// check update value
		{
			assertThat(webDriverOperations.getText(By.id("name")), is("test"));
			assertThat(webDriverOperations.getText(By.id("email")),
					is("test@xxx.co.jp"));
			assertThat(webDriverOperations.getText(By.id("birthday")),
					is("2016-01-01"));
			assertThat(webDriverOperations.getText(By.id("zip")),
					is("1234567"));
			assertThat(webDriverOperations.getText(By.id("address")),
					is("Nagoya"));
		}

		// click Login page button
		{
			webDriverOperations.click(By.id("home"));
		}

		// login
		{
			webDriverOperations.overrideText(By.id("email"),"test@xxx.co.jp");
			webDriverOperations.overrideText(By.id("password"),"password");
			webDriverOperations.click(By.id("login"));
		}

		// check initial value
		{
			assertThat(webDriverOperations.getText(By.id("Kokoro")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("〔Ame ni mo Makezu〕")),
					is("〔Ame ni mo Makezu〕"));
			assertThat(webDriverOperations.getText(By.id("Run, Melos!")),
					is("Run, Melos!"));
			assertThat(webDriverOperations.getText(By.id("userName")),
					is("test"));

		}
	}

	@Test
	public void testShowGoods() {

		// login
		testInitialLogin();

		// click the item name
		{
			webDriverOperations.click(By.id("Kokoro"));
		}

		// check the display
		{
			assertThat(webDriverOperations.getText(By.id("name")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("price")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("description")),
					is("Souseki Natsume wrote this book"));
		}

		// click home button
		{
			webDriverOperations.click(By.id("home"));
		}

		// update the category
		{
			webDriverOperations.select(By.id("categoryId"),"music");
			webDriverOperations.click(By.id("update"));
		}

		// display
		{
			assertThat(webDriverOperations.getText(By.id("Symphony No. 5 in C minor (Fate)")),
					is("Symphony No. 5 in C minor (Fate)"));
			assertThat(webDriverOperations.getText(By.id("Eine kleine Nachtmusik")),
					is("Eine kleine Nachtmusik"));
			assertThat(webDriverOperations.getText(By.id("Swan Lake")),
					is("Swan Lake"));
		}

		// click the item name
		{
			webDriverOperations.click(By.id("Swan Lake"));
		}

		// check the display
		{
			assertThat(webDriverOperations.getText(By.id("name")),
					is("Swan Lake"));
			assertThat(webDriverOperations.getText(By.id("price")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("description")),
					is("Tchaikovsky composed this music"));
		}

	}

	@After
	public void tearDown() {

		// click logout button
		{
			webDriverOperations.click(By.id("logout"));
		}

	}

}
