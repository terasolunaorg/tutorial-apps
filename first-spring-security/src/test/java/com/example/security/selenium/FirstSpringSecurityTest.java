package com.example.security.selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import com.example.security.FunctionTestSupport;

@ContextConfiguration(locations = { "classpath:META-INF/spring/seleniumContext.xml" })
public class FirstSpringSecurityTest extends FunctionTestSupport {

    WebDriver driver;

    @Value("${selenium.applicationContextUrl}/")
    String baseUrl;

    @Value("${selenium.contextName}")
    String contextName;

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

    /**
     * ログイン画面の表示テスト<br>
     * <li>プロジェクトのルートにアクセスし、ログイン画面に遷移すること。 <li>ログイン画面のusernameの初期値がdemoであること <li>
     * ログイン画面のpasswordの初期値がdemoであること
     */
    @Test
    public void testLoginForm() {

        // 初期値を確認
        {
            assertThat(
                    driver.findElement(By.id("username")).getAttribute("value"),
                    is("demo"));
            assertThat(
                    driver.findElement(By.id("password")).getAttribute("value"),
                    is("demo"));
            saveScreenCapture(driver, "testLoginForm");

        }

    }

    /**
     * Home画面の表示テスト<br>
     * ログイン画面を表示した後に、デフォルトのユーザでログインする。<br>
     * <li>ログイン画面に"Hello World!"が表示されていること <li>
     * ログイン画面に"Welcome Taro Yamada !!"が表示されていること <li>
     * ログイン画面に"view account"のリンクが表示されていること
     */
    @Test
    public void testLogin() {

        // デフォルトログイン
        defaultLogin();

        // Home画面を確認
        {
            assertThat(driver.findElement(By.tagName("h1")).getText(),
                    is("Hello world!"));

            List<WebElement> cheese = driver.findElements(By.tagName("p"));
            assertThat(cheese.get(1).getText(), is("Welcome Taro Yamada !!"));
            assertThat(
                    driver.findElement(
                            By.xpath("//a[@href='/" + contextName
                                    + "/account']")).getText(),
                    is("view account"));
            saveScreenCapture(driver, "testLogin");
        }

    }

    /**
     * ログアウト処理のテスト デフォルトのユーザでログイン後、Home画面でログアウトボタンを押下する。 <li>
     * ログアウトボタン押下後にログイン画面に戻ること <li>ログイン画面のusernameの初期値がdemoであること <li>
     * ログイン画面のpasswordの初期値がdemoであること
     */
    @Test
    public void testLogout() {

        // ログイン
        defaultLogin();

        // Home画面でログアウトボタン押下
        {
            saveScreenCapture(driver, "testLogout");
            driver.findElement(By.tagName("button")).click();
        }

        // ログイン画面の確認
        {
            assertThat(
                    driver.findElement(By.id("username")).getAttribute("value"),
                    is("demo"));
            assertThat(
                    driver.findElement(By.id("password")).getAttribute("value"),
                    is("demo"));
            saveScreenCapture(driver, "testLogout");
        }

    }

    /**
     * アカウント確認画面のテスト<br>
     * ログイン後、アカウント画面へのリンクを押下する。<br>
     * <li>Usernameが"demo"であること <li>First nameが"Taro"であること <li>Last
     * nameが"Yamada"であること
     * 
     */
    @Test
    public void testViewAccount() {

        // ログイン
        defaultLogin();

        // アカウント確認画面へのリンク押下
        {
            saveScreenCapture(driver, "testViewAccount");
            driver.findElement(
                    By.xpath("//a[@href='/" + contextName + "/account']"))
                    .click();
        }

        // アカウント確認画面の確認
        {
            // テーブルの取得
            List<WebElement> cheese = driver.findElements(By
                    .xpath("//table/tbody/tr/td"));

            assertThat(cheese.get(0).getText(), is("demo"));
            assertThat(cheese.get(1).getText(), is("Taro"));
            assertThat(cheese.get(2).getText(), is("Yamada"));
            saveScreenCapture(driver, "testViewAccount");
        }

    }

    /**
     * 登録外ユーザでの認証テスト
     * ログイン画面を表示し、登録されていないユーザ名を入力してログインボタンを押下する。
     * <li>ログイン画面にエラーメッセージ"Bad credentials"が出力されること
     */
    @Test
    public void testInvalidUserLogin() {

        // 登録されていないユーザでログイン
        {
            driver.findElement(By.id("username")).clear();
            driver.findElement(By.id("username")).sendKeys("aaa");
            saveScreenCapture(driver, "testInvalidUserLogin");
            driver.findElement(By.name("submit")).click();

        }

        // エラーメッセージの確認・ログイン画面の確認
        {
            assertThat(driver.findElement(By.tagName("li")).getText(),
                    is("Bad credentials"));

            assertThat(
                    driver.findElement(By.id("username")).getAttribute("value"),
                    is("demo"));
            assertThat(
                    driver.findElement(By.id("password")).getAttribute("value"),
                    is("demo"));
            saveScreenCapture(driver, "testInvalidUserLogin");
        }
    }

    @After
    public void tearDown() {

        driver.close();
        driver.quit();
    }

    /**
     * ログインする
     */
    public void defaultLogin() {

        // ログイン
        driver.findElement(By.name("submit")).click();
    }

}
