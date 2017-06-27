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
package org.terasoluna.securelogin.selenium.loginform.page;

import java.util.concurrent.TimeUnit;

import org.terasoluna.gfw.tutorial.selenium.WebDriverOperations;

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
