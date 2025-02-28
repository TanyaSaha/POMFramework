package com.qa.opencart.factory;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;

	ChromeOptions co;
	FirefoxOptions fo;
	EdgeOptions eo;
	
	private static final Logger log = LogManager.getLogger(OptionsManager.class);
	
	public OptionsManager(Properties prop)
	{
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptions()
	{
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
		//System.out.println("** Running tests in Headless Mode **");
		log.info("** Running tests in Headless Mode **");
		co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
		//System.out.println("** Running tests in Incognito Mode **");
		log.info("** Running tests in Incognito Mode **");
		co.addArguments("--incongnito");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions()
	{
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
	//	System.out.println("** Running tests in Headless Mode **");
		log.info("** Running tests in Headless Mode **");
		fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
	//	System.out.println("** Running tests in Incognito Mode **");
		log.info("** Running tests in Incognito Mode **");
		fo.addArguments("--incongnito");
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions()
	{
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
	//	System.out.println("** Running tests in Headless Mode **");
		log.info("** Running tests in Headless Mode **");
		eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
	//	System.out.println("** Running tests in Incognito Mode **");
		log.info("** Running tests in Incognito Mode **");
		eo.addArguments("--inPrivate");
		}
		return eo;
	}
	
	

}
