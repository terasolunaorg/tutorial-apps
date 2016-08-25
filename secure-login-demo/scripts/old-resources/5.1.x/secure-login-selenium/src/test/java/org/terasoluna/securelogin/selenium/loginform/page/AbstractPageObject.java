package org.terasoluna.securelogin.selenium.loginform.page;

import java.util.concurrent.TimeUnit;

import org.terasoluna.securelogin.selenium.WebDriverOperations;

public abstract class AbstractPageObject {

	protected final String applicationContextUrl;

	private final int defaultIntervalSeconds = 1;

	protected WebDriverOperations webDriverOperations;

	protected String url;

	public AbstractPageObject(WebDriverOperations webDriverOperations,
			String applicationContextURL) {
		this.webDriverOperations = webDriverOperations;
		this.applicationContextUrl = applicationContextURL;
	}

	protected void waitDefaultInterval() {
		webDriverOperations.suspend(defaultIntervalSeconds, TimeUnit.SECONDS);
	}

	public AbstractPageObject open() {
		webDriverOperations.displayPage(applicationContextUrl + url);
		waitDefaultInterval();
		return this;
	}

	public AbstractPageObject openWithDescription(String description) {
		if (url.contains("?")) {
			webDriverOperations.displayPage(applicationContextUrl + url
					+ "&testdescription=" + description);
		}else{
			webDriverOperations.displayPage(applicationContextUrl + url
					+ "?testdescription=" + description);
		}
		waitDefaultInterval();
		return this;
	}
}
