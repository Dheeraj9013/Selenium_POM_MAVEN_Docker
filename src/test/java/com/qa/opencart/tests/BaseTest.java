package com.qa.opencart.tests;



import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountDetailsChangePage;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.DemoPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.SearchPage;
import com.qa.opencart.pages.StorePage;


public class BaseTest {
	
	DriverFactory fac;
	Properties prop;
	WebDriver driver;
	
	SoftAssert softassert;
	
	
	LoginPage logpage;
	AccountsPage accountpage;
	DemoPage demopage;
	StorePage  storepage;
	SearchPage searchpage;
	AccountDetailsChangePage accountdetails;
	
	
	
	@BeforeTest
	public void setup() {
		
		fac = new DriverFactory();
		prop = fac.init_properties();
		driver = fac.init_driver(prop);
		logpage =  new LoginPage(driver);
		softassert = new SoftAssert();
		
		
		
	}
	
	@AfterTest
	public void teardown() {
		//driver.quit();
	}

}
