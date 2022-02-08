package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	
	private By searchTitile = By.xpath("//a[text()='Search']");
	
	
	
	

	@Step("Get Search Title")
	public String getSearchTitle() {
		
		return util.doGetText(searchTitile);
	}
	
	

}
