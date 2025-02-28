package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.io.Files;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exceptions.FrameworkExcpetion;

import io.qameta.allure.Step;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static final Logger log = LogManager.getLogger(DriverFactory.class);

	@Step("Initialize the driver using properties: {0}")
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		//System.out.println("Browser name is: " + browserName);
		log.info("Browser name is: " + browserName);
		

		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;
		case "safari":
			driver = new SafariDriver();
			break;

		default:
		//	System.out.println("Please pass the right browser: " + browserName);
			log.error("Please pass the right browser: " +browserName);
			throw new FrameworkExcpetion("*** Invalid browser Name ***");

		}

//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(prop.getProperty("url"));
//		return driver;

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initiate the properties from .properties file
	 * 
	 * @return
	 */
	// supply env name using maven command line
		// mvn clean install -Denv="qa"
		// mvn clean install
		public Properties initProp() {
			String envName = System.getProperty("env");
			//System.out.println("running test suite on env: " + envName);
			log.info("running test suite on env: " + envName);
		
			FileInputStream ip = null;
			prop = new Properties();
			
			try {
				if (envName == null) {
					// System.out.println("no env is passed, hence running test suite on qa env..");
					log.warn("no env is passed, hence running test suite on qa env..");
				
					ip = new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
				} else {
					switch (envName.trim().toLowerCase()) {
					case "qa":
						ip = new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
						break;
					case "dev":
						ip = new FileInputStream(AppConstants.CONFIG_DEV_PROP_FILE_PATH);
						break;
					case "stage":
						ip = new FileInputStream(AppConstants.CONFIG_STAGE_PROP_FILE_PATH);
						break;
					case "uat":
						ip = new FileInputStream(AppConstants.CONFIG_UAT_PROP_FILE_PATH);
						break;
					case "prod":
						ip = new FileInputStream(AppConstants.CONFIG_PROD_FILE_PATH);
						break;

					default:
						//System.out.println("plz pass the right env name..."+ envName);
						log.error("plz pass the right env name..."+ envName);
						
						throw new FrameworkExcpetion("===INVALID ENV===");
					}
				}

				prop.load(ip);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				} catch (IOException e) {
				e.printStackTrace();
			}

			return prop;
		}
		/**
		 * takescreenshot
		 */

		public static String getScreenshot() {
			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
			String path = System.getProperty("user.dir") + "/screenshot/" + "_" + System.currentTimeMillis() + ".png";
			File destination = new File(path);

			try {
				Files.copy(srcFile, destination);
							
			} catch (IOException e) {
				e.printStackTrace();
			}

			return path;
		}

		public static File getScreenshotFile() {
			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
			return srcFile;
		}

		public static byte[] getScreenshotByte() {
			return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

		}

		public static String getScreenshotBase64() {
			return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

		}


}
