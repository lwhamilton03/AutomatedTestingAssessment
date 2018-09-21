package com.qa.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoadUpPage {

	@FindBy(xpath = "//*[@id=\"j_username\"]")
	private WebElement user; 
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/div/form/table/tbody/tr[2]/td[2]/input")
	private WebElement pass; 
	
	@FindBy(xpath = "//*[@id=\"yui-gen1-button\"]")
	private WebElement submitButton;
	
	public void logIn(String username, String password)
	{
		user.click();
		user.sendKeys(username);
		pass.click();
		pass.sendKeys(password);
		submitButton.submit();
	}
	
}
