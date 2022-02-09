package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listners.TestAllureListener;
import com.qa.opencart.utils.constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Listeners(TestAllureListener.class)
public class AccountsPageTest extends BaseTest {
	@Description("Account page Prerequisite")
	@Severity(SeverityLevel.CRITICAL)
	@BeforeClass
	public void accPageSetUp() {
		accountpage = logpage.login(prop.getProperty("username"), prop.getProperty("password"));
		accountpage = logpage.enterPinDetails(constants.PIN_VALUE);
	}
	
	
	@Description("Verify Account Details Text")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void verifyAccountDetailsText() {
		String accountdetails = accountpage.getAccountDetailsText();
		softassert.assertEquals(accountdetails, constants.ACCOUNT_DETAILS);
		softassert.assertAll();
	}
	
	@Description("Verify Demo Page Front text")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=3,enabled=false)
	
	public void verifydemoPageFrontText() {
		demopage = accountpage.openDemoLink();
		String storeText = demopage.getstoreFrontText();
		softassert.assertEquals(storeText, constants.STORE_FRONT_TEXT);
		softassert.assertAll();
	}
	
	
	@Description("Verify Account Details Page")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	
	public void verifyAccountDetailsPage() {
		accountdetails = accountpage.navigateAccountDetailsChangePage();
		String detailsText = accountdetails.getEditMyInformationText();
		Assert.assertEquals(detailsText, constants.INFORMATION);
	}

}
