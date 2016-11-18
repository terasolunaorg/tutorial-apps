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
 
		// ログイン画面を表示
		{
			webDriverOperations.displayPage(baseUrl);
		}
 
	}
 
	@Test
	public void testInitialLogin() {
 
		// ログイン
		defaultLogin();
 
		// 初期値を確認
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
 
		// アカウント作成画面に遷移
		{
			webDriverOperations.click(By.id("createAccount"));
		}
 
		// フォームに値を入れてconfirmボタンをクリック
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
 
		// 更新値を確認
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
 
		// backボタンをクリック
		{
			webDriverOperations.click(By.id("back"));
		}
 
		// 更新値を確認
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
 
		// confirmボタンをクリック
		{
			webDriverOperations.overrideText(By.id("password"),"password");
			webDriverOperations.overrideText(By.id("confirmPassword"),"password");
			webDriverOperations.click(By.id("confirm"));
		}
 
		// createボタンをクリック
		{
			webDriverOperations.click(By.id("create"));
		}
 
		// 更新値を確認
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
 
		// Login pageボタンをクリック
		{
			webDriverOperations.click(By.id("home"));
		}
 
		// ログイン
		{
			webDriverOperations.overrideText(By.id("email"),"test@xxx.co.jp");
			webDriverOperations.overrideText(By.id("password"),"password");
			webDriverOperations.click(By.id("login"));
		}
 
		// 初期値を確認
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
 
		// ログイン
		defaultLogin();
 
		// アイテム名をクリック
		{
			webDriverOperations.click(By.id("Kokoro"));
		}
 
		// 表示を確認
		{
			assertThat(webDriverOperations.getText(By.id("name")),
					is("Kokoro"));
			assertThat(webDriverOperations.getText(By.id("price")),
					is("¥900"));
			assertThat(webDriverOperations.getText(By.id("description")),
					is("Souseki Natsume wrote this book"));
		}
 
		// homeボタンをクリック
		{
			webDriverOperations.click(By.id("home"));
		}
 
		// カテゴリを更新
		{
			webDriverOperations.select(By.id("categoryId"),"music");
			webDriverOperations.click(By.id("update"));
		}
 
		// 表示を確認
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
 
		// アイテム名をクリック
		{
			webDriverOperations.click(By.id("Swan Lake"));
		}
 
		// 表示を確認
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
 
		// ログイン
		defaultLogin();
 
		// Account Updateボタンをクリック
		{
			webDriverOperations.click(By.id("updateAccount"));
		}
 
		// デフォルト値を確認
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
 
		// nextボタンをクリック
		{
			webDriverOperations.clearText(By.id("name"));
			webDriverOperations.overrideText(By.id("name"),("test"));
			webDriverOperations.clearText(By.id("address"));
			webDriverOperations.overrideText(By.id("address"),"Osaka");
			webDriverOperations.click(By.id("next"));
		}
 
		// デフォルト値を確認
		{
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("cardNumber")),
					is("1111111111111111"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("cardExpirationDate")),
					is("2015-08"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("cardSecurityCode")),
					is("111"));
		}
 
		// backボタンをクリック
		{
			webDriverOperations.click(By.id("back"));
		}
 
		// 入力した値が残っていることを確認
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
 
		// nextボタンをクリック
		{
			webDriverOperations.click(By.id("next"));
		}
 
		// confirmボタンをクリック
		{
			webDriverOperations.clearText(By.id("cardNumber"));
			webDriverOperations.overrideText(By.id("cardNumber"),"1234567890123456");
			webDriverOperations.click(By.id("confirm"));
		}
 
		// 入力値を確認
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
 
		// backボタンをクリック
		{
			webDriverOperations.click(By.id("back"));
		}
 
		// 入力した値が残っていることを確認
		{
			assertThat(
					webDriverOperations.getInputFieldValue(By.id("cardNumber")),
					is("1234567890123456"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("cardExpirationDate")),
					is("2015-08"));
			assertThat(webDriverOperations.getInputFieldValue(By.id("cardSecurityCode")),
					is("111"));
		}
 
		// confirmボタンをクリック
		{
			webDriverOperations.click(By.id("confirm"));
		}
 
		// updateボタンをクリック
		{
			webDriverOperations.click(By.id("update"));
		}
 
		// 更新値を確認
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
 
		// homeボタンをクリック
		{
			webDriverOperations.click(By.id("home"));
		}
 
		// 更新値を確認
		{
			assertThat(webDriverOperations.getText(By.id("userName")),
					is("test"));
		}
 
		// Account Updateボタンをクリック
		{
			webDriverOperations.click(By.id("updateAccount"));
		}
 
		// nextボタンをクリック
		{
			webDriverOperations.clearText(By.id("name"));
			webDriverOperations.overrideText(By.id("name"),"aaaaaa");
			webDriverOperations.clearText(By.id("address"));
			webDriverOperations.overrideText(By.id("address"),"Nagoya");
			webDriverOperations.click(By.id("next"));
		}
 
		// homeボタンをクリック
		{
			webDriverOperations.click(By.id("home"));
		}
 
		// Account Updateボタンをクリック
		{
			webDriverOperations.click(By.id("updateAccount"));
		}
 
		// 更新値を確認
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
 
		// 更新された値をもとの値に更新しなおす
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
 
		// ログイン
		defaultLogin();
 
		// addCartボタンをクリック
		{
			webDriverOperations.clearText(By.id("quantity0"));
			webDriverOperations.overrideText(By.id("quantity0"),"2");
			webDriverOperations.click(By.id("add0"));
		}
 
		// 更新値を確認
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
 
		// addCartボタンをクリック
		{
			webDriverOperations.clearText(By.id("quantity1"));
			webDriverOperations.overrideText(By.id("quantity1"),"1");
			webDriverOperations.click(By.id("add1"));
		}
 
		// 更新値を確認
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
 
		// Account Updateボタンをクリック
		{
			webDriverOperations.click(By.id("updateAccount"));
		}
 
		// homeボタンをクリック
		{
			webDriverOperations.click(By.id("home"));
		}
 
		// 更新値を確認
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
 
		// ログイン
		defaultLogin();
 
		// addCartボタンをクリック
		{
			webDriverOperations.clearText(By.id("quantity0"));
			webDriverOperations.overrideText(By.id("quantity0"),"2");
			webDriverOperations.click(By.id("add0"));
		}
 
		// addCartボタンをクリック
		{
			webDriverOperations.clearText(By.id("quantity1"));
			webDriverOperations.overrideText(By.id("quantity1"),"1");
			webDriverOperations.click(By.id("add1"));
		}
 
		// viewCartボタンをクリック
		{
			webDriverOperations.click(By.id("viewCart"));
		}
 
		// 2つめのアイテムにチェックを入れてremoveボタンをクリック
		{
			webDriverOperations.click(By.id("removedItemsIds1"));
			webDriverOperations.click(By.id("remove"));
		}
 
		// 更新値を確認
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
 
		// 削除チェックをせずにremoveボタンをクリック
		{
			webDriverOperations.click(By.id("remove"));
		}
 
		// エラーメッセージを確認
		{
			assertThat(webDriverOperations.getText(By.id("removedItemsIds.errors"))
					, is("may not be empty"));
		}
	}
 
	@Test
	public void testOrderCartItem() {
 
		// ログイン
		defaultLogin();
 
		// addCartボタンをクリック
		{
			webDriverOperations.clearText(By.id("quantity0"));
			webDriverOperations.overrideText(By.id("quantity0"),"2");
			webDriverOperations.click(By.id("add0"));
		}
 
		// addCartボタンをクリック
		{
			webDriverOperations.clearText(By.id("quantity1"));
			webDriverOperations.overrideText(By.id("quantity1"),"1");
			webDriverOperations.click(By.id("add1"));
		}
 
		// viewCartボタンをクリック
		{
			webDriverOperations.click(By.id("viewCart"));
		}
 
		// confirm your orderボタンをクリック
		{
			webDriverOperations.click(By.id("confirm"));
		}
 
		// 更新値を確認
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
 
		// confirm your orderボタンをクリック
		{
			webDriverOperations.click(By.id("order"));
		}
 
		// 更新値を確認
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
 
		// logoutボタンをクリック
		{
			webDriverOperations.click(By.id("logout"));
		}

	}
 
}