package com.qa.testcases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.constants.Constants;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class HomePageTest
{
	public TestBase testBase;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	
	@BeforeMethod
	public void setUp()
	{
		testBase = new TestBase();
		prop = testBase.initialize_Properties();
		driver = testBase.initialize_driver();
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void verifyHomePageTitleTest()
	{
		String title = homePage.getHomePageTitle();
		System.out.println("Home Page Title is:"+title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test
	public void verifyHomePageHeaderTest()
	{
		Assert.assertTrue(homePage.verifyHomePageHeader());
		String homePageHeader = homePage.getHomePageHeaderText();
		System.out.println("Home Page Header is:"+homePageHeader);
		Assert.assertEquals(homePageHeader, Constants.HOME_PAGE_HEADER);
	}
	
	
	@Test
	public void verifyContactsLinkTest()
	{
		homePage.navigateToContactsPage();
	}
	
	@Test(enabled=false)
	public void verifyDealsLinkTest()
	{
		Assert.assertEquals(true, false);
	}
	
	@AfterMethod //This method will be executed after every test method
	public void tearDown()
	{
		driver.quit();
	}
	
}

