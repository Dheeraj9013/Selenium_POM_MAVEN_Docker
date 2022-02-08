package com.qa.opencart.tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listners.TestAllureListener;
import com.qa.opencart.utils.constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Listeners(TestAllureListener.class)
public class StorePageText extends BaseTest {
	@Description("My Store PAge Setup")
	@Severity(SeverityLevel.CRITICAL)
	@BeforeTest()
	public void storePageSetup() {
		accountpage = logpage.login(prop.getProperty("username"), prop.getProperty("password"));
		demopage = accountpage.openDemoLink();
		storepage = demopage.enterDemoPage();
	}

	@DataProvider
	public Object[][] getProduct() {
		return new Object[][] {

				{ "macbook" }, { "Apple" }, { "Samsung" } };
	}
	@Description("Verify Your Serach Store")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1, dataProvider = "getProduct")

	public void verifyyourStore(String productName) {
		searchpage = storepage.search(productName);
		String title = searchpage.getSearchTitle();
		softassert.assertEquals(title, constants.SEARCH);
		softassert.assertAll();

	}

}
