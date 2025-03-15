package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// Every login page has its own private web driver
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	    eleUtil = new ElementUtil(driver);
	}

	// 1. By Locator: page objects: OR
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password11");
	
	// 2. Public page actions - methods (features)
	@Step("Get the Login Page Title")
	public String getLoginPageTitle()
	{
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
	//	System.out.println("Login Page Title is: "+title);
		ChainTestListener.log("Login Page Title is: "+title);
		return title;
	}
	
	@Step("Get the Login Page URL")
	public String getLoginPageURL()
	{
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Login Page URL is: "+url);
		return url;
	}
	
	@Step("Verifying forgot password link is displayed...")
	public boolean isForgotPwdLinkExists()
	{
		return eleUtil.doIsElementDisplayed(forgotPwdLink);
	}
	
	@Step("login with email : {0} and pwd : {1}")
	public HomePage doLogin(String email, String pwd)
	{
		System.out.println("Login credentials are: "+email+" : "+pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.SHORT_TIME_OUT).sendKeys(email);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
	    return new HomePage(driver);
		
	}
	

	
	

}
