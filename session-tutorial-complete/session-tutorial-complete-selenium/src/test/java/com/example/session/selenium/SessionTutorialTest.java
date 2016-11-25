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
		defaultLogin();
 
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
 
		// move creatAccount page
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
 
		// click back button
		{
			driver.findElement(By.id("back")).click();
		}
 
		// check update value
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
		defaultLogin();
 
		// click the item name
		{
			driver.findElement(By.id("Kokoro")).click();
		}
 
		// check the display page
		{
			assertThat(driver.findElement(By.id("name")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("price")).getText(),
					is("¥ 900"));
			assertThat(driver.findElement(By.id("description")).getText(),
					is("Souseki Natsume wrote this book"));
		}
 
		// click the home button
		{
			driver.findElement(By.id("home")).click();
		}
 
		// update category
		{
			new Select(driver.findElement(By.id("categoryId")))
					.selectByVisibleText("music");
			driver.findElement(By.id("update")).click();
		}
 
		// check the display page
		{
			assertThat(
					driver.findElement(
							By.id("Symphony No. 5 in C minor (Fate)"))
							.getText(), is("Symphony No. 5 in C minor (Fate)"));
			assertThat(driver.findElement(By.id("Eine kleine Nachtmusik"))
					.getText(), is("Eine kleine Nachtmusik"));
			assertThat(driver.findElement(By.id("Swan Lake")).getText(),
					is("Swan Lake"));
		}
 
		// click the item name
		{
			driver.findElement(By.id("Swan Lake")).click();
		}
		// check page
		{
			assertThat(driver.findElement(By.id("name")).getText(),
					is("Swan Lake"));
			assertThat(driver.findElement(By.id("price")).getText(),
					is("¥ 900"));
			assertThat(driver.findElement(By.id("description")).getText(),
					is("Tchaikovsky composed this music"));
		}
 
	}
 
	@Test
	public void testChangeAcountInfo() {
 
		// login
		defaultLogin();
 
 		// click Account Update button
		{
			driver.findElement(By.id("updateAccount")).click();
		}
 
		// check the form default page
		{
			assertThat(driver.findElement(By.id("name")).getAttribute("value"),
					is("xxx"));
			assertThat(
					driver.findElement(By.id("email")).getAttribute("value"),
					is("a@b.com"));
			assertThat(
					driver.findElement(By.id("birthday")).getAttribute("value"),
					is("2015-08-01"));
			assertThat(driver.findElement(By.id("zip")).getAttribute("value"),
					is("1111111"));
			assertThat(
					driver.findElement(By.id("address")).getAttribute("value"),
					is("Tokyo"));
		}
 
		// click the next button
		{
			driver.findElement(By.id("name")).clear();
			driver.findElement(By.id("name")).sendKeys("test");
			driver.findElement(By.id("address")).clear();
			driver.findElement(By.id("address")).sendKeys("Osaka");
			driver.findElement(By.id("next")).click();
		}
 
		// check initial value
		{
			assertThat(
					driver.findElement(By.id("cardNumber")).getAttribute(
							"value"), is("1111111111111111"));
			assertThat(driver.findElement(By.id("cardExpirationDate"))
					.getAttribute("value"), is("2015-08"));
			assertThat(driver.findElement(By.id("cardSecurityCode"))
					.getAttribute("value"), is("111"));
		}
 
		// click the buck button
		{
			driver.findElement(By.id("back")).click();
		}
 
		// check that the entered value remains
		{
			assertThat(driver.findElement(By.id("name")).getAttribute("value"),
					is("test"));
			assertThat(
					driver.findElement(By.id("email")).getAttribute("value"),
					is("a@b.com"));
			assertThat(
					driver.findElement(By.id("birthday")).getAttribute("value"),
					is("2015-08-01"));
			assertThat(driver.findElement(By.id("zip")).getAttribute("value"),
					is("1111111"));
			assertThat(
					driver.findElement(By.id("address")).getAttribute("value"),
					is("Osaka"));
		}
 
		// click the next button
		{
			driver.findElement(By.id("next")).click();
		}
 
		// click the confirm button
		{
			driver.findElement(By.id("cardNumber")).clear();
			driver.findElement(By.id("cardNumber"))
					.sendKeys("1234567890123456");
			driver.findElement(By.id("confirm")).click();
		}
 
		// check the input value
		{
			assertThat(driver.findElement(By.id("name")).getText(), is("test"));
			assertThat(driver.findElement(By.id("email")).getText(),
					is("a@b.com"));
			assertThat(driver.findElement(By.id("birthday")).getText(),
					is("2015-08-01"));
			assertThat(driver.findElement(By.id("zip")).getText(),
					is("1111111"));
			assertThat(driver.findElement(By.id("address")).getText(),
					is("Osaka"));
			assertThat(driver.findElement(By.id("cardNumber")).getText(),
					is("****-****-****-3456"));
			assertThat(driver.findElement(By.id("cardExpirationDate"))
					.getText(), is("2015-08"));
			assertThat(driver.findElement(By.id("cardSecurityCode")).getText(),
					is("111"));
		}
 
		// click the buck button
		{
			driver.findElement(By.id("back")).click();
		}
 
		// check that the entered value remains
		{
			assertThat(
					driver.findElement(By.id("cardNumber")).getAttribute(
							"value"), is("1234567890123456"));
			assertThat(driver.findElement(By.id("cardExpirationDate"))
					.getAttribute("value"), is("2015-08"));
			assertThat(driver.findElement(By.id("cardSecurityCode"))
					.getAttribute("value"), is("111"));
		}
 
		// click the confirm button
		{
			driver.findElement(By.id("confirm")).click();
		}
 
		// click the update button
		{
			driver.findElement(By.id("update")).click();
		}
 
		// check update value
		{
			assertThat(driver.findElement(By.id("name")).getText(), is("test"));
			assertThat(driver.findElement(By.id("email")).getText(),
					is("a@b.com"));
			assertThat(driver.findElement(By.id("birthday")).getText(),
					is("2015-08-01"));
			assertThat(driver.findElement(By.id("zip")).getText(),
					is("1111111"));
			assertThat(driver.findElement(By.id("address")).getText(),
					is("Osaka"));
			assertThat(driver.findElement(By.id("cardNumber")).getText(),
					is("****-****-****-3456"));
			assertThat(driver.findElement(By.id("cardExpirationDate"))
					.getText(), is("2015-08"));
			assertThat(driver.findElement(By.id("cardSecurityCode")).getText(),
					is("111"));
		}
 
		// click the home button
		{
			driver.findElement(By.id("home")).click();
		}
 
		// check update value
		{
			assertThat(driver.findElement(By.id("userName")).getText(),
					is("test"));
		}
 
		// click the Account Update button
		{
			driver.findElement(By.id("updateAccount")).click();
		}
 
		// click the next button
		{
			driver.findElement(By.id("name")).clear();
			driver.findElement(By.id("name")).sendKeys("aaaaaa");
			driver.findElement(By.id("address")).clear();
			driver.findElement(By.id("address")).sendKeys("Nagoya");
			driver.findElement(By.id("next")).click();
		}
 
		// click the home button
		{
			driver.findElement(By.id("home")).click();
		}
 
		// click the Account Update button
		{
			driver.findElement(By.id("updateAccount")).click();
		}
 
		// check update value
		{
			assertThat(driver.findElement(By.id("name")).getAttribute("value"),
					is("test"));
			assertThat(
					driver.findElement(By.id("email")).getAttribute("value"),
					is("a@b.com"));
			assertThat(
					driver.findElement(By.id("birthday")).getAttribute("value"),
					is("2015-08-01"));
			assertThat(driver.findElement(By.id("zip")).getAttribute("value"),
					is("1111111"));
			assertThat(
					driver.findElement(By.id("address")).getAttribute("value"),
					is("Osaka"));
		}
 
		// Re-update the updated value to its original value
		{
			driver.findElement(By.id("name")).clear();
			driver.findElement(By.id("name")).sendKeys("xxx");
			driver.findElement(By.id("address")).clear();
			driver.findElement(By.id("address")).sendKeys("Tokyo");
			driver.findElement(By.id("next")).click();
 
			driver.findElement(By.id("cardNumber")).clear();
			driver.findElement(By.id("cardNumber"))
					.sendKeys("1111111111111111");
			driver.findElement(By.id("confirm")).click();
 
			driver.findElement(By.id("update")).click();
 
			driver.findElement(By.id("home")).click();
		}
	}
 
	@Test
	public void testAddCartItem() {
 
		// login
		defaultLogin();
 
		// click the addCart button
		{
			driver.findElement(By.id("quantity0")).clear();
			driver.findElement(By.id("quantity0")).sendKeys("2");
			driver.findElement(By.id("add0")).click();
		}
 
		// check update value
		{
			assertThat(driver.findElement(By.id("itemName0")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("itemPrice0")).getText(),
					is("¥ 900"));
			assertThat(driver.findElement(By.id("itemQuantity0")).getText(),
					is("2"));
			assertThat(driver.findElement(By.id("totalPrice")).getText(),
					is("¥ 1,800"));
		}
 
		// click the addCart button
		{
			driver.findElement(By.id("quantity1")).clear();
			driver.findElement(By.id("quantity1")).sendKeys("1");
			driver.findElement(By.id("add1")).click();
		}
 
		// check update value
		{
			assertThat(driver.findElement(By.id("itemName0")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("itemPrice0")).getText(),
					is("¥ 900"));
			assertThat(driver.findElement(By.id("itemQuantity0")).getText(),
					is("2"));
			assertThat(driver.findElement(By.id("itemName1")).getText(),
					is("〔Ame ni mo Makezu〕"));
			assertThat(driver.findElement(By.id("itemPrice1")).getText(),
					is("¥ 800"));
			assertThat(driver.findElement(By.id("itemQuantity1")).getText(),
					is("1"));
			assertThat(driver.findElement(By.id("totalPrice")).getText(),
					is("¥ 2,600"));
		}
 
		// click the Account Update button
		{
			driver.findElement(By.id("updateAccount")).click();
		}
 
		// click the home button
		{
			driver.findElement(By.id("home")).click();
		}
 
		// check update value
		{
			assertThat(driver.findElement(By.id("itemName0")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("itemPrice0")).getText(),
					is("¥ 900"));
			assertThat(driver.findElement(By.id("itemQuantity0")).getText(),
					is("2"));
			assertThat(driver.findElement(By.id("itemName1")).getText(),
					is("〔Ame ni mo Makezu〕"));
			assertThat(driver.findElement(By.id("itemPrice1")).getText(),
					is("¥ 800"));
			assertThat(driver.findElement(By.id("itemQuantity1")).getText(),
					is("1"));
			assertThat(driver.findElement(By.id("totalPrice")).getText(),
					is("¥ 2,600"));
		}
	}
 
	@Test
	public void testDeleteCartItem() {
 
		// login
		defaultLogin();
 
		// click the addCart button
		{
			driver.findElement(By.id("quantity0")).clear();
			driver.findElement(By.id("quantity0")).sendKeys("2");
			driver.findElement(By.id("add0")).click();
		}
 
		// click the addCart button
		{
			driver.findElement(By.id("quantity1")).clear();
			driver.findElement(By.id("quantity1")).sendKeys("1");
			driver.findElement(By.id("add1")).click();
		}
 
		// click the viewCart button
		{
			driver.findElement(By.id("viewCart")).click();
		}
 
		// check the second item and click the remove button
		{
			driver.findElement(By.id("removedItemsIds1")).click();
			driver.findElement(By.id("remove")).click();
		}
 
		// check update value
		{
			assertThat(driver.findElement(By.id("itemName0")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("itemPrice0")).getText(),
					is("¥ 900"));
			assertThat(driver.findElement(By.id("itemQuantity0")).getText(),
					is("2"));
			assertThat(driver.findElement(By.id("totalPrice")).getText(),
					is("¥ 1,800"));
		}
 
		// Click the remove button without checking deletion
		{
			driver.findElement(By.id("remove")).click();
		}
 
		// check error message
		{
			assertThat(driver.findElement(By.id("removedItemsIds.errors"))
					.getText(), is("may not be empty"));
		}
	}
 
	@Test
	public void testOrderCartItem() {
 
		// login
		defaultLogin();
 
		// click the addCart button
		{
			driver.findElement(By.id("quantity0")).clear();
			driver.findElement(By.id("quantity0")).sendKeys("2");
			driver.findElement(By.id("add0")).click();
		}
 
		// click the addCart button
		{
			driver.findElement(By.id("quantity1")).clear();
			driver.findElement(By.id("quantity1")).sendKeys("1");
			driver.findElement(By.id("add1")).click();
		}
 
		// click the viewCart button
		{
			driver.findElement(By.id("viewCart")).click();
		}
 
		// click confirm your order button
		{
			driver.findElement(By.id("confirm")).click();
		}
 
		// check update value
		{
			assertThat(driver.findElement(By.id("itemName0")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("itemPrice0")).getText(),
					is("¥ 900"));
			assertThat(driver.findElement(By.id("itemQuantity0")).getText(),
					is("2"));
			assertThat(driver.findElement(By.id("itemName1")).getText(),
					is("〔Ame ni mo Makezu〕"));
			assertThat(driver.findElement(By.id("itemPrice1")).getText(),
					is("¥ 800"));
			assertThat(driver.findElement(By.id("itemQuantity1")).getText(),
					is("1"));
			assertThat(driver.findElement(By.id("totalPrice")).getText(),
					is("¥ 2,600"));
 
			assertThat(driver.findElement(By.id("email")).getText(),
					is("a@b.com"));
 
		}
 
		// click confirm your order button
		{
			driver.findElement(By.id("order")).click();
		}
 
		// check update value
		{
			assertThat(driver.findElement(By.id("orderNumber")).getText()
					.length(), is(36));
 
			assertThat(driver.findElement(By.id("itemName0")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("itemPrice0")).getText(),
					is("¥ 900"));
			assertThat(driver.findElement(By.id("itemQuantity0")).getText(),
					is("2"));
			assertThat(driver.findElement(By.id("itemName1")).getText(),
					is("〔Ame ni mo Makezu〕"));
			assertThat(driver.findElement(By.id("itemPrice1")).getText(),
					is("¥ 800"));
			assertThat(driver.findElement(By.id("itemQuantity1")).getText(),
					is("1"));
			assertThat(driver.findElement(By.id("totalPrice")).getText(),
					is("¥ 2,600"));
		}
 
	}
 
	private void defaultLogin() {
		driver.findElement(By.id("email")).sendKeys("a@b.com");
		driver.findElement(By.id("password")).sendKeys("demo");
		driver.findElement(By.id("login")).click();
	}
 
	@After
	public void tearDown() {
 
		// click logout button
		{
			driver.findElement(By.id("logout")).click();
		}

	}
 
}