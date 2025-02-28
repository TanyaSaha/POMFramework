package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class HomePage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	// By Locators
	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content>h2");
	private By search = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	
	// public page actions
	
	public String getHomePageTitle()
	{
		String title = eleUtil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Home Page Title is: "+title);
		return title;
	}
	
	public String getHomePageURL()
	{
		String url = eleUtil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIME_OUT);
		System.out.println("Home Page URL is: "+url);
		return url;
	}
	
	public boolean isLogoutLinkExist()
	{
		return eleUtil.doIsElementDisplayed(logoutLink);
	}
	
	public void logout()
	{
		if(isLogoutLinkExist())
				{
				eleUtil.doClick(logoutLink);
				}
	}
	
	public List<String> getHeadersList()
	{
		List<WebElement> headersList = eleUtil.waitForElementsVisible(headers, AppConstants.SHORT_TIME_OUT);
		List<String> headerValList = new ArrayList<String>();
		
		for(WebElement e: headersList)
		{
			System.out.println(e.getText());
			String options = e.getText();
			headerValList.add(options);
		}
		return headerValList;
		
	}
	
	public SearchResultsPage doSearch(String searchKey)
	{
		System.out.println("SearchKey: "+ searchKey);
		WebElement searchEle = eleUtil.waitForElementVisible(search, AppConstants.SHORT_TIME_OUT);
		searchEle.clear();
		searchEle.sendKeys(searchKey);
		eleUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}

}
