package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage  {
	//1- declare private driver
	private WebDriver driver;
	private ElementUtil util;
	
	//2- create a page constuctor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util  = new ElementUtil(driver);
		
		
	}
	
	//3- Private By locators
	
	private By emailIdLogin = By.id("input-email");
	private By passwordLogin = By.id("input-password");
	private By loginButton = By.xpath("//button[contains(@class,'btn-lg hidden-xs')]");
	private By forgotPassword = By.xpath("//a[contains(text(),'Forgot your password?')]");
	private By pinTextBox = By.id("input-pin");
	private By continueButton = By.xpath("//button[text()='Continue']");
	
	
	
	@Step("get Login page Title")
	public String getLoginPageTitle() {
		
		return driver.getTitle();
		
	}
	@Step("Forgot password link is available")
	public boolean isforgotpassword() {
		return util.doIsDisplayed(forgotPassword);
	}
	
	@Step("Login into the page via username: {0} and password {1}")
	public AccountsPage login(String username , String password) {
		
		util.doSendKeys(emailIdLogin, username);
		util.doSendKeys(passwordLogin, password);
		util.doClick(loginButton);
		return new AccountsPage(driver);
		
	}
	
	
	@Step("Enter Pin Details in Account page")
	public AccountsPage enterPinDetails(String pin) {
		util.doSendKeys(pinTextBox, pin);
		util.doClick(continueButton);
		return new AccountsPage(driver);
	}
	
	

}
