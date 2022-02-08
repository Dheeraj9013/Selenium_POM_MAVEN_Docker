package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listners.TestAllureListener;
import com.qa.opencart.utils.constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

	@Description("login page Title Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {

		String pagetitle = logpage.getLoginPageTitle();
		Assert.assertEquals(pagetitle, constants.LOGIN_PAGE_TITLE);

	}

	@Description("forgot password link")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void forgotPasswordLinkTest() {

		boolean linkavailable = logpage.isforgotpassword();

		Assert.assertTrue(linkavailable);
	}

	@Description("login Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void loginTest() {

		logpage.login(prop.getProperty("username"), prop.getProperty("password"));

	

	}

	@Description("Verify Pint Enter Details")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void verifyPinEnter() {

		accountpage = logpage.enterPinDetails(constants.PIN_VALUE);
		String accountDetails = accountpage.getAccountDetailsText();
		softassert.assertEquals(accountDetails, constants.ACCOUNT_DETAILS);
		softassert.assertAll();
	}

}
