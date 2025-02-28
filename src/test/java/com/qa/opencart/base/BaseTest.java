package com.qa.opencart.base;

import java.sql.Driver;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Description;
import pages.CommonsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductInfoPage;
import pages.SearchResultsPage;


//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	
	protected Properties prop;
	
	protected CommonsPage commonsPage;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	
	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName)
	{
		df= new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		commonsPage = new CommonsPage(driver);	
	}
	

	@AfterMethod
    public void attachScreenshot(ITestResult result)
    {
		if(!result.isSuccess()){
		// ChainTestListener.embed(File, mimeType);
		ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
    }
	

	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	


}
