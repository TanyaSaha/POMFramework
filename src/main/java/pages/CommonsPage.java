package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class CommonsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public CommonsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By logo = By.cssSelector("img.img-responsive");
	private By footer = 	By.xpath("//footer//a");
	
	public boolean isLogoDisplayed()
	{
		return eleUtil.isElementDisplayed(logo);
	}
	
    public List<String> getFootersList()
    {
    	List<WebElement> footerList = eleUtil.waitForElementsPresence(footer, AppConstants.DEFAULT_TIME_OUT);
    	System.out.println("Total number of footers :"+footerList.size());
    	List<String> footers = new ArrayList<String>();
    	
    	for(WebElement e: footerList)
    	{
    		String footerlinks = e.getText();
    		footers.add(footerlinks);
    	}
    	return footers;
    }
    
    public boolean checkFooterLink(String footerLink)
    {
    	return getFootersList().contains(footerLink);
    }
    
    
}
