package com.qa.opencart.tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listners.TestAllureListener;
import com.qa.opencart.utils.constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
@Listeners(TestAllureListener.class)
public class SearchPageText extends BaseTest {
	
	@BeforeTest
	public void searchSetUpPrerequisite() {
		accountpage = logpage.login(prop.getProperty("username"), prop.getProperty("password"));
		demopage = accountpage.openDemoLink();
		storepage = demopage.enterDemoPage();
		//searchpage = storepage.search(values)
	}
	
	@Description("Verify Serach Title")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void verifySearchTitle() {
		String search = searchpage.getSearchTitle();
		softassert.assertEquals(search, constants.SEARCH);
		softassert.assertAll();
	}

}
