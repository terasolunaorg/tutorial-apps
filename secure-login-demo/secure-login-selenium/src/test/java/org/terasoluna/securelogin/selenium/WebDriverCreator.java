/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
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
package org.terasoluna.securelogin.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.context.support.ApplicationObjectSupport;

public class WebDriverCreator extends ApplicationObjectSupport {
    /**
     * デフォルトのWebDriver(Beanファイルに定義されているWebDriver)を作成する。
     * @return デフォルトのWebDriver
     */
    public WebDriver createDefaultWebDriver() {
        WebDriver webDriver = getApplicationContext().getBean(WebDriver.class);
        return webDriver;
    }

    /**
     * 任意のロケールを有効にした、WebDriverを作成する。
     * <p>
     * FireFox,Chromeのみをサポート<br>
     * 引数 に"en" を指定すると、英語ロケールで起動する。<br>
     * 引数に "" を指定した場合、ロケールなしで起動する。
     * </p>
     * @param localeStr
     * @return WebDriver 動作対象のブラウザ
     */
    public WebDriver createLocaleSpecifiedDriver(String localeStr) {

        for (String activeProfile : getApplicationContext().getEnvironment()
                .getActiveProfiles()) {
            if ("chrome".equals(activeProfile)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=" + localeStr);
                return new ChromeDriver(options);
            } else if ("firefox".equals(activeProfile)) {
                break;
            } else if ("ie".equals(activeProfile)) {
                throw new UnsupportedOperationException("InternetExplorerを使用してロケール指定のブラウザ起動はできません。");
            } else if ("phantomJs".equals(activeProfile)) {
                throw new UnsupportedOperationException("PhantomJSを使用してロケール指定のブラウザ起動はできません。");
            }
        }

        // デフォルトのブラウザはFirefoxとする
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", localeStr);
        return new FirefoxDriver(profile);
    }

    /**
     * ダウンロード機能を有効にしたWebDirverを起動する。
     * <p>
     * FireFoxのみをサポート<br>
     * </p>
     * @param downloadTempDirectory ダウンロード用の一時保存ディレクトリ
     * @return ダウンロード機能を有効にしたWebDirverインスタンス
     */
    public WebDriver createDownloadableWebDriver(String downloadTempDirectory) {
        for (String activeProfile : getApplicationContext().getEnvironment()
                .getActiveProfiles()) {
            if ("chrome".equals(activeProfile) || "ie".equals(activeProfile)
                    || "phantomJs".equals(activeProfile)) {
                throw new UnsupportedOperationException("FireFox以外のブラウザでダウンロード機能を使用するテストを実行することはできません。");
            }
        }
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", downloadTempDirectory);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.lastDir", downloadTempDirectory);
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWhenStarting",
                false);
        profile.setPreference(
                "services.sync.prefs.sync.browser.download.manager.showWhenStarting",
                false);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/pdf, text/csv, application/vnd.ms-excel, text/plain");

        WebDriver webDriver = new FirefoxDriver(profile);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return webDriver;
    }
}
