package com.supportlibraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@SuppressWarnings("deprecation")
public class ReusableLibraries extends BaseClass {

	/**
	 * This function is for smart waiting for an object
	 */
	public boolean waitForObject(WebDriver driver, int timeLimitInSeconds, WebElement element) {

		boolean isElementPresent;

		try {
			WebElement webElement;

			WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
			webElement = (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
			isElementPresent = webElement.isDisplayed();
			return isElementPresent;
		} catch (Exception e) {
			isElementPresent = false;
			System.out.println(e.getMessage());
			return isElementPresent;

		}
	}

	/**
	 * This function verifies if the object is available in the application or not
	 */
	public void verifyObjectExistense(WebDriver driver, WebElement webElement) {

		waitForObject(driver, 60, webElement);

		Assert.assertTrue(webElement != null);
	}

	/**
	 * This function compare the expected and actual values and fails the test case
	 * if the expected and actual values values are not matching
	 */
	public void verifyExpectedActualValues(String expectedValue, String actualValue, String Message) {

		Assert.assertEquals(expectedValue, actualValue, Message);

	}

	/**
	 * This function verify if the booean value is true
	 */
	public void verifyAssertTrue(boolean boolenValue, String Message) {

		Assert.assertTrue(boolenValue, Message);

	}

}
