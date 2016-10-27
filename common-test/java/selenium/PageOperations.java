/*
 * Copyright(c) 2014-2016 NTT Corporation.
 */
package todo.selenium;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.BeanUtils;

public class PageOperations {

	private PageOperations() {
		// NOP
	}

	public static <P extends Page<P>> P loadNextPage(Page<?> currentPage, Class<P> nextPage, WebDriver driver) {
		if (nextPage == currentPage.getClass()) {
			return nextPage.cast(currentPage).reload();
		}
		try {
			return BeanUtils.instantiateClass(nextPage.getConstructor(WebDriver.class), driver);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		}
	}


}
