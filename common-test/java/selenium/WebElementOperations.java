/*
 * Copyright(c) 2014-2016 NTT Corporation.
 */
package todo.selenium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebElementOperations {
	private WebElementOperations() {
		// NOP
	}

	public static <P extends Page<P>> P input(P page, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
		return page;
	}

	public static <P extends Page<P>> P select(P page, List<WebElement> radios, boolean value) {
		radios.get(value ? 0 : 1).click();
		return page;
	}


	public static <P extends Page<P>> P check(P page, List<WebElement> checkboxes, String... selectValues) {
		Set<String> valueSet = new HashSet<>(Arrays.asList(selectValues));
		for (WebElement checkbox : checkboxes) {
			if (valueSet.contains(getValue(checkbox)) && !checkbox.isSelected()) {
				checkbox.click();
			} else if (checkbox.isSelected()) {
				checkbox.click();
			}
		}
		return page;
	}

	public static String getValue(WebElement item) {
		return item.getAttribute("value");
	}
	
	public static String getElementTextValue(WebElement item) {
		return item.getText();
	}
	
	public static void setValue(WebElement item,String val) {
		 item.sendKeys(val);
	}
	
	/**
     * 指定した要素(セレクト)を選択する。
     * @param by 要素(テキスト項目)を探すための識別子
     */
    public static void select(WebElement webElement, String value) {
        new Select(webElement).selectByVisibleText(value);
    }
    
    /**
     * Check whether the given element is present on the web page.
     * @param webElement
     * @return
     */
    public static Boolean isElementPresent(WebElement webElement){
    	Boolean visible = false;
    	try {
    		webElement.isDisplayed();
			visible = true;
		} catch (NoSuchElementException nse) {
			visible = false;
		}
		return visible;
    }
    
}
