package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class ContactsPage extends TestBase 
{
	@FindBy(xpath = "//span[text()='Create contact']")
	@CacheLookup
	WebElement createContactButton;
	
	@FindBy(id = "uid-ctrl-1")
	WebElement email;
	
	@FindBy(id = "uid-ctrl-2")
	WebElement firstName;
	
	@FindBy(id = "uid-ctrl-3")
	WebElement lastName;
	
	@FindBy(id = "uid-ctrl-5")
	WebElement jobTitle;
	
	@FindBy(xpath = "//li//span[text()='Create contact']")
	WebElement createContactSecondBtn;
	
	
	public ContactsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createNewContact(String emailVal, String firstname, String lastname, String jobtitle)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions.elementToBeClickable(createContactButton));
		createContactButton.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(email));
		email.sendKeys(emailVal);
		
		wait.until(ExpectedConditions.elementToBeClickable(firstName));
		firstName.sendKeys(firstname);
		
		wait.until(ExpectedConditions.elementToBeClickable(lastName));
		lastName.sendKeys(lastname);
		
		wait.until(ExpectedConditions.elementToBeClickable(jobTitle));
		jobTitle.sendKeys(jobtitle);
		
		wait.until(ExpectedConditions.elementToBeClickable(createContactSecondBtn));
		System.out.println("Hai");
		createContactSecondBtn.click();
		TestUtil.shortWait();
	}
}
