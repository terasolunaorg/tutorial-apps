package org.terasoluna.securelogin.selenium;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverOperations {

    protected final WebDriver webDriver;

    protected final WebDriverInputFieldAccessor webDriverInputFieldAccessor;

    protected final ScreenCapture screenCapture;

    protected final PageSource pageSource;

    protected final int offsetSecondsOfTimeoutForImplicitlyWait;

    protected int defaultTimeoutSecondsForImplicitlyWait = 10;

    public WebDriverOperations(WebDriver webDriver,
            WebDriverInputFieldAccessor webDriverInputFieldAccessor,
            ScreenCapture screenCapture, PageSource pageSource,
            int offsetSecondsOfTimeoutForImplicitlyWait) {
        this.webDriver = webDriver;
        this.webDriverInputFieldAccessor = webDriverInputFieldAccessor;
        this.screenCapture = screenCapture;
        this.pageSource = pageSource;
        this.offsetSecondsOfTimeoutForImplicitlyWait = offsetSecondsOfTimeoutForImplicitlyWait;
        setDefaultTimeoutForImplicitlyWait();
    }

    /**
     * 指定したURLのページを表示する。
     * @param url ページを表示するためのURL
     */
    public void displayPage(String url) {
        webDriver.get(url);
    }

    /**
     * 要素を見つけるまでの待機処理のデフォルトのタイムアウト値を設定する。
     * @param defaultTimeoutSecondsForImplicitlyWait 要素を見つけるまでの待機処理のデフォルトのタイムアウト値(秒)
     * @return WebDriverOperationsインスタンス
     */
    public WebDriverOperations setDefaultTimeoutForImplicitlyWait(
            int defaultTimeoutSecondsForImplicitlyWait) {
        this.defaultTimeoutSecondsForImplicitlyWait = defaultTimeoutSecondsForImplicitlyWait;
        setDefaultTimeoutForImplicitlyWait();
        return this;
    }

    /**
     * 要素を見つけるまでの待機処理のタイムアウト値をデフォルト値に設定する。
     */
    public void setDefaultTimeoutForImplicitlyWait() {
        setTimeoutForImplicitlyWait(defaultTimeoutSecondsForImplicitlyWait,
                true);
    }

    /**
     * 要素を見つけるまでの待機処理のタイムアウト値を設定する。
     */
    public void setTimeoutForImplicitlyWait(int timeoutSeconds,
            boolean applyOffset) {
        int adjustWaitTime;
        if (applyOffset) {
            adjustWaitTime = timeoutSeconds
                    + offsetSecondsOfTimeoutForImplicitlyWait;
        } else {
            adjustWaitTime = timeoutSeconds;
        }
        webDriver.manage().timeouts().implicitlyWait(adjustWaitTime,
                TimeUnit.SECONDS);
    }

    /**
     * WebDriverを返却する。
     * <p>
     * 固有の操作を行いたい場合は、このメソッドで取得したWebDriverを使用してブラウザの操作を行ってください。
     * </p>
     * @return WebDriver
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * 指定した要素(ボタンやリンクなど)をクリックする。
     * @param by 要素(ボタンやリンクなど)を探すための識別子
     */
    public void click(By by) {
        webDriver.findElement(by).click();
    }

    /**
     * 指定した要素(ボタンやリンクなど)を強制的にクリックする。
     * @param by 要素(ボタンやリンクなど)を探すための識別子
     */
    public void forceClick(By by) {
        getJavascriptExecutor().executeScript("arguments[0].click();",
                webDriver.findElement(by));
    }

    /**
     * 指定した要素(テキスト項目)に入力されている値をクリアする。
     * @param by 要素(テキスト項目)を探すための識別子
     */
    public void clearText(By by) {
        webDriver.findElement(by).clear();
    }

    /**
     * 指定した要素(テキストフィールド)に値を追加する。
     * @param by 要素(テキストフィールド)を探すための識別子
     */
    public void appendText(By by, String value) {
        webDriverInputFieldAccessor.appendValue(by, value, webDriver);
    }

    /**
     * 指定した要素(テキストフィールド)の値を上書きする。
     * @param by 要素(テキストフィールド)を探すための識別子
     */
    public void overrideText(By by, String value) {
        webDriverInputFieldAccessor.overrideValue(by, value, webDriver);
    }

    /**
     * 指定した要素(テキストエリア)に値を追加する。
     * <p>
     * 使用頻度が低いため、高速モード(JavaScript)モードは対応していない。
     * </p>
     * @param by 要素(テキストエリア)を探すための識別子
     */
    public void appendTextArea(By by, String value) {
        webDriver.findElement(by).sendKeys(value);
    }

    /**
     * 指定した要素(テキストエリア)の値を上書きする。
     * <p>
     * 使用頻度が低いため、高速モード(JavaScript)モードは対応していない。
     * </p>
     * @param by 要素(テキストエリア)を探すための識別子
     */
    public void overrideTextArea(By by, String value) {
        WebElement element = webDriver.findElement(by);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * 指定した要素(ファイル参照)に、指定したファイルを設定する。
     * @param by 要素(ファイル参照)を探すための識別子
     */
    public void referUploadFile(By by, File file) {
        WebElement element = webDriver.findElement(by);
        element.sendKeys(file.getAbsolutePath());
    }

    /**
     * 指定した要素(セレクト)を選択する。
     * @param by 要素(テキスト項目)を探すための識別子
     */
    public void select(By by, String value) {
        new Select(webDriver.findElement(by)).selectByVisibleText(value);
    }

    /**
     * 指定した要素に設定されているテキスト(表示値)を取得する。
     * @param by 要素を探すための識別子
     */
    public String getText(By by) {
        return webDriver.findElement(by).getText();
    }

    /**
     * 指定した入力フィールドに設定されている値を取得する。
     * @param by 要素を探すための識別子
     */
    public String getInputFieldValue(By by) {
        return webDriverInputFieldAccessor.getValue(by, webDriver);
    }

    /**
     * X-Trackを取得する。
     * @return X-Track
     */
    public String getXTrack() {
        return webDriver.findElement(getIdOfXTrack()).getText();
    }

    /**
     * X-Trackの識別オブジェクトを取得する。
     * @return X-Trackの識別オブジェクト
     */
    public By getIdOfXTrack() {
        return By.id("xTrack");
    }

    /**
     * 指定した要素が存在するかチェックする。
     * @param by 要素を探すための識別子
     * @return 指定した要素が存在する場合にtrueを返却する。
     */
    public boolean exists(By by) {
        waitForDisplayed(By.tagName("body"));
        setTimeoutForImplicitlyWait(0, false);
        boolean existsElement = true;
        try {
            webDriver.findElement(by).getText();
        } catch (NoSuchElementException e) {
            existsElement = false;
        } finally {
            setDefaultTimeoutForImplicitlyWait();
        }
        return existsElement;
    }

    /**
     * 指定した要素(ボタンやリンクなど)が表示されるのを待つ。
     * @param by 要素(ボタンやリンクなど)を探すための識別子
     */
    public void waitForDisplayed(By by) {
        webDriver.findElement(by);
    }

    /**
     * 指定された表示条件に一致するまで待つ。
     * @param expectedCondition 表示条件
     */
    public void waitForDisplayed(ExpectedCondition<?> expectedCondition) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), defaultTimeoutSecondsForImplicitlyWait
                + offsetSecondsOfTimeoutForImplicitlyWait);
        wait.until(expectedCondition);
    }

    /**
     * 指定した時間だけ処理を停止する。
     * @param waitTime 停止する時間
     * @param timeUnit 時間の単位
     */
    public void suspend(long waitTime, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cookieを取得する。
     * <p>
     * 指定したCookie名に対応するCookieを取得する。
     * </p>
     * @param cookieName Cookie名
     * @return 指定したCookie名に対応するCookie
     */
    public Cookie getCookie(String cookieName) {
        return webDriver.manage().getCookieNamed(cookieName);
    }

    /**
     * Cookieを削除する。
     * <p>
     * 指定したCookie名に対応するCookieを削除する。
     * </p>
     * @param cookieName Cookie名
     */
    public void deleteCookie(String cookieName) {
        webDriver.manage().deleteCookieNamed(cookieName);
    }

    /**
     * JavascriptExecutor を取得する。
     */
    public JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) webDriver;
    }

    /**
     * タイトルを取得する。
     */
    public String getTitle() {
        waitForDisplayed(By.tagName("title"));
        return webDriver.getTitle();
    }

    /**
     * アドレスバーの値を取得する。
     */
    public String getCurrentUrl() {
        waitForDisplayed(By.tagName("body"));
        return webDriver.getCurrentUrl();
    }

    /**
     * スクリーンキャプチャをエビデンスとして保存する。
     */
    public void saveScreenCapture() {
        screenCapture.save(webDriver);
    }

    /**
     * スクリーンキャプチャをエビデンスとして保存する。
     * <p>
     * 保存するファイル名の一部に、subTitleで指定した値が設定されます。
     * </p>
     * @param subTitle サブタイトル
     */
    public void saveScreenCapture(String subTitle) {
        screenCapture.save(webDriver, subTitle);
    }

    /**
     * HTMLのソースをエビデンスとして保存する。
     */
    public void savePageSource() {
        pageSource.save(webDriver);
    }

    /**
     * HTMLのソースをエビデンスとして保存する。
     * <p>
     * 保存するファイル名の一部に、subTitleで指定した値が設定されます。
     * </p>
     * @param subTitle サブタイトル
     */
    public void savePageSource(String subTitle) {
        pageSource.save(webDriver, subTitle);
    }

    /**
     * ブラウザの「戻る」を実施。
     */
    public void back() {
        webDriver.navigate().back();
    }
}
