package pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productMap;
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails  img");
	private By productMetaData = By.xpath("(//h1/following-sibling::ul)[1]/li");
	private By productPriceData = By.xpath("(//h1/following-sibling::ul)[2]/li");
	

	public String getProductHeader()
	{
		String header = eleUtil.doElementGetText(productHeader);
		System.out.println("Product Header is: "+header);
		return header;
	}
	
	public int getProductImagesCount()
	{
		int imagesCount = eleUtil.waitForElementsPresence(productImages, AppConstants.DEFAULT_TIME_OUT).size();
		System.out.println(getProductHeader()+" : Images Count = "+imagesCount);
		return imagesCount;
	}
	
	/**
	 * Get the full product information: Header, Images Count, Meta data, Price data
	 * @return
	 */
	public Map<String, String> getProductInfo()
	{
		productMap = new HashMap<String, String>(); 		  // Insertion order is not preserved
	//	productMap = new LinkedHashMap<String, String>(); // Insertion order is preserved
	//	productMap = new TreeMap<String, String>();            // Alphabetical order is maintained
		productMap.put("header", getProductHeader());
		productMap.put("ImagesCount", getProductImagesCount()+"");
		getProductMetaData();
		getProductPriceData();
		
		return productMap;
		
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	private void getProductMetaData()
	{
		List<WebElement> metaList = eleUtil.waitForElementsPresence(productMetaData, AppConstants.DEFAULT_TIME_OUT);
		for(WebElement e: metaList)
		{
			String metaText = e.getText();
			String meta[] = metaText.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	
	private void getProductPriceData()
	{
		List<WebElement> priceList = eleUtil.waitForElementsPresence(productPriceData, AppConstants.DEFAULT_TIME_OUT);
		String productPrice = priceList.get(0).getText().trim();
		String productExTax= priceList.get(1).getText().split(":")[1].trim();
		productMap.put("price", productPrice);
		productMap.put("Ex Tax", productExTax);
	}
	

}
