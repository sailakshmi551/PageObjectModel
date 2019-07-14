package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;


public class LoginPage extends TestBase
{
	
	//1.a: Define Page Objects (PAGE OR) : using PageFactory Pattern
	@FindBy(id = "username")
	WebElement emailId;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "loginBtn")
	WebElement loginButton;
	
	@FindBy(linkText = "Sign up")
	WebElement signUpLink;
	
	//1.b: Constructor of page class and initialize elements with driver
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//2. Page Methods - Actions:
	public String getLoginPageTitle()
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(signUpLink));
		return driver.getTitle();
	}
	
	public boolean verifySignUpLink()
	{
		return signUpLink.isDisplayed();
	}
	
	public HomePage login(String un, String pwd)
	{
		emailId.sendKeys(un);
		password.sendKeys(pwd);
		loginButton.click();
		
		return new HomePage(driver);
	}





}
