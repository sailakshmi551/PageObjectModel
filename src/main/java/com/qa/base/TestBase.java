package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.utils.FileUtil;
import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;

public class TestBase 
{
	public Properties prop;
	public static WebDriver driver;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	public TestUtil util;
	
	
	public WebDriver initialize_driver()
	{
		System.setProperty("webdriver.chrome.driver","C://chromedriver.exe");
		driver = new ChromeDriver();
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		tdriver.set(driver);
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver()
	{
		return tdriver.get();
	}
	
	public Properties initialize_Properties()
	{
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("D:\\MavenWorkspace\\PageObjectModelPatternTest\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	
	public String getScreenshot()
	{
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try 
		{
			FileUtils.copyFile(src, destination);
		} 
		catch (IOException e) 
		{
			System.out.println("Capture Failed "+e.getMessage());
			
		}
		return path;
	}

	
}
