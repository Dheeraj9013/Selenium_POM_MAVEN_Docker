package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	
	
	
	private WebDriver driver;
	private ElementUtil util;
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	
	private By accountDetails = By.xpath("//a[text()='Account Details']");
	private By demoLink = By.xpath("//a[text()='Demo']");
	private By accountLink = By.xpath("//div[@id='collapse-menu']/descendant::a[contains(@href,'menu0')]");
	private By accountDetailsExpand = By.xpath("//div[@id='collapse-menu']/descendant::a[contains(@data-original-title,'Edit your account details')]");
	
	
	
	
	
	@Step("Get Account Details Text")
	public String getAccountDetailsText() {
		return util.doGetText(accountDetails);
	}
	@Step("Open Demo link")
	public DemoPage openDemoLink() {
		util.doClick(demoLink);
		return new DemoPage(driver);
	}
	
	
	@Step("Account Details Upadte Field Method")
	public AccountDetailsChangePage navigateAccountDetailsChangePage() {
		util.doClick(accountLink);
		util.doClick(accountDetailsExpand);
		return new AccountDetailsChangePage(driver);
		
	}

}
