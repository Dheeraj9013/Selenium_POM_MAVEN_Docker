package com.qa.opencart.tests;



import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
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
	@Parameters({"browser" ,"browserversion"})
	public void setup(String browser , String browserVersion) throws FileNotFoundException {
		
		fac = new DriverFactory();
		prop = fac.init_properties();
		
		if(browser!=null) {
			prop.setProperty("browser", browser);
			prop.setProperty("browserversion",browserVersion);
		}
		
		
		driver = fac.init_driver(prop);
		logpage =  new LoginPage(driver);
		softassert = new SoftAssert();
		
		
		
	}
	
	@AfterTest
	public void teardown() {
		
		driver.quit();
	}

}
