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
		{
			webDriverOperations.overrideText(By.id("email"),"a@b.com");
			webDriverOperations.overrideText(By.id("password"),"demo");
			webDriverOperations.click(By.id("login"));
		}

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

		// 入力した値が残っていることを確認
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

		// ログイン
		testInitialLogin();

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
			assertThat(webDriverOperations.getText(By.id("Symphony No. 5 in C minor (Fate)")),
					is("Symphony No. 5 in C minor (Fate)"));
			assertThat(webDriverOperations.getText(By.id("Eine kleine Nachtmusik")),
					is("Eine kleine Nachtmusik"));
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

	@After
	public void tearDown() {

		// logoutボタンをクリック
		{
			webDriverOperations.click(By.id("logout"));
		}

	}

}
