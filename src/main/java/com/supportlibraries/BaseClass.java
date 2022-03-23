package com.supportlibraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	static Properties prop = new Properties();
	static FileInputStream fis;
	public static WebDriver driver;

	/**
	 * This function is for loading the config.properties file and load all the key
	 * value pairs data
	 */
	public Properties loadConfigFile() throws IOException {

		fis = new FileInputStream("Config.properties");

		prop.load(fis);

		return prop;

	}

	/**
	 * This function is for initializing webdriver object
	 */
	public WebDriver initializeWebDriver() throws MalformedURLException {

		System.setProperty("webdriver.chrome.driver", "ChromeDriver\\chromedriver.exe");

		driver = new ChromeDriver();

		return driver;

	}
}
