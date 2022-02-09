package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionManager optionmanager;
	public static String highlight;
	// Thread is mainly Due to Parrallel Execution in our Test . Separate local Copy
	// of Driver will be Created.
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the WebDriver
	 * 
	 * @param browserName
	 * @return this will return driver
	 */
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");
		String browserVersion = prop.getProperty("browserversion");
		optionmanager = new OptionManager(prop);
		highlight = prop.getProperty("highlight");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver("chrome",browserVersion);
			} else {
				// driver = new ChromeDriver(optionmanager.getChromeOptions());
				tldriver.set(new ChromeDriver(optionmanager.getChromeOptions()));

			}

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver("firefox",browserVersion);
			} else {
				// driver = new FirefoxDriver(optionmanager.getFirefoxOptions());
				tldriver.set(new FirefoxDriver(optionmanager.getFirefoxOptions()));
			}

		} else if (browserName.equalsIgnoreCase("safari")) {

			// driver = new SafariDriver();
			tldriver.set(new SafariDriver());
		} else {
			System.out.println("please pass the right browser:" + browserName);
		}

//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
////		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
//		driver.get(prop.getProperty("url"));
		// return driver;
		/*
		 * Instead of Driver we will use getDriver method
		 */
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	public void init_remoteWebDriver(String browser,String browserversion) {
		if(browser.equalsIgnoreCase("chrome")) {
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			dc.setCapability("browserName", "chrome");
			dc.setCapability("browserversion", browserversion);
			dc.setCapability("enableVNC", true);
			
			
			
			dc.setCapability(ChromeOptions.CAPABILITY, optionmanager.getChromeOptions());
			try {
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),dc));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setCapability("browserName", "firefox");
			dc.setCapability("browserversion", browserversion);
			dc.setCapability("enableVNC", true);
			dc.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionmanager.getFirefoxOptions());
			try {
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),dc));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public Properties init_properties() {
		prop = new Properties();
		FileInputStream fis = null;

		String envName = System.getProperty("env");
		if (envName == null) {
			try {
				fis = new FileInputStream("./src/test/resources/config/config.properties");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			switch (envName.toLowerCase()) {
			case "qa":
				try {
					fis = new FileInputStream("./src/test/resources/config/config.qa.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			default:
				System.out.println("please provide proper:" + envName);
				break;
			}
		}

		try {
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File filepath = new File(path);
		try {
			FileUtils.copyFile(src, filepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;

	}

}
