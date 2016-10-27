/*
 * Copyright(c) 2014-2016 NTT Corporation.
 */
package todo.selenium;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.AfterClass;

public abstract class FunctionTestSupportForMultiBrowser extends
                                                        FunctionTestSupport {

    private static final Map<Integer, WebDriverOperations> webDriverOperationsMap = new ConcurrentHashMap<Integer, WebDriverOperations>();

    @AfterClass
    public static void tearDownWebDriverOperationsMap() {
        webDriverOperationsMap.clear();
    }

    /**
     * デフォルトコンストラクタ。
     * <p>
     * デフォルトブラウザを使用しない(起動しない)設定にする。
     * </p>
     */
    protected FunctionTestSupportForMultiBrowser() {
        super();
        disableDefaultWebDriver();
    }

    /**
     * 指定したIDのWebDriverを操作するためのオブジェクトをセットアップする。
     * <p>
     * 指定したIDのWebDriverが起動していない場合は、新たにブラウザを起動する。
     * </p>
     * @param webDriverId WebDriverを識別するためのID
     * @return セットアップしたWebDriverを操作するためのオブジェクト
     */
    protected final WebDriverOperations setUpWebDriverOperations(int webDriverId) {
        WebDriverOperations operations = null;
        if (webDriverOperationsMap.containsKey(webDriverId)) {
            operations = webDriverOperationsMap.get(webDriverId);
        } else {
            operations = newWebDriverOperations();
            webDriverOperationsMap.put(webDriverId, operations);
        }
        operations.displayPage(getPackageRootUrl());
        return operations;
    }

    /**
     * 指定されたブラウザ(WebDriverOperationsに関連付られているWebDirver)をquitする。
     * <p>
     * {@link #setUpWebDriverOperations()}を使って起動したブラウザ(WebDriverOperationsに関連付られているWebDirver)をquitする場合は、このメソッドを呼び出すこと。
     * </p>
     * @param webDriverOperations quitするWebDriverと関連付られているWebDriverOperationsインスタンス
     */
    protected final void quitWebDriverWebDriverOperations(int webDriverId) {
        if (webDriverOperationsMap.containsKey(webDriverId)) {
            quitWebDriver(getWebDriverOperations(webDriverId));
            webDriverOperationsMap.remove(webDriverOperations);
        }
    }

    /**
     * 指定したIDのWebDriverを操作するための操作オブジェクトを取得する。
     * @param webDriverId WebDriverを識別するためのID
     * @return 指定したIDのWebDriverを操作するためのオブジェクト
     */
    protected final WebDriverOperations getWebDriverOperations(int webDriverId) {
        return webDriverOperationsMap.get(webDriverId);
    }

}
