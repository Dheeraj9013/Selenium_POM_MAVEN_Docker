package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class StorePage {
	
	
	private WebDriver driver;
	private ElementUtil util;
	
	
	public StorePage(WebDriver driver) {
		this.driver = driver;
		util  = new ElementUtil(driver);
		
	}
	
	private By yourStoreText = By.xpath("//a[text()='Your Store']");
	private By searchTextBox = By.xpath("//input[@type='text']");
	private By searchButton = By.xpath("//div[@id='search']/descendant::button");
	
	
	
	@Step("Get Store Text")
	public String getStroeText() {
		return util.doGetText(yourStoreText);
	}
	
	
	
	@Step("Search Functionality methods")
	public SearchPage search(String values) {
		util.windowPopUp();
		util.doActionsSendKeys(searchTextBox, values);
		util.doActionsClick(searchButton);
		driver.findElement(searchTextBox).clear();
		
		return new SearchPage(driver);
	}

}
