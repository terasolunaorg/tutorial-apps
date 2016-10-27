/*
 * Copyright(c) 2014-2016 NTT Corporation.
 */
package todo.selenium;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

public class ScreenCapture {

    private static final Logger logger = LoggerFactory
            .getLogger(ScreenCapture.class);

    @Value("${selenium.enableCapture}")
    protected boolean enableCapture;

    private File evidenceSavingDirectory;

    private final AtomicInteger sequence = new AtomicInteger(0);

    public void setUp(File evidenceSavingDirectory) {
        sequence.set(0);
        this.evidenceSavingDirectory = evidenceSavingDirectory;
    }

    public void save(WebDriver webDriver) {
        save(webDriver, (String) null);
    }

    public void save(WebDriver webDriver, String subTitle) {
        if (!enableCapture) {
            return;
        }
        saveForced(webDriver, subTitle);
    }

    public void saveForced(WebDriver webDriver, String subTitle) {

        TakesScreenshot takesScreenshot = null;
        if (webDriver instanceof TakesScreenshot) {
            takesScreenshot = (TakesScreenshot) webDriver;
        }
        if (takesScreenshot == null) {
            logger.warn(
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
            FileUtils.copyFile(screenshotFile,
                    new File(evidenceSavingDirectory, evidenceFile));
        } catch (IOException e) {
            logger.error(e.toString());
        }

    }

}
