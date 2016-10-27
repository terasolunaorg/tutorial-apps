/*
 * Copyright(c) 2014-2016 NTT Corporation.
 */
package todo.selenium;

import todo.selenium.Page;
import todo.selenium.WebElementOperations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SystemErrorPage implements Page<SystemErrorPage>{
	
	private  WebDriver driver;
	
	
	@CacheLookup
	@FindBy (xpath = "//*[@id='wrapper']/div/ul[2]/li")
	private WebElement errorMessageElement;
	
	@CacheLookup
	@FindBy (xpath = "//*[@id='wrapper']/div/ul/li")
	private WebElement resNFMsgElement;

	public SystemErrorPage(WebDriver driver2) {
		this.driver = driver2;
		reload();
	}

	@Override
	public SystemErrorPage reload() {
		
		PageFactory.initElements(driver, this);
		return this;
	}
	
	public String getErrorMessage(){
		return WebElementOperations.getElementTextValue(errorMessageElement);
	}
	
	public String getResNFErrorMessage(){
		return WebElementOperations.getElementTextValue(resNFMsgElement);
	}
	
}
