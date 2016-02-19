package com.example.session.selenium;
 
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
 
import com.example.session.FunctionTestSupport;
 
@ContextConfiguration(locations = { "classpath:META-INF/spring/seleniumContext.xml" })
public class SessionTutorialTest extends FunctionTestSupport {
 
	WebDriver driver;
 
	@Value("${selenium.applicationContextUrl}")
	String baseUrl;
 
	@Before
	public void setUp() {
 
		if (driver == null) {
			driver = createDefaultLocaleDriver();
		}
 
		// ログイン画面を表示
		{
			driver.get(baseUrl);
		}
 
	}
 
	@Test
	public void testInitialLogin() {
 
		// ログイン
		defaultLogin();
 
		// 初期値を確認
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
 
		// アカウント作成画面に遷移
		{
			driver.findElement(By.id("createAccount")).click();
		}
 
		// フォームに値を入れてconfirmボタンをクリック
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
 
		// 更新値を確認
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
 
		// backボタンをクリック
		{
			driver.findElement(By.id("back")).click();
		}
 
		// 更新値を確認
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
 
		// confirmボタンをクリック
		{
			driver.findElement(By.id("password")).sendKeys("password");
			driver.findElement(By.id("confirmPassword")).sendKeys("password");
			driver.findElement(By.id("confirm")).click();
		}
 
		// createボタンをクリック
		{
			driver.findElement(By.id("create")).click();
		}
 
		// 更新値を確認
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
 
		// Login pageボタンをクリック
		{
			driver.findElement(By.id("home")).click();
		}
 
		// ログイン
		{
			driver.findElement(By.id("email")).sendKeys("test@xxx.co.jp");
			driver.findElement(By.id("password")).sendKeys("password");
			driver.findElement(By.id("login")).click();
		}
 
		// 初期値を確認
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
 
		// ログイン
		defaultLogin();
 
		// アイテム名をクリック
		{
			driver.findElement(By.id("Kokoro")).click();
		}
 
		// 表示を確認
		{
			assertThat(driver.findElement(By.id("name")).getText(),
					is("Kokoro"));
			assertThat(driver.findElement(By.id("price")).getText(),
					is("¥ 900"));
			assertThat(driver.findElement(By.id("description")).getText(),
					is("Souseki Natsume wrote this book"));
		}
 
		// homeボタンをクリック
		{
			driver.findElement(By.id("home")).click();
		}
 
		// カテゴリを更新
		{
			new Select(driver.findElement(By.id("categoryId")))
					.selectByVisibleText("music");
			driver.findElement(By.id("update")).click();
		}
 
		// 表示を確認
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
 
		// アイテム名をクリック
		{
			driver.findElement(By.id("Swan Lake")).click();
		}
 
		// 表示を確認
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
 
		// ログイン
		defaultLogin();
 
		// Account Updateボタンをクリック
		{
			driver.findElement(By.id("updateAccount")).click();
		}
 
		// デフォルト値を確認
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
 
		// nextボタンをクリック
		{
			driver.findElement(By.id("name")).clear();
			driver.findElement(By.id("name")).sendKeys("test");
			driver.findElement(By.id("address")).clear();
			driver.findElement(By.id("address")).sendKeys("Osaka");
			driver.findElement(By.id("next")).click();
		}
 
		// デフォルト値を確認
		{
			assertThat(
					driver.findElement(By.id("cardNumber")).getAttribute(
							"value"), is("1111111111111111"));
			assertThat(driver.findElement(By.id("cardExpirationDate"))
					.getAttribute("value"), is("2015-08"));
			assertThat(driver.findElement(By.id("cardSecurityCode"))
					.getAttribute("value"), is("111"));
		}
 
		// backボタンをクリック
		{
			driver.findElement(By.id("back")).click();
		}
 
		// 入力した値が残っていることを確認
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
 
		// nextボタンをクリック
		{
			driver.findElement(By.id("next")).click();
		}
 
		// confirmボタンをクリック
		{
			driver.findElement(By.id("cardNumber")).clear();
			driver.findElement(By.id("cardNumber"))
					.sendKeys("1234567890123456");
			driver.findElement(By.id("confirm")).click();
		}
 
		// 入力値を確認
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
 
		// backボタンをクリック
		{
			driver.findElement(By.id("back")).click();
		}
 
		// 入力した値が残っていることを確認
		{
			assertThat(
					driver.findElement(By.id("cardNumber")).getAttribute(
							"value"), is("1234567890123456"));
			assertThat(driver.findElement(By.id("cardExpirationDate"))
					.getAttribute("value"), is("2015-08"));
			assertThat(driver.findElement(By.id("cardSecurityCode"))
					.getAttribute("value"), is("111"));
		}
 
		// confirmボタンをクリック
		{
			driver.findElement(By.id("confirm")).click();
		}
 
		// updateボタンをクリック
		{
			driver.findElement(By.id("update")).click();
		}
 
		// 更新値を確認
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
 
		// homeボタンをクリック
		{
			driver.findElement(By.id("home")).click();
		}
 
		// 更新値を確認
		{
			assertThat(driver.findElement(By.id("userName")).getText(),
					is("test"));
		}
 
		// Account Updateボタンをクリック
		{
			driver.findElement(By.id("updateAccount")).click();
		}
 
		// nextボタンをクリック
		{
			driver.findElement(By.id("name")).clear();
			driver.findElement(By.id("name")).sendKeys("aaaaaa");
			driver.findElement(By.id("address")).clear();
			driver.findElement(By.id("address")).sendKeys("Nagoya");
			driver.findElement(By.id("next")).click();
		}
 
		// homeボタンをクリック
		{
			driver.findElement(By.id("home")).click();
		}
 
		// Account Updateボタンをクリック
		{
			driver.findElement(By.id("updateAccount")).click();
		}
 
		// 更新値を確認
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
 
		// 更新された値をもとの値に更新しなおす
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
 
		// ログイン
		defaultLogin();
 
		// addCartボタンをクリック
		{
			driver.findElement(By.id("quantity0")).clear();
			driver.findElement(By.id("quantity0")).sendKeys("2");
			driver.findElement(By.id("add0")).click();
		}
 
		// 更新値を確認
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
 
		// addCartボタンをクリック
		{
			driver.findElement(By.id("quantity1")).clear();
			driver.findElement(By.id("quantity1")).sendKeys("1");
			driver.findElement(By.id("add1")).click();
		}
 
		// 更新値を確認
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
 
		// Account Updateボタンをクリック
		{
			driver.findElement(By.id("updateAccount")).click();
		}
 
		// homeボタンをクリック
		{
			driver.findElement(By.id("home")).click();
		}
 
		// 更新値を確認
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
 
		// ログイン
		defaultLogin();
 
		// addCartボタンをクリック
		{
			driver.findElement(By.id("quantity0")).clear();
			driver.findElement(By.id("quantity0")).sendKeys("2");
			driver.findElement(By.id("add0")).click();
		}
 
		// addCartボタンをクリック
		{
			driver.findElement(By.id("quantity1")).clear();
			driver.findElement(By.id("quantity1")).sendKeys("1");
			driver.findElement(By.id("add1")).click();
		}
 
		// viewCartボタンをクリック
		{
			driver.findElement(By.id("viewCart")).click();
		}
 
		// 2つめのアイテムにチェックを入れてremoveボタンをクリック
		{
			driver.findElement(By.id("removedItemsIds1")).click();
			driver.findElement(By.id("remove")).click();
		}
 
		// 更新値を確認
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
 
		// 削除チェックをせずにremoveボタンをクリック
		{
			driver.findElement(By.id("remove")).click();
		}
 
		// エラーメッセージを確認
		{
			assertThat(driver.findElement(By.id("removedItemsIds.errors"))
					.getText(), is("may not be empty"));
		}
	}
 
	@Test
	public void testOrderCartItem() {
 
		// ログイン
		defaultLogin();
 
		// addCartボタンをクリック
		{
			driver.findElement(By.id("quantity0")).clear();
			driver.findElement(By.id("quantity0")).sendKeys("2");
			driver.findElement(By.id("add0")).click();
		}
 
		// addCartボタンをクリック
		{
			driver.findElement(By.id("quantity1")).clear();
			driver.findElement(By.id("quantity1")).sendKeys("1");
			driver.findElement(By.id("add1")).click();
		}
 
		// viewCartボタンをクリック
		{
			driver.findElement(By.id("viewCart")).click();
		}
 
		// confirm your orderボタンをクリック
		{
			driver.findElement(By.id("confirm")).click();
		}
 
		// 更新値を確認
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
 
		// confirm your orderボタンをクリック
		{
			driver.findElement(By.id("order")).click();
		}
 
		// 更新値を確認
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
 
		// logoutボタンをクリック
		{
			driver.findElement(By.id("logout")).click();
		}
		driver.quit();
	}
 
}