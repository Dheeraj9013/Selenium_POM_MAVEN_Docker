package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listners.TestAllureListener;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(TestAllureListener.class)
public class AccountDetailsChangeTest  extends BaseTest{
	
	@Description("Account Detials Change PreRequisite")
	@Severity(SeverityLevel.BLOCKER)
	@BeforeTest
	public void accountDetailsChangePagepre() {
		accountpage = logpage.login(prop.getProperty("username"), prop.getProperty("password"));
		accountpage = logpage.enterPinDetails(constants.PIN_VALUE);
		accountdetails = accountpage.navigateAccountDetailsChangePage();
		
		
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(constants.SHEET_NAME);
	}
	@Description("Update the Accounts Details PAge")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider="getRegisterData")
	public void verifyUpdateDetails(String usernames,String firstnames,String lastnames,String value) {
		System.out.println(value);
		String text = value.substring(0,1);
		Assert.assertTrue(accountdetails.updateAccountDetails(usernames,firstnames,lastnames,text));
	}
	
	

}
