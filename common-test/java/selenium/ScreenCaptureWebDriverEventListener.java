/*
 * Copyright(c) 2014-2016 NTT Corporation.
 */
package todo.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Captures screen using ScreenCapture class after navigation and click on button/link Please extend the class and override the
 * behavior as per requirement
 */
public class ScreenCaptureWebDriverEventListener extends
                                                WebDriverEventListenerAdapter {

    protected final ScreenCapture screenCapture;

    public ScreenCaptureWebDriverEventListener(ScreenCapture screenCapture) {
        this.screenCapture = screenCapture;
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        // following statement is required for ensuring that every screen is captured.
        // some screens that do not have assertions or element access, need following
        // statement such that driver accesses the screen atleast once
        webDriver.findElement(By.tagName("body"));
        screenCapture.save(webDriver);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver webDriver) {
        screenCapture.save(webDriver);
    }

}
