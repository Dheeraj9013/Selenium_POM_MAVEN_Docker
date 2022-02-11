package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil extends DriverFactory {

	WebDriver driver;
	JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;

		jsUtil = new JavaScriptUtil(this.driver);

	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> elementsList = driver.findElements(locator);

		return elementsList;
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		

			System.out.println("locator is : " + locator);
			element = driver.findElement(locator);

			System.out.println(highlight);
			if (Boolean.parseBoolean(highlight)) {
				
					try {
						jsUtil.flash(element);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
//						System.out.println("print IO Exception");
						
					    Thread.currentThread().interrupt();
					}
				
			}
			System.out.println("WebElement is created successfully : " + locator);

		
		return element;
	}

	public void doSendKeys(By locator, String value) {
		waitForElementPresent(locator, 10, 1);
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		waitForElementPresent(locator, 10, 1);
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		waitForElementPresent(locator, 10, 1);
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		waitForElementPresent(locator, 10, 1);
		return getElement(locator).isDisplayed();
	}

	// **********************************Drop Down Utils
	// *********************************

	public void doSelectByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	public void doSelectByIndex(By locator, String index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(Integer.parseInt(index));
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public int doDropDownOptionsCount(By locator) {
		return doGetDropDownOptions(locator).size();
	}

	public ArrayList<String> doGetDropDownOptions(By locator) {
		ArrayList<String> ar = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> OptionsList = select.getOptions();

		for (int i = 0; i < OptionsList.size(); i++) {
			String text = OptionsList.get(i).getText();
			ar.add(text);
		}
		return ar;
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select selectday = new Select(getElement(locator));
		List<WebElement> OptionsList = selectday.getOptions();

		for (int i = 0; i < OptionsList.size(); i++) {
			String text = OptionsList.get(i).getText();
			if (text.equals(value)) {
				OptionsList.get(i).click();
				break;
			}
		}
	}

	public void doSelectDropDownValueWithoutSelect(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);

		for (int i = 0; i < optionsList.size(); i++) {
			String text = optionsList.get(i).getText();
			if (text.equals(value)) {
				optionsList.get(i).click();
				break;
			}
		}
	}

	public void selectChoiceValues(By locator, String... value) {
		// List<WebElement> choiceList =
		// driver.findElements(By.cssSelector("span.comboTreeItemTitle"));
		List<WebElement> choiceList = getElements(locator);

		if (!value[0].equalsIgnoreCase("ALL")) {

			for (int i = 0; i < choiceList.size(); i++) {
				String text = choiceList.get(i).getText();
				System.out.println(text);

				for (int k = 0; k < value.length; k++) {
					if (text.equals(value[k])) {
						choiceList.get(i).click();
						break;
					}
				}

			}
		}
		// select all the values:
		else {
			try {
				for (int all = 0; all < choiceList.size(); all++) {
					choiceList.get(all).click();
				}
			} catch (Exception e) {

			}
		}
	}

	// **********************************Actions class Utils
	// *********************************

	public void doDragAndDrop(By source, By target) {
		Actions action = new Actions(driver);
		WebElement sourceEle = getElement(source);
		WebElement targetEle = getElement(target);
		action.dragAndDrop(sourceEle, targetEle).build().perform();
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionsClick(By locator) {
		waitForElementPresent(locator, 10, 1);
		Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
	}

	// ***************************** Wait Utils
	// *******************************************

	public List<WebElement> visibilityofAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public WebElement waitForElementPresent(By locator, int timeout, int polling) {
//		WebDriverWait wait = new WebDriverWait(driver, timeout);
//		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//		return element;

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling)).ignoring(Throwable.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	public boolean waitForUrl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));
	}

	public Alert waitForAlertToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}

	// clickWhenReady:
	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public String waitForTitleToBePresent(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	private String mainHandle;

	public void windowPopUp() {

		try {

			Set<String> handles = driver.getWindowHandles();

			Iterator<String> itr = handles.iterator();

			mainHandle = itr.next();

			String childHandle = itr.next();

			driver.switchTo().window(childHandle);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method is used to switch back the window to parent window.
	 */
	public void windowSwitchBackToParent() {
		try {
			driver.close();
			driver.switchTo().window(mainHandle);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
