package com.example.security;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
public class FunctionTestSupport extends ApplicationObjectSupport {

    @Inject
    MessageSource messageSource;

    @Value("${selenium.evidenceBaseDirectory}")
    String evidenceBaseDirectory;

    @Rule
    public final TestName testName = new TestName();

    private static final Logger LOGGER = LoggerFactory
            .getLogger(FunctionTestSupport.class);

    private final AtomicInteger sequence = new AtomicInteger(0);

    private String simplePackageName;

    private File evidenceSavingDirectory;

    protected FunctionTestSupport() {
        this.simplePackageName = this.getClass().getPackage().getName()
                .replaceAll(".*\\.", "");
    }

    @Before
    public final void setUpEvidence() {

        String testCaseName = testName.getMethodName().replaceAll("^test", "");

        this.evidenceSavingDirectory = new File(String.format("%s/%s/%s",
                evidenceBaseDirectory, simplePackageName, testCaseName));

        LOGGER.debug("evidenceSavingDirectory is "
                + evidenceSavingDirectory.getAbsolutePath());

    }

    /**
     * Starts a WebDriver using specified Locale information<br>
     * <p>
     * Only FireFox and Chrome are supported<br>
     * If "en" is specified in arguments, English locale is used<br>
     * If "" is specified, then WebDriver is started without any specific locale
     * </p>
     * 
     * @param localeStr
     * @return WebDriver web driver
     */
    protected WebDriver createLocaleSpecifiedDriver(String localeStr) {
        WebDriver driver = null;
        for (String activeProfile : getApplicationContext().getEnvironment()
                .getActiveProfiles()) {
            if ("chrome".equals(activeProfile)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=" + localeStr);
                driver = new ChromeDriver(options);
                break;
            } else if ("firefox".equals(activeProfile)) {
                break;
            } else if ("ie".equals(activeProfile)) {
                logger.warn("Cannot use Internet explorer if specifying locale. Startup the browser without any specific locale.");
                driver = new InternetExplorerDriver();
                break;
            }
        }

        if (driver == null) {
            // firefox is default browser
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("intl.accept_languages", localeStr);
            driver = new FirefoxDriver(profile);
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        return driver;
    }

    /**
     * Starts a WebDriver using default Locale<br>
     * <p>
     * Only FireFox and Chrome are supported<br>
     * </p>
     * 
     * @return WebDriver web driver
     */
    protected WebDriver createDefaultLocaleDriver() {
        String localeStr = Locale.getDefault().getLanguage();
        return createLocaleSpecifiedDriver(localeStr);
    }

    /**
     * Get localized message from message source.
     * 
     * @param code
     *            message code
     * @return localized message
     */
    protected String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    public void saveScreenCapture(WebDriver webDriver, String subTitle) {

        TakesScreenshot takesScreenshot = null;
        if (webDriver instanceof TakesScreenshot) {
            takesScreenshot = (TakesScreenshot) webDriver;
        }
        if (takesScreenshot == null) {
            LOGGER.warn(
                    "WebDriver is not supported screenshot. WebDeiver is {}.",
                    webDriver);
            return;
        }

        if (StringUtils.isEmpty(subTitle)) {
            subTitle = "";
        } else {
            subTitle = "-" + subTitle;
        }

        File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        int sequenceNo = sequence.incrementAndGet();
        String evidenceFile = String.format("screen_capture_%03d%s.png",
                sequenceNo, subTitle);

        try {
            FileUtils.copyFile(screenshotFile, new File(
                    evidenceSavingDirectory, evidenceFile));
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }

    }

}
