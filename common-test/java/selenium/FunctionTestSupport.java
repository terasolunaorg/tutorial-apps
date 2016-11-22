/*
 * Copyright(c) 2014-2016 NTT Corporation.
 */
package todo.selenium;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/seleniumContext.xml" })
public abstract class FunctionTestSupport extends ApplicationObjectSupport {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(FunctionTestSupport.class);

    private static WebDriver webDriver;

    private static final Set<WebDriver> webDrivers = new HashSet<WebDriver>();

    @Rule
    public final TestName testName = new TestName();

    @Rule
    public final TestWatcher testWatcherForFailed = new TestWatcher() {
        @Override
        public void succeeded(Description description) {
            onSucceeded();
            saveSucceededEvidence();
        }

        @Override
        public void failed(Throwable e, Description description) {
            onFailed(e);
            saveFailedEvidence();
        }

        @Override
        public void finished(Description description) {
            onFinished();
        }
    };

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
    private ScreenCapture screenCapture;

    @Inject
    private PageSource pageSource;

    @Inject
    protected WebDriverCreator webDriverCreator;

    private boolean useDefaultWebDriver = true;

    private String simplePackageName;
    
    protected File evidenceSavingDirectory;

    private WebDriverInputFieldAccessor webDriverInputFieldAccessor = WebDriverInputFieldAccessor.JAVASCRIPT;

    @Value("${selenium.timeoutForImplicitlyWait.offsetSeconds:0}")
    private int offsetSecondsOfTimeoutForImplicitlyWait;

    protected FunctionTestSupport() {
        this.simplePackageName = this.getClass().getPackage().getName()
                .replaceAll(".*\\.", "");
    }

    @Value("${selenium.webDriverInputFieldAccessor:JAVASCRIPT}")
    public void setWebDriverInputFieldAccessor(
            String webDriverInputFieldAccessor) {
        this.webDriverInputFieldAccessor = WebDriverInputFieldAccessor
                .valueOf(webDriverInputFieldAccessor.toUpperCase());
    }

    @Before
    public final void setUpEvidence() {

        String testCaseName = testName.getMethodName().replaceAll("^test", "");

        evidenceSavingDirectory = new File(String.format("%s/%s/%s",
                evidenceBaseDirectory, simplePackageName, testCaseName));

        LOGGER.debug("evidenceSavingDirectory is "
                + evidenceSavingDirectory.getAbsolutePath());

        screenCapture.setUp(evidenceSavingDirectory);
        pageSource.setUp(evidenceSavingDirectory);

    }

    @Before
    public final void setUpDefaultWebDriver() {
        if (!useDefaultWebDriver) {
            return;
        }
        if (webDriver == null) {
            webDriver = newDefaultWebDriver();
        }
        this.webDriverOperations = new WebDriverOperations(webDriver, webDriverInputFieldAccessor, screenCapture, pageSource, offsetSecondsOfTimeoutForImplicitlyWait);

        webDriverOperations.displayPage(getPackageRootUrl());
    }

    @AfterClass
    public final static void tearDownWebDrivers() {
        quitWebDrivers();
        webDriver = null;
    }

    /**
     * Disable the default WebDriver.
     * <p>
     * When this method is called, the browser operated by the default WebDriver will not start up.
     * </p>
     */
    protected void disableDefaultWebDriver() {
        this.useDefaultWebDriver = false;
    }

    /**
     * Activate the default WebDriver.
     * <p>
     * When this method is called, a browser operated by the default WebDriver is launched.
     * </p>
     * <p>
     * This operation is the default operation.
     * </p>
     */
    protected void enableDefaultWebDriver() {
        this.useDefaultWebDriver = true;
    }

    /**
     * Quit the currently used browser (WebDriver). <br>
     */
    protected final void quitCurrentWebDriver() {
        quitWebDriver(webDriver);
        webDriver = null;
    }

    /**
     * Launch a new browser (WebDriver).
     * <p>
     * By calling this method, the browser is newly activated, and a WebDriverOperations instance for operating the activated browser is returned.
     * </p>
     * <p>
     * Use {@link # quitWebDriver (WebDriverOperations)} if you explicitly quit the WebDriver obtained using this method. <br>
     * If you do not quit explicitly, it will be quit when all the test cases of the corresponding class have finished.
     * </p>
     * @return
     */
    protected final WebDriverOperations newWebDriverOperations() {
        WebDriverOperations webDriverOperations = new WebDriverOperations(newDefaultWebDriver(), webDriverInputFieldAccessor, screenCapture, pageSource, offsetSecondsOfTimeoutForImplicitlyWait);
        webDriverOperations.displayPage(getPackageRootUrl());
        return webDriverOperations;
    }

    /**
     * Quit the specified browser (WebDirver associated with WebDriverOperations).
     * <p>
     * Call this method if you want to quit the browser (WebDirver associated with WebDriverOperations) that was launched using {@link # new WebDriverOperations ()}.
     * </p>
     * @param webDriverOperations : WebDriverOperations instance associated with the WebDriver to quit
     */
    protected final void quitWebDriver(WebDriverOperations webDriverOperations) {
        quitWebDriver(webDriverOperations.getWebDriver());
    }

    /**
     * Set the specified WebDriver as WebDriver to be used by default.
     * @param webDriver : WebDriver to be used by default
     */
    protected final void setCurrentWebDriver(WebDriver newWebDriver) {

        if (webDriver == null || webDriver != newWebDriver) {

            webDriver = newWebDriver;
            webDrivers.add(webDriver);

        }
        this.webDriverOperations = new WebDriverOperations(webDriver, webDriverInputFieldAccessor, screenCapture, pageSource, offsetSecondsOfTimeoutForImplicitlyWait);

        webDriverOperations.displayPage(getPackageRootUrl());
    }

    /**
     * A method for performing processing when the test succeeds.
     * <p>
     * Please override as necessary.
     * </p>
     */
    protected void onSucceeded() {
    }

    /**
     * A method for performing processing when the test fails.
     * <p>
     * Please override as necessary.
     * </p>
     * @param e : Exception that occurred when the test failed
     */
    protected void onFailed(Throwable e) {
    }

    /**
     * A method for performing processing when the test is finished.
     * <p>
     * Please override as necessary.
     * </p>
     */
    protected void onFinished() {
    }

    /**
     * Return the URL of the route allocated to the package.
     * <p>
     * The URL for displaying the top page for each function is returned.
     * </p>
     * @return URL of the route allocated to the package
     */
    protected String getPackageRootUrl() {
        return applicationContextUrl + "/" + simplePackageName + "/";
    }

    private WebDriver newDefaultWebDriver() {
        WebDriver webDriver = webDriverCreator.createDefaultWebDriver();
        webDrivers.add(webDriver);
        return webDriver;
    }

    private final void quitWebDriver(WebDriver webDriver) {
        webDrivers.remove(webDriver);
        webDriver.quit();
    }

    private static void quitWebDrivers() {
        for (WebDriver webDriver : webDrivers) {
            try {
                webDriver.quit();
            } catch (Throwable t) {
                LOGGER.error("failed quit.", t);
            }
        }
        webDrivers.clear();
    }

    protected void saveSucceededEvidence() {
        String subTitle = "succeeded";
        for (WebDriver webDriver : webDrivers) {
            try {
                screenCapture.save(webDriver, subTitle);
            } catch (Throwable t) {
                LOGGER.error("failed screen capture.", t);
            }
            try {
                pageSource.save(webDriver, subTitle);
            } catch (Throwable t) {
                LOGGER.error("failed screen PageSource.", t);
            }
        }
    }

    protected void saveFailedEvidence() {
        String subTitle = "failed";
        for (WebDriver webDriver : webDrivers) {
            try {
                screenCapture.saveForced(webDriver, subTitle);
            } catch (Throwable t) {
                LOGGER.error("failed screen capture.", t);
            }
            try {
                pageSource.saveForced(webDriver, subTitle);
            } catch (Throwable t) {
                LOGGER.error("failed screen PageSource.", t);
            }
        }
    }
}
