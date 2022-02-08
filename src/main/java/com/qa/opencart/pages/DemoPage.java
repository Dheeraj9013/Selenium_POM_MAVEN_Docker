package com.qa.opencart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;


import io.qameta.allure.Step;

public class DemoPage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	
	
	public DemoPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	
	private By storeFrontText = By.xpath("//h2[text()='Store Front']");
	private By demoPageUrl = By.xpath("//a[@href='http://demo.opencart.com/']");
	
	@Step("Get Store Front Text")
	public String getstoreFrontText() {
		return util.doGetText(storeFrontText);
	}
	
	
	@Step("Enter Demo page URL")
	public StorePage enterDemoPage() {
		util.doClick(demoPageUrl);
		util.windowPopUp();
		return new StorePage(driver);
		
	}
	

}
