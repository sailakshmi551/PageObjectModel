package com.qa.testcases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.constants.Constants;
import com.qa.pages.LoginPage;

public class LoginPageTest 
{
	public TestBase testBase;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	
	// test classes : --> test methods -- test cases
	
	// test case 1:
		// pre condition: open browser, url,
		// test steps -- test case : loginTest -- un/pwd/loginbtn
		// validations -- ac vs exp
		// post step: close the browser
	
	// test case 2:
		// pre condition: open browser, url,
		// test steps -- test case : check signup link on the page
		// validations -- ac vs exp
		// post step: close the browser
	
	//This method will be executed before every @Test method
	@BeforeMethod
	public void setUp()
	{
		testBase = new TestBase();
		prop = testBase.initialize_Properties();
		driver = testBase.initialize_driver();
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
	}
	
	@Test
	public void verifyLoginPageTitleTest()
	{
	
		String title = loginPage.getLoginPageTitle();
		System.out.println("The login page title is:"+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void verifySignUpLinkTest()
	{
		Assert.assertTrue(loginPage.verifySignUpLink());
	}
	
	@Test
	public void loginCorrectCredentialsTest()
	{
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	//This method will be executed after every test method
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
}
