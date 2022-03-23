package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GumtreeHomePage {

	WebDriver driver;

	public GumtreeHomePage(WebDriver driver) {

		this.driver = driver;

		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "a[class='button button--primary warning__button-hide']")
	public WebElement IUnderstand;

	@FindBy(id = "search-query")
	public WebElement searchQueryTextBox;

	@FindBy(id = "categoryId-wrp")
	public WebElement categoryDropDown;

	@FindBy(xpath = "//div[contains(text(),'Electronics & Computer')]")
	public WebElement electronicsCategory;

	@FindBy(id = "search-area")
	public WebElement locationField;

	@FindBy(id = "srch-radius-input")
	public WebElement radiusDropDown;

	@FindBy(id = "srch-radius-wrp-option-20")
	public WebElement acturalRadius;

	@FindBy(css = "button[type='submit']")
	public WebElement searchButton;

	@FindBy(css = "span[class='user-ad-row-new-design__title-span']")
	public List<WebElement> userAdvertisement;

	@FindBy(css = "h1[class='vip-ad-title__header']")
	public WebElement userAdvertisementHeaderDetailsPage;

	@FindBy(css = "span[class='breadcrumbs__summary']")
	public WebElement breadCrumbsSummary;

	@FindBy(xpath = "//div[contains(text(),'Similar Ads')]/parent::div//li")
	public List<WebElement> similarAdsList;

}
