package com.qa.opencart.tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listners.TestAllureListener;
import com.qa.opencart.utils.constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Listeners(TestAllureListener.class)
public class DemoPageTest extends BaseTest {
	
	@Description("Demo page SetUp")
	@Severity(SeverityLevel.CRITICAL)
	@BeforeClass
	public void demopageSetup(){
		accountpage = logpage.login(prop.getProperty("username"), prop.getProperty("password"));
		demopage = accountpage.openDemoLink();
	}
	
	@Description("Verify Store Front Text")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	
	public void verifyStoreFrontText() {
		String storeFrontText = demopage.getstoreFrontText();
		softassert.assertEquals(storeFrontText, constants.STORE_FRONT_TEXT);
		softassert.assertAll();
	}
	@Description("Verify My Store Text")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void verifyMyStoreText() {
		
		storepage = demopage.enterDemoPage();
		String storetext = storepage.getStroeText();
		softassert.assertEquals(storetext, constants.YOUR_STORE);
		softassert.assertAll();
	}

}
