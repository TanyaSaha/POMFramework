package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100: design login page for open cart")
@Story("US 101: design the various features of open cart login page")
@Feature("Feature 50: Login Page Feature")
@Owner("Vijay Ravindra Kamisetty")
public class LoginPageTest extends BaseTest {
	
	@Description("checking login page title....")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest()   
	{
		ChainTestListener.log("Verify Login Page Title");
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}	
	
	@Description("checking forgot password link....")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageURLTest()
	{
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
	}
	
	@Description("checking login page title....")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkExistTest()
	{
	Assert.assertTrue(loginPage.isForgotPwdLinkExists(), AppError.ELEMENT_NOT_FOUND_ERROR);
		
	}
	
	@Description("Verifying  user is able to login with correct credentials....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=Integer.MAX_VALUE)
	public void loginTest()
	{
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);
	}
	
	@Description("Verifying  the logo on the page ....")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void logoTest()
	{
		Assert.assertTrue(commonsPage.isLogoDisplayed(), AppError.LOGO_NOT_FOUND_ERROR);
	}
	

	@DataProvider
	public Object[][] getFooterData()
	{
		return new Object[][] {
				{"About Us"},
				{"Contact Us"},
				{"Specials"},
				{"Order History"}
	};
	}
	
	@Description("Verifying the footers of the page ....")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider="getFooterData")
	public void footerTest(String footerLinks)
	{
	  Assert.assertTrue(commonsPage.checkFooterLink(footerLinks));
	}
	
	
	
	
	
	
}



