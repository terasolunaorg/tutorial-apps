/*
 * Copyright(c) 2024 NTT DATA Group Corporation. Copyright(c) 2013 NTT Corporation.
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
package org.terasoluna.gfw.tutorial.selenium;

import java.io.File;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import jakarta.inject.Inject;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.session.config.SeleniumContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SeleniumContextConfig.class })
public abstract class FunctionTestSupport extends ApplicationObjectSupport {

    private static final Logger logger = LoggerFactory.getLogger(
            FunctionTestSupport.class);

    protected static WebDriver driver;

    private static final Set<WebDriver> webDrivers = new HashSet<WebDriver>();

    @Value("${selenium.serverUrl}")
    protected String serverUrl;

    @Value("${selenium.contextName}")
    protected String contextName;

    @Value("${selenium.applicationContextUrl}")
    protected String applicationContextUrl;

    @Value("${selenium.evidenceBaseDirectory}")
    protected String evidenceBaseDirectory;

    protected WebDriverOperations webDriverOperations;

    @Inject
    protected ScreenCapture screenCapture;

    @Inject
    protected PageSource pageSource;

    @Inject
    private WebDriverManagerConfigurer webDriverManagerConfigurer;

    @Rule
    public TestName testName = new TestName();

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            onSucceeded();
            succeededEvidence();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            onFailed(e);
            failedEvidence();
        }

        @Override
        protected void finished(Description description) {
            onFinished();
        }
    };

    private boolean useSetupDefaultWebDriver = true;

    private String simplePackageName;

    protected WebDriverInputFieldAccessor inputFieldAccessor = WebDriverInputFieldAccessor.JAVASCRIPT;

    protected long defaultTimeoutSecForImplicitlyWait = 5;

    protected FunctionTestSupport() {
        this.simplePackageName = this.getClass().getPackage().getName()
                .replaceAll(".*\\.", "");
    }

    @Value("${selenium.webDriverInputFieldAccessor:JAVASCRIPT}")
    public void setWebDriverInputFieldAccessor(
            String webDriverInputFieldAccessor) {
        this.inputFieldAccessor = WebDriverInputFieldAccessor.valueOf(
                webDriverInputFieldAccessor.toUpperCase());
    }

    @AfterClass
    public final static void tearDownWebDrivers() {
        quitWebDrivers();
        driver = null;
    }

    @Before
    public final void setUpEvidence() {

        String testCaseName = testName.getMethodName().replaceAll("^test", "");

        File evidenceSavingDirectory = new File(String.format("%s/%s/%s",
                evidenceBaseDirectory, simplePackageName, testCaseName));

        logger.debug("evidenceSavingDirectory is " + evidenceSavingDirectory
                .getAbsolutePath());

        screenCapture.setUp(evidenceSavingDirectory);
        pageSource.setUp(evidenceSavingDirectory);
    }

    @Before
    public final void setUpDefaultWebDriver() {
        if (!useSetupDefaultWebDriver) {
            return;
        }
        bootDefaultWebDriver();
    }

    @Before
    public final void setUpDBLog() {
    }

    protected void bindWebDriver(WebDriver webDriver) {
        webDrivers.add(webDriver);
    }

    protected void unbindWebDriver(WebDriver webDriver) {
        webDrivers.remove(webDriver);
    }

    protected void bootDefaultWebDriver() {
        if (driver == null) {
            driver = newWebDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                defaultTimeoutSecForImplicitlyWait));
        driver.get(getPackageRootUrl());

        this.webDriverOperations = new WebDriverOperations(driver, inputFieldAccessor, screenCapture);
        this.webDriverOperations.setDefaultTimeoutForImplicitlyWait(
                defaultTimeoutSecForImplicitlyWait);
    }

    private WebDriver newWebDriver() {
        WebDriver driver = null;
        for (String activeProfile : getApplicationContext().getEnvironment()
                .getActiveProfiles()) {
            if ("chrome".equals(activeProfile)) {
                driver = new ChromeDriver();
                break;
            } else if ("firefox".equals(activeProfile)) {
                break;
            } else if ("ie".equals(activeProfile)) {
                driver = new InternetExplorerDriver();
                break;
            }
        }

        if (driver == null) {
            webDriverManagerConfigurer.setUp();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.startup.homepage_override.mstone",
                    "ignore");
            profile.setPreference("network.proxy.type", 0);
            profile.setPreference("devtools.jsonview.enabled", false);
            FirefoxOptions options = new FirefoxOptions().setProfile(profile);
            driver = new FirefoxDriver(options);
        }

        webDrivers.add(driver);
        return driver;
    }

    protected void quitDefaultWebDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }

    protected WebDriver getDefaultWebDriver() {
        return driver;
    }

    protected String getPackageRootUrl() {
        return applicationContextUrl + "/" + simplePackageName + "/";
    }

    protected void disableSetupDefaultWebDriver() {
        this.useSetupDefaultWebDriver = false;
    }

    protected void enableSetupDefaultWebDriver() {
        this.useSetupDefaultWebDriver = true;
    }

    private static void quitWebDrivers() {
        for (WebDriver webDriver : webDrivers) {
            try {
                webDriver.quit();
            } catch (Throwable t) {
                logger.error("failed quit.", t);
            }
        }
        webDrivers.clear();
    }

    private void succeededEvidence() {
        String subTitle = "succeeded";
        for (WebDriver webDriver : webDrivers) {
            try {
                screenCapture.save(webDriver, subTitle);
            } catch (Throwable t) {
                logger.error("failed screen capture.", t);
            }
            try {
                pageSource.save(webDriver, subTitle);
            } catch (Throwable t) {
                logger.error("failed screen PageSource.", t);
            }
        }
    }

    private void failedEvidence() {
        String subTitle = "failed";
        for (WebDriver webDriver : webDrivers) {
            try {
                screenCapture.saveForced(webDriver, subTitle);
            } catch (Throwable t) {
                logger.error("failed screen capture.", t);
            }
            try {
                pageSource.saveForced(webDriver, subTitle);
            } catch (Throwable t) {
                logger.error("failed screen PageSource.", t);
            }
        }
    }

    protected void onSucceeded() {
    }

    protected void onFailed(Throwable e) {
    }

    protected void onFinished() {
    }

}
