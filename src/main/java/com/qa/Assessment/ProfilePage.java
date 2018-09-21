package com.qa.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage {

	@FindBy(id = "main-panel")
	private WebElement body;

	@FindBy(xpath = "//*[@id=\"main-panel\"]/h1")
	private WebElement nameText; 
	//*[@id="main-panel"]/form/table/tbody/tr[1]/td[3]/input
	//*[@id="main-panel"]/form/table/tbody/tr[1]/td[3]/input
	//*[@id="main-panel"]/form/table/tbody/tr[1]/td[3]/input
	@FindBy(xpath = "//*[@id=\"tasks\"]/div/a")
	private WebElement configureButton; 
	
	public WebElement getBody() {
		return body;
	}

	public void setBody(WebElement body) {
		this.body = body;
	}

	public WebElement getNameText() {
		return nameText;
	}

	public void setNameText(WebElement nameText) {
		this.nameText = nameText;
	} 
	
	public void clickConfigure()
	{
		configureButton.click();
	}
}
