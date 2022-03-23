package com.businesscomponents;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pages.GumtreeHomePage;
import com.supportlibraries.ReusableLibraries;

public class GumtreeCodeChallenge extends ReusableLibraries {

	WebDriver driver;

	Properties prop;

	GumtreeHomePage homePage;

	@BeforeMethod
	public void setUp() throws IOException {

		prop = loadConfigFile();

		driver = initializeWebDriver();

		driver.manage().window().maximize();

		homePage = new GumtreeHomePage(driver);

	}

	public static boolean isNumeric(String str) {

		return str.matches("-?\\d+(.\\d+)?");

	}

	/**
	 * This function is to search for Sennheiser Headphones in gumtree homepage in
	 * sydney location with in 20kms radius and validate the ad id as well as
	 * similar ads
	 */
	@Test
	@Parameters({ "searchCriteria", "location" })
	public void verifyAdsData(String searchCriteria, String location) throws InterruptedException {

		driver.get(prop.getProperty("applicationUrl"));

		homePage.IUnderstand.click();

		homePage.searchQueryTextBox.sendKeys(searchCriteria);

		homePage.categoryDropDown.click();

		homePage.electronicsCategory.click();

		homePage.locationField.sendKeys(location);

		homePage.radiusDropDown.click();

		homePage.acturalRadius.click();

		homePage.searchButton.click();

		verifyObjectExistense(driver, homePage.userAdvertisement.get(0));

		String expectedText = homePage.userAdvertisement.get(0).getText();

		homePage.userAdvertisement.get(0).click();

		verifyObjectExistense(driver, homePage.userAdvertisementHeaderDetailsPage);

		verifyExpectedActualValues(expectedText, homePage.userAdvertisementHeaderDetailsPage.getText(),
				"Verify if user is in ad details page");

		verifyAssertTrue(isNumeric(homePage.breadCrumbsSummary.getText().split(" ")[2]),
				"Verify if ad id is numeric value");

		verifyObjectExistense(driver, homePage.similarAdsList.get(0));

		verifyAssertTrue(homePage.similarAdsList.size() >= 1,
				"Verify if atlesat one similar ad is available in details page");

	}

	/**
	 * Function to close the browser after the test execution
	 */
	@AfterMethod
	public void tearDown() {

		driver.close();

		driver.quit();
	}

}
