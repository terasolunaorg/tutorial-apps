/*
 * Copyright (C) 2013-2016 NTT DATA Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package todo.selenium;

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

/**
 * This Class providing browser operation (logic for WebDriver).
 */
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
     * Display the page with the specified URL.
     * @param url : URL for displaying the page
     */
    public void displayPage(String url) {
        webDriver.get(url);
    }

    /**
     * Set the default timeout value of standby processing until finding an element.
     * @param defaultTimeoutSecondsForImplicitlyWait : Default timeout value (seconds) for wait processing until finding an element
     * @return WebDriverOperations instance
     */
    public WebDriverOperations setDefaultTimeoutForImplicitlyWait(
            int defaultTimeoutSecondsForImplicitlyWait) {
        this.defaultTimeoutSecondsForImplicitlyWait = defaultTimeoutSecondsForImplicitlyWait;
        setDefaultTimeoutForImplicitlyWait();
        return this;
    }

    /**
     * Set the timeout value of standby processing to the default value.
     */
    public void setDefaultTimeoutForImplicitlyWait() {
        setTimeoutForImplicitlyWait(defaultTimeoutSecondsForImplicitlyWait,
                true);
    }

    /**
     * Set the timeout value of standby processing.
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
     * Return WebDriver.
     * <p>
     * If you want to perform unique operations, please use the WebDriver obtained by this method to operate the browser.
     * </p>
     * @return WebDriver
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * Click the specified element (button, link, etc.).
     * @param by : Identifier for finding elements (buttons, links, etc.)
     */
    public void click(By by) {
        webDriver.findElement(by).click();
    }

    /**
     * Forcibly click on the specified element (button, link, etc.).
     * @param by : Identifier for finding elements (buttons, links, etc.)
     */
    public void forceClick(By by) {
        getJavascriptExecutor().executeScript("arguments[0].click();",
                webDriver.findElement(by));
    }

    /**
     * Clear the value entered in the specified element (text item).
     * @param by : identifier for searching for an element (text item)
     */
    public void clearText(By by) {
        webDriver.findElement(by).clear();
    }

    /**
     * Add a value to the specified element (text field).
     * @param by : Identifier for looking up an element (text field)
     */
    public void appendText(By by, String value) {
        webDriverInputFieldAccessor.appendValue(by, value, webDriver);
    }

    /**
     * Overwrite the value of the specified element (text field).
     * @param by : Identifier for looking up an element (text field)
     */
    public void overrideText(By by, String value) {
        webDriverInputFieldAccessor.overrideValue(by, value, webDriver);
    }

    /**
     * Add a value to the specified element (text area).
     * <p>
     * Because it is not used frequently, it does not support high-speed mode (JavaScript) mode.
     * </p>
     * @param by : Identifier for looking up an element (text area)
     */
    public void appendTextArea(By by, String value) {
        webDriver.findElement(by).sendKeys(value);
    }

    /**
     * Overwrite the value of the specified element (text area).
     * <p>
     * Because it is not used frequently, it does not support high-speed mode (JavaScript) mode.
     * </p>
     * @param by : Identifier for looking up an element (text area)
     */
    public void overrideTextArea(By by, String value) {
        WebElement element = webDriver.findElement(by);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Set the specified file to the specified element (see file).
     * @param by : Identifier for finding elements (file reference)
     */
    public void referUploadFile(By by, File file) {
        WebElement element = webDriver.findElement(by);
        element.sendKeys(file.getAbsolutePath());
    }

    /**
     * Select the specified element (select).
     * @param by : Identifier for searching for an element (text item)
     */
    public void select(By by, String value) {
        new Select(webDriver.findElement(by)).selectByVisibleText(value);
    }

    /**
     * Get the text (display value) set for the specified element.
     * @param by : Identifier for searching for elements
     */
    public String getText(By by) {
        return webDriver.findElement(by).getText();
    }

    /**
     * Get the value set in the specified input field.
     * @param by : Identifier for searching for elements
     */
    public String getInputFieldValue(By by) {
        return webDriverInputFieldAccessor.getValue(by, webDriver);
    }

    /**
     * Get X-Track.
     * @return X-Track
     */
    public String getXTrack() {
        return webDriver.findElement(getIdOfXTrack()).getText();
    }

    /**
     * Get the identification object of X-Track.
     * @return identification object of X-Track.
     */
    public By getIdOfXTrack() {
        return By.id("xTrack");
    }

    /**
     * Check whether the specified element exists.
     * @param by : Identifier for searching for elements
     * @return Returns true if the specified element exists.
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
     * Wait for the specified element (button, link, etc.) to be displayed.
     * @param by : Identifier for finding elements (buttons, links, etc.)
     */
    public void waitForDisplayed(By by) {
        webDriver.findElement(by);
    }

    /**
     * Wait until it matches the specified display condition.
     * @param expectedCondition : Display condition
     */
    public void waitForDisplayed(ExpectedCondition<?> expectedCondition) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), defaultTimeoutSecondsForImplicitlyWait
                + offsetSecondsOfTimeoutForImplicitlyWait);
        wait.until(expectedCondition);
    }

    /**
     * Stop processing for the specified time.
     * @param waitTime : Time to stop
     * @param timeUnit : Unit of time
     */
    public void suspend(long waitTime, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get cookies.
     * <p>
     * Gets a cookie corresponding to the specified cookie name.
     * </p>
     * @param cookieName : cookie name.
     * @return A cookie corresponding to the specified cookie name
     */
    public Cookie getCookie(String cookieName) {
        return webDriver.manage().getCookieNamed(cookieName);
    }

    /**
     * Delete the cookie.
     * <p>
     * Delete the cookie corresponding to the specified cookie name.
     * </p>
     * @param cookieName : cookie name.
     */
    public void deleteCookie(String cookieName) {
        webDriver.manage().deleteCookieNamed(cookieName);
    }

    /**
     * Get JavascriptExecutor.
     */
    public JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) webDriver;
    }

    /**
     * Get the title.
     */
    public String getTitle() {
        waitForDisplayed(By.tagName("title"));
        return webDriver.getTitle();
    }

    /**
     * Get the value of the address bar.
     */
    public String getCurrentUrl() {
        waitForDisplayed(By.tagName("body"));
        return webDriver.getCurrentUrl();
    }

    /**
     * Save the screen capture as evidence.
     */
    public void saveScreenCapture() {
        screenCapture.save(webDriver);
    }

    /**
     * Save the screen capture as evidence.
     * <p>
     * The value specified by subTitle is set as part of the filename to be saved.
     * </p>
     * @param subTitle : subtitle
     */
    public void saveScreenCapture(String subTitle) {
        screenCapture.save(webDriver, subTitle);
    }

    /**
     * Save HTML source as evidence.
     */
    public void savePageSource() {
        pageSource.save(webDriver);
    }

    /**
     * Save HTML source as evidence.
     * <p>
     * The value specified by subTitle is set as part of the filename to be saved.
     * </p>
     * @param subTitle subtitle
     */
    public void savePageSource(String subTitle) {
        pageSource.save(webDriver, subTitle);
    }

    /**
     * Perform "return" of browser.
     */
    public void back() {
        webDriver.navigate().back();
    }

    /**
     * Get version information of JVM of AP server.
     */
    public String getJvmVersionOfApServer() {
        String[] version = getText(By.id("jvmVersion")).split("\\.");
        return version[0] + "." + version[1];
    }
    
    /**
     * It is judged whether the version of the JVM of the AP server is 1.7 or later
     */
    public boolean isJvm7OrLater(){
    	String[] version = getJvmVersionOfApServer().split("\\.");
    	return Integer.valueOf(version[1]) >= 7;
    }
    
    /**
     * It is judged whether the version of the JVM of the AP server is 1.8 or later
     */
    public boolean isJvm8OrLater(){
    	String[] version = getJvmVersionOfApServer().split("\\.");
    	return Integer.valueOf(version[1]) >= 8;
    }

}
