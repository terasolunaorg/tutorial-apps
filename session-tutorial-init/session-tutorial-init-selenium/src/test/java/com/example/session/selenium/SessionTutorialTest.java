package com.example.session.selenium.session;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.session.selenium.FunctionTestSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/seleniumContext.xml" })
public class SessionTutorialTest extends FunctionTestSupport {


	@Value("${selenium.applicationContextUrl}/loginForm")
	String baseUrl;

	@Test
	public void testInitialLogin() {

		// login
		{
			driver.findElement(By.id("email")).sendKeys("a@b.com");
			driver.findElement(By.id("password")).sendKeys("demo");
			driver.findElement(By.id("login")).click();
		}

		// check initial value
		{
			assertThat(driver.findElement(By.id("Kokoro")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("〔Ame ni mo Makezu〕"))
					.getText(), is("〔Ame ni mo Makezu〕"));
			assertThat(driver.findElement(By.id("Run, Melos!")).getText(),
					is("Run, Melos!"));
			assertThat(driver.findElement(By.id("userName")).getText(),
					is("xxx"));

		}
	}

	@Test
	public void testCreateAccount() {

		// move createAccount page
		{
			driver.findElement(By.id("createAccount")).click();
		}

		// input the form value and click confirm button
		{
			driver.findElement(By.id("name")).sendKeys("test");
			driver.findElement(By.id("email")).sendKeys("test@xxx.co.jp");
			driver.findElement(By.id("password")).sendKeys("password");
			driver.findElement(By.id("confirmPassword")).sendKeys("password");
			driver.findElement(By.id("birthday")).sendKeys("2016-01-01");
			driver.findElement(By.id("zip")).sendKeys("1234567");
			driver.findElement(By.id("address")).sendKeys("Nagoya");
			driver.findElement(By.id("confirm")).click();
		}

		// check the update value
		{
			assertThat(driver.findElement(By.id("name")).getText(), is("test"));
			assertThat(driver.findElement(By.id("email")).getText(),
					is("test@xxx.co.jp"));
			assertThat(driver.findElement(By.id("birthday")).getText(),
					is("2016-01-01"));
			assertThat(driver.findElement(By.id("zip")).getText(),
					is("1234567"));
			assertThat(driver.findElement(By.id("address")).getText(),
					is("Nagoya"));
		}

		// click back button
		{
			driver.findElement(By.id("back")).click();
		}

		// check that the entered value remains
		{
			assertThat(driver.findElement(By.id("name")).getAttribute("value"),
					is("test"));
			assertThat(
					driver.findElement(By.id("email")).getAttribute("value"),
					is("test@xxx.co.jp"));
			assertThat(
					driver.findElement(By.id("password")).getAttribute("value"),
					is(""));
			assertThat(driver.findElement(By.id("confirmPassword"))
					.getAttribute("value"), is(""));
			assertThat(
					driver.findElement(By.id("birthday")).getAttribute("value"),
					is("2016-01-01"));
			assertThat(driver.findElement(By.id("zip")).getAttribute("value"),
					is("1234567"));
			assertThat(
					driver.findElement(By.id("address")).getAttribute("value"),
					is("Nagoya"));
		}

		// click confirm button
		{
			driver.findElement(By.id("password")).sendKeys("password");
			driver.findElement(By.id("confirmPassword")).sendKeys("password");
			driver.findElement(By.id("confirm")).click();
		}

		// click create button
		{
			driver.findElement(By.id("create")).click();
		}

		// check update value
		{
			assertThat(driver.findElement(By.id("name")).getText(), is("test"));
			assertThat(driver.findElement(By.id("email")).getText(),
					is("test@xxx.co.jp"));
			assertThat(driver.findElement(By.id("birthday")).getText(),
					is("2016-01-01"));
			assertThat(driver.findElement(By.id("zip")).getText(),
					is("1234567"));
			assertThat(driver.findElement(By.id("address")).getText(),
					is("Nagoya"));
		}

		// click Login page button
		{
			driver.findElement(By.id("home")).click();
		}

		// login
		{
			driver.findElement(By.id("email")).sendKeys("test@xxx.co.jp");
			driver.findElement(By.id("password")).sendKeys("password");
			driver.findElement(By.id("login")).click();
		}

		// check initial value
		{
			assertThat(driver.findElement(By.id("Kokoro")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("〔Ame ni mo Makezu〕"))
					.getText(), is("〔Ame ni mo Makezu〕"));
			assertThat(driver.findElement(By.id("Run, Melos!")).getText(),
					is("Run, Melos!"));
			assertThat(driver.findElement(By.id("userName")).getText(),
					is("test"));

		}
	}

	@Test
	public void testShowGoods() {

		// login
		testInitialLogin();

		// click the item name
		{
			driver.findElement(By.id("Kokoro")).click();
		}

		// check the display
		{
			assertThat(driver.findElement(By.id("name")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("price"))
					.getText(), is("¥ 900"));
			assertThat(driver.findElement(By.id("description")).getText(),
					is("Souseki Natsume wrote this book"));
		}

		// click home button
		{
			driver.findElement(By.id("home")).click();
		}

		// update the category
		{
			new Select(driver.findElement(By.id("categoryId"))).selectByVisibleText("music");
			driver.findElement(By.id("update")).click();
		}

		// display
		{
			assertThat(driver.findElement(By.id("Symphony No. 5 in C minor (Fate)")).getText(),
					is("Symphony No. 5 in C minor (Fate)"));
			assertThat(driver.findElement(By.id("Eine kleine Nachtmusik"))
					.getText(), is("Eine kleine Nachtmusik"));
			assertThat(driver.findElement(By.id("Swan Lake")).getText(),
					is("Swan Lake"));
		}

		// click the item name
		{
			driver.findElement(By.id("Swan Lake")).click();
		}

		// check the display
		{
			assertThat(driver.findElement(By.id("name")).getText(),
					is("Swan Lake"));
			assertThat(driver.findElement(By.id("price"))
					.getText(), is("¥ 900"));
			assertThat(driver.findElement(By.id("description")).getText(),
					is("Tchaikovsky composed this music"));
		}

	}

	@After
	public void tearDown() {

		// click logout button
		{
			driver.findElement(By.id("logout")).click();
		}

	}

}
