package com.qa.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfigurePage {

	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/table/tbody/tr/td/input")
	private WebElement fullName; 
	
	@FindBy(xpath = "//*[@id=\"yui-gen2-button\"]")
	private WebElement applyButton;
	
	@FindBy(xpath = "//*[@id=\"yui-gen5-button\"]")
	private WebElement saveButton; 
	
	public void clickName()
	{
		getFullName().click();
	}
	
	public void apply()
	{
		applyButton.click();
	}
	public void save()
	{
		saveButton.click();
	}

	public WebElement getFullName() {
		return fullName;
	}

	public void setFullName(WebElement fullName) {
		this.fullName = fullName;
	}
}
