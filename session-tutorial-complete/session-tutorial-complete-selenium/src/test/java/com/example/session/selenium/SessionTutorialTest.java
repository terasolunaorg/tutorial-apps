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
 
		// show login page
		{
			webDriverOperations.displayPage(baseUrl);
		}
 
	}
 
	@Test
	public void testInitialLogin() {
 
		// login
		defaultLogin();
 
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
 
		// move creatAccount page
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
 
		// click back button
		{
			webDriverOperations.click(By.id("back"));
		}
 
		// check update value
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
			assertThat(webDriverOperations.getText(By.id("〔Ame ni mo Makezu〕"))
					, is("〔Ame ni mo Makezu〕"));
			assertThat(webDriverOperations.getText(By.id("Run, Melos!")),
					is("Run, Melos!"));
			assertThat(webDriverOperations.getText(By.id("userName")),
					is("test"));
 
		}
	}
 
	@Test
	public void testShowGoods() {
 
		// login
		defaultLogin();
 
		// click the item name
		{
			webDriverOperations.click(By.id("Kokoro"));
		}
 
		// check the display page
		{
			assertThat(webDriverOperations.getText(By.id("name")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("price")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("description")),
					is("Souseki Natsume wrote this book"));
		}
 
		// click the home button
		{
			webDriverOperations.click(By.id("home"));
		}
 
		// update category
		{
			webDriverOperations.select(By.id("categoryId"),"music");
			webDriverOperations.click(By.id("update"));
		}
 
		// check the display page
		{
			assertThat(
					webDriverOperations.getText(
							By.id("Symphony No. 5 in C minor (Fate)"))
							, is("Symphony No. 5 in C minor (Fate)"));
			assertThat(webDriverOperations.getText(By.id("Eine kleine Nachtmusik"))
					, is("Eine kleine Nachtmusik"));
			assertThat(webDriverOperations.getText(By.id("Swan Lake")),
					is("Swan Lake"));
		}
 
		// click the item name
		{
			webDriverOperations.click(By.id("Swan Lake"));
		}
		// check page
		{
			assertThat(webDriverOperations.getText(By.id("name")),
					is("Swan Lake"));
			assertThat(webDriverOperations.getText(By.id("price")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("description")),
					is("Tchaikovsky composed this music"));
		}
 
	}
 
	@Test
	public void testChangeAcountInfo() {
 
		// login
		defaultLogin();
 
 		// click Account Update button
		{
			webDriverOperations.click(By.id("updateAccount"));
		}
 
		// check the form default page
		{
			assertThat(webDriverOperations.getInputFieldValue(By.id("name")),
					is("xxx"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("email")),
					is("a@b.com"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("birthday")),
					is("2015-08-01"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("zip")),
					is("1111111"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("address")),
					is("Tokyo"));
		}
 
		// click the next button
		{
			webDriverOperations.clearText(By.id("name"));
			webDriverOperations.overrideText(By.id("name"),("test"));
			webDriverOperations.clearText(By.id("address"));
			webDriverOperations.overrideText(By.id("address"),"Osaka");
			webDriverOperations.click(By.id("next"));
		}
 
		// check initial value
		{
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("cardNumber")),
					is("1111111111111111"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("cardExpirationDate")),
					is("2015-08"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("cardSecurityCode")),
					is("111"));
		}
 
		// click the buck button
		{
			webDriverOperations.click(By.id("back"));
		}
 
		// check that the entered value remains
		{
			assertThat(webDriverOperations.getInputFieldValue(By.id("name")),
					is("test"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("email")),
					is("a@b.com"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("birthday")),
					is("2015-08-01"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("zip")),
					is("1111111"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("address")),
					is("Osaka"));
		}
 
		// click the next button
		{
			webDriverOperations.click(By.id("next"));
		}
 
		// click the confirm button
		{
			webDriverOperations.clearText(By.id("cardNumber"));
			webDriverOperations.overrideText(By.id("cardNumber"),"1234567890123456");
			webDriverOperations.click(By.id("confirm"));
		}
 
		// check the input value
		{
			assertThat(webDriverOperations.getText(By.id("name")), is("test"));
			assertThat(webDriverOperations.getText(By.id("email")),
					is("a@b.com"));
			assertThat(webDriverOperations.getText(By.id("birthday")),
					is("2015-08-01"));
			assertThat(webDriverOperations.getText(By.id("zip")),
					is("1111111"));
			assertThat(webDriverOperations.getText(By.id("address")),
					is("Osaka"));
			assertThat(webDriverOperations.getText(By.id("cardNumber")),
					is("****-****-****-3456"));
			assertThat(webDriverOperations.getText(By.id("cardExpirationDate"))
					, is("2015-08"));
			assertThat(webDriverOperations.getText(By.id("cardSecurityCode")),
					is("111"));
		}
 
		// click the buck button
		{
			webDriverOperations.click(By.id("back"));
		}
 
		// check that the entered value remains
		{
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("cardNumber")),
					is("1234567890123456"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("cardExpirationDate")),
					is("2015-08"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("cardSecurityCode")),
					is("111"));
		}
 
		// click the confirm button
		{
			webDriverOperations.click(By.id("confirm"));
		}
 
		// click the update button
		{
			webDriverOperations.click(By.id("update"));
		}
 
		// check update value
		{
			assertThat(webDriverOperations.getText(By.id("name")), is("test"));
			assertThat(webDriverOperations.getText(By.id("email")),
					is("a@b.com"));
			assertThat(webDriverOperations.getText(By.id("birthday")),
					is("2015-08-01"));
			assertThat(webDriverOperations.getText(By.id("zip")),
					is("1111111"));
			assertThat(webDriverOperations.getText(By.id("address")),
					is("Osaka"));
			assertThat(webDriverOperations.getText(By.id("cardNumber")),
					is("****-****-****-3456"));
			assertThat(webDriverOperations.getText(By.id("cardExpirationDate"))
					, is("2015-08"));
			assertThat(webDriverOperations.getText(By.id("cardSecurityCode")),
					is("111"));
		}
 
		// click the home button
		{
			webDriverOperations.click(By.id("home"));
		}
 
		// check update value
		{
			assertThat(webDriverOperations.getText(By.id("userName")),
					is("test"));
		}
 
		// click the Account Update button
		{
			webDriverOperations.click(By.id("updateAccount"));
		}
 
		// click the next button
		{
			webDriverOperations.clearText(By.id("name"));
			webDriverOperations.overrideText(By.id("name"),"aaaaaa");
			webDriverOperations.clearText(By.id("address"));
			webDriverOperations.overrideText(By.id("address"),"Nagoya");
			webDriverOperations.click(By.id("next"));
		}
 
		// click the home button
		{
			webDriverOperations.click(By.id("home"));
		}
 
		// click the Account Update button
		{
			webDriverOperations.click(By.id("updateAccount"));
		}
 
		// check update value
		{
			assertThat(webDriverOperations.getInputFieldValue(By.id("name")),
					is("test"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("email")),
					is("a@b.com"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("birthday")),
					is("2015-08-01"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("zip")),
					is("1111111"));
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("address")),
					is("Osaka"));
		}
 
		// Re-update the updated value to its original value
		{
			webDriverOperations.clearText(By.id("name"));
			webDriverOperations.overrideText(By.id("name"),"xxx");
			webDriverOperations.clearText(By.id("address"));
			webDriverOperations.overrideText(By.id("address"),"Tokyo");
			webDriverOperations.click(By.id("next"));
 
			webDriverOperations.clearText(By.id("cardNumber"));
			webDriverOperations.overrideText(By.id("cardNumber"),"1111111111111111");
			webDriverOperations.click(By.id("confirm"));
 
			webDriverOperations.click(By.id("update"));
 
			webDriverOperations.click(By.id("home"));
		}
	}
 
	@Test
	public void testAddCartItem() {
 
		// login
		defaultLogin();
 
		// click the addCart button
		{
			webDriverOperations.clearText(By.id("quantity0"));
			webDriverOperations.overrideText(By.id("quantity0"),"2");
			webDriverOperations.click(By.id("add0"));
		}
 
		// check update value
		{
			assertThat(webDriverOperations.getText(By.id("itemName0")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("itemPrice0")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("itemQuantity0")),
					is("2"));
			assertThat(webDriverOperations.getText(By.id("totalPrice")),
					is("¥1,800"));
		}
 
		// click the addCart button
		{
			webDriverOperations.clearText(By.id("quantity1"));
			webDriverOperations.overrideText(By.id("quantity1"),"1");
			webDriverOperations.click(By.id("add1"));
		}
 
		// check update value
		{
			assertThat(webDriverOperations.getText(By.id("itemName0")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("itemPrice0")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("itemQuantity0")),
					is("2"));
			assertThat(webDriverOperations.getText(By.id("itemName1")),
					is("〔Ame ni mo Makezu〕"));
			assertThat(webDriverOperations.getText(By.id("itemPrice1")),
					is("¥800"));
			assertThat(webDriverOperations.getText(By.id("itemQuantity1")),
					is("1"));
			assertThat(webDriverOperations.getText(By.id("totalPrice")),
					is("¥2,600"));
		}
 
		// click the Account Update button
		{
			webDriverOperations.click(By.id("updateAccount"));
		}
 
		// click the home button
		{
			webDriverOperations.click(By.id("home"));
		}
 
		// check update value
		{
			assertThat(webDriverOperations.getText(By.id("itemName0")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("itemPrice0")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("itemQuantity0")),
					is("2"));
			assertThat(webDriverOperations.getText(By.id("itemName1")),
					is("〔Ame ni mo Makezu〕"));
			assertThat(webDriverOperations.getText(By.id("itemPrice1")),
					is("¥800"));
			assertThat(webDriverOperations.getText(By.id("itemQuantity1")),
					is("1"));
			assertThat(webDriverOperations.getText(By.id("totalPrice")),
					is("¥2,600"));
		}
	}
 
	@Test
	public void testDeleteCartItem() {
 
		// login
		defaultLogin();
 
		// click the addCart button
		{
			webDriverOperations.clearText(By.id("quantity0"));
			webDriverOperations.overrideText(By.id("quantity0"),"2");
			webDriverOperations.click(By.id("add0"));
		}
 
		// click the addCart button
		{
			webDriverOperations.clearText(By.id("quantity1"));
			webDriverOperations.overrideText(By.id("quantity1"),"1");
			webDriverOperations.click(By.id("add1"));
		}
 
		// click the viewCart button
		{
			webDriverOperations.click(By.id("viewCart"));
		}
 
		// check the second item and click the remove button
		{
			webDriverOperations.click(By.id("removedItemsIds1"));
			webDriverOperations.click(By.id("remove"));
		}
 
		// check update value
		{
			assertThat(webDriverOperations.getText(By.id("itemName0")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("itemPrice0")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("itemQuantity0")),
					is("2"));
			assertThat(webDriverOperations.getText(By.id("totalPrice")),
					is("¥1,800"));
		}
 
		// Click the remove button without checking deletion
		{
			webDriverOperations.click(By.id("remove"));
		}
 
		// check error message
		{
			assertThat(webDriverOperations.getText(By.id("removedItemsIds.errors"))
					, is("may not be empty"));
		}
	}
 
	@Test
	public void testOrderCartItem() {
 
		// login
		defaultLogin();
 
		// click the addCart button
		{
			webDriverOperations.clearText(By.id("quantity0"));
			webDriverOperations.overrideText(By.id("quantity0"),"2");
			webDriverOperations.click(By.id("add0"));
		}
 
		// click the addCart button
		{
			webDriverOperations.clearText(By.id("quantity1"));
			webDriverOperations.overrideText(By.id("quantity1"),"1");
			webDriverOperations.click(By.id("add1"));
		}
 
		// click the viewCart button
		{
			webDriverOperations.click(By.id("viewCart"));
		}
 
		// click confirm your order button
		{
			webDriverOperations.click(By.id("confirm"));
		}
 
		// check update value
		{
			assertThat(webDriverOperations.getText(By.id("itemName0")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("itemPrice0")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("itemQuantity0")),
					is("2"));
			assertThat(webDriverOperations.getText(By.id("itemName1")),
					is("〔Ame ni mo Makezu〕"));
			assertThat(webDriverOperations.getText(By.id("itemPrice1")),
					is("¥800"));
			assertThat(webDriverOperations.getText(By.id("itemQuantity1")),
					is("1"));
			assertThat(webDriverOperations.getText(By.id("totalPrice")),
					is("¥2,600"));
 
			assertThat(webDriverOperations.getText(By.id("email")),
					is("a@b.com"));
 
		}
 
		// click confirm your order button
		{
			webDriverOperations.click(By.id("order"));
		}
 
		// check update value
		{
			assertThat(webDriverOperations.getText(By.id("orderNumber"))
					.length(), is(36));
 
			assertThat(webDriverOperations.getText(By.id("itemName0")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("itemPrice0")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("itemQuantity0")),
					is("2"));
			assertThat(webDriverOperations.getText(By.id("itemName1")),
					is("〔Ame ni mo Makezu〕"));
			assertThat(webDriverOperations.getText(By.id("itemPrice1")),
					is("¥800"));
			assertThat(webDriverOperations.getText(By.id("itemQuantity1")),
					is("1"));
			assertThat(webDriverOperations.getText(By.id("totalPrice")),
					is("¥2,600"));
		}
 
	}
 
	private void defaultLogin() {
		webDriverOperations.overrideText(By.id("email"),"a@b.com");
		webDriverOperations.overrideText(By.id("password"),"demo");
		webDriverOperations.click(By.id("login"));
	}
 
	@After
	public void tearDown() {
 
		// click logout button
		{
			webDriverOperations.click(By.id("logout"));
		}

	}
 
}