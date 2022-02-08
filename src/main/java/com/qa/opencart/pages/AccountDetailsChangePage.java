package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.constants;

import io.qameta.allure.Step;

public class AccountDetailsChangePage {

	private WebDriver driver;
	private ElementUtil util;

	public AccountDetailsChangePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	private By editMyInformation = By.xpath("//*[text()='Edit My Information']");
	private By username = By.id("input-username");
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By submitButton = By.xpath("//button[@type='submit']");
	private By countrydropdown = By.xpath("//select[@id='input-country']");
	private By successmessage = By.xpath("//div[@class='alert alert-success']");
	private By accountLink = By.xpath("//div[@id='collapse-menu']/descendant::a[contains(@href,'menu0')]");
	private By accountDetailsExpand = By.xpath(
			"//div[@id='collapse-menu']/descendant::a[contains(@data-original-title,'Edit your account details')]");

	@Step("Get Edit My Information TExt in Account page")
	public String getEditMyInformationText() {
		return util.doGetText(editMyInformation);
	}

	@Step("Update Account Details Method")
	public Boolean updateAccountDetails(String usernames, String firstnames, String lastnames, String value) {
		driver.findElement(username).clear();
		util.doSendKeys(username, usernames);
		driver.findElement(firstname).clear();
		util.doSendKeys(firstname, firstnames);
		driver.findElement(lastname).clear();
		util.doSendKeys(lastname, lastnames);
		util.doSelectByIndex(countrydropdown, value);
		util.doClick(submitButton);

		String successmess = util.doGetText(successmessage);

		if (successmess.contains(constants.SUCESS_MESSAGE)) {
			util.doClick(accountLink);
			util.doClick(accountDetailsExpand);
			return true;
		} else {
			return false;
		}
	}

}
