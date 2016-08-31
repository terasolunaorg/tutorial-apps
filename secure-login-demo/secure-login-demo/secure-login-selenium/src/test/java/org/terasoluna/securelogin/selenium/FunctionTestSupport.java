package org.terasoluna.securelogin.selenium;

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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/seleniumContext.xml" })
public class FunctionTestSupport extends ApplicationObjectSupport {

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
    protected RestOperations restOperations;

    @Inject
    protected DBLogAssertOperations dbLogAssertOperations;

    @Inject
    private ScreenCapture screenCapture;

    @Inject
    private PageSource pageSource;

    @Inject
    private DBLog dbLog;

    @Inject
    protected DBLogCleaner dbLogCleaner;

    @Inject
    protected WebDriverCreator webDriverCreator;

    private boolean useDefaultWebDriver = true;

    private String simplePackageName;

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
    public final void setUpDBLog() {
        dbLogCleaner.cleanupAll();
    }

    @Before
    public final void setUpEvidence() {

        String testCaseName = testName.getMethodName().replaceAll("^test", "");

        File evidenceSavingDirectory = new File(String.format("%s/%s/%s",
                evidenceBaseDirectory, simplePackageName, testCaseName));

        LOGGER.debug("evidenceSavingDirectory is "
                + evidenceSavingDirectory.getAbsolutePath());

        screenCapture.setUp(evidenceSavingDirectory);
        pageSource.setUp(evidenceSavingDirectory);
        dbLog.setUp(evidenceSavingDirectory);

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
     * デフォルトのWebDriverを無効化する。
     * <p>
     * このメソッドを呼び出した場合、デフォルトのWebDriverによって操作されるブラウザは立ち上がらない。
     * </p>
     */
    protected void disableDefaultWebDriver() {
        this.useDefaultWebDriver = false;
    }

    /**
     * デフォルトのWebDriverを有効化する。
     * <p>
     * このメソッドを呼び出した場合、デフォルトのWebDriverによって操作されるブラウザが立ち上がる。
     * </p>
     * <p>
     * この動作が、デフォルトの動作となる。
     * </p>
     */
    protected void enableDefaultWebDriver() {
        this.useDefaultWebDriver = true;
    }

    /**
     * 現在使用されているブラウザ(WebDirver)をquitする。 <br>
     */
    protected final void quitCurrentWebDriver() {
        quitWebDriver(webDriver);
        webDriver = null;
    }

    /**
     * ブラウザ(WebDriver)を新たに起動する。
     * <p>
     * このメソッドを呼び出すことで、ブラウザが新たに起動し、起動したブラウザを操作するためのWebDriverOperationsインスタンスが返却される。
     * </p>
     * <p>
     * このメソッドを使って取得したWebDriverを明示的にquitする場合は、 {@link #quitWebDriver(WebDriverOperations)}を使用すること。 <br>
     * 明示的にquitしない場合は、該当クラスのテストケースが全て終了した際に、quitされる。
     * </p>
     * @return
     */
    protected final WebDriverOperations newWebDriverOperations() {
        WebDriverOperations webDriverOperations = new WebDriverOperations(newDefaultWebDriver(), webDriverInputFieldAccessor, screenCapture, pageSource, offsetSecondsOfTimeoutForImplicitlyWait);
        webDriverOperations.displayPage(getPackageRootUrl());
        return webDriverOperations;
    }

    /**
     * 指定されたブラウザ(WebDriverOperationsに関連付られているWebDirver)をquitする。
     * <p>
     * {@link #newWebDriverOperations()}を使って起動したブラウザ(WebDriverOperationsに関連付られているWebDirver)をquitする場合は、このメソッドを呼び出すこと。
     * </p>
     * @param webDriverOperations quitするWebDriverと関連付られているWebDriverOperationsインスタンス
     */
    protected final void quitWebDriver(WebDriverOperations webDriverOperations) {
        quitWebDriver(webDriverOperations.getWebDriver());
    }

    /**
     * 指定したWebDriverをデフォルトで使用するWebDriverに設定する。
     * @param webDriver デフォルトで使用するWebDriver
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
     * テストが成功した時の処理を行うためのメソッド。
     * <p>
     * 必要に応じてオーバーライドしてください。
     * </p>
     */
    protected void onSucceeded() {
    }

    /**
     * テストが失敗した時の処理を行うためのメソッド。
     * <p>
     * 必要に応じてオーバーライドしてください。
     * </p>
     * @param e テストが失敗した時に発生した例外
     */
    protected void onFailed(Throwable e) {
    }

    /**
     * テストが終了した時の処理を行うためのメソッド。
     * <p>
     * 必要に応じてオーバーライドしてください。
     * </p>
     */
    protected void onFinished() {
    }

    /**
     * Web上のリソースとして存在するかチェックを行う。
     * <p>
     * REST API(HEADメソッド)を呼び出し、サーバ側に指定したリソース(ファイル)が存在するかチェックを行っている。
     * </p>
     * @param templateOfUri URIのテンプレート(パス変数指定可)
     * @param uriVariables パス変数にバインドする値
     * @return 指定したファイルがサーバ上に存在する場合は<code>true</code>を返却する。404(Not Found)がサーバから返却された場合は<code>false</code>
     *         を返却するが、それ以外のHTTPステータスの場合は例外を再スローする。
     */
    protected boolean existsWebResource(String templateOfUri,
            Object... uriVariables) {
        boolean exists = false;
        try {
            restOperations.headForHeaders(templateOfUri, uriVariables);
            exists = true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw e;
            }
        }
        return exists;
    }

    /**
     * パッケージに割り振られているルートのURLを返却する。
     * <p>
     * 機能毎のトップページを表示するためのURLが返却される。
     * </p>
     * @return パッケージに割り振られているルートのURL
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

    private void saveSucceededEvidence() {
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
        try {
            dbLog.save(subTitle);
        } catch (Throwable t) {
            LOGGER.error("failed dbLog capture.", t);
        }
    }

    private void saveFailedEvidence() {
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
        try {
            dbLog.saveForced(subTitle);
        } catch (Throwable t) {
            LOGGER.error("failed dbLog capture.", t);
        }
    }
}
