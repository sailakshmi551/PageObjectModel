package com.qa.testcases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.constants.Constants;
import com.qa.pages.ContactsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;

public class ContactsPageTest 
{
	public TestBase testBase;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public ContactsPage contactsPage;
	
	@BeforeMethod
	public void setUp()
	{
		testBase = new TestBase();
		prop = testBase.initialize_Properties();
		driver = testBase.initialize_driver();
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.navigateToContactsPage();
	}
	
	@DataProvider(name = "getContactsTestData")
	public Object[][] getContactsTestData() {
		Object contactsData[][] = TestUtil.getTestData(Constants.CONTACTS_SHEET_NAME);
		return contactsData;
	}
	
	

	@Test(dataProvider = "getContactsTestData")
	public void createNewContactTest(String email, String firstName, String lastName, String jobTitle) {
		contactsPage.createNewContact(email, firstName, lastName, jobTitle);
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
