package com.qa.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateUserPage {

	@FindBy(xpath = "//*[@id=\"username\"]")
	private WebElement username;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[2]/td[2]/input")
	private WebElement password;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[3]/td[2]/input")
	private WebElement confirmPassword;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[4]/td[2]/input")
	private WebElement fullName; 
	
	@FindBy(xpath = "//*[@id=\"yui-gen3-button\"]")
	private WebElement submitCreateUser;  
	
	public void createUser(String name, String pass, String confirm, String full)
	{
		username.click();
		username.sendKeys(name);
		password.click();
		password.sendKeys(pass);
		confirmPassword.click();
		confirmPassword.sendKeys(confirm);
		fullName.click();
		fullName.sendKeys(full);
	}
	
	public void submitUser()
	{
		submitCreateUser.click();
	}
}
