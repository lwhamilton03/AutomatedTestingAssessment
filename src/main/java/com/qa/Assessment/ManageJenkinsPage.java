package com.qa.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageJenkinsPage {

	@FindBy(xpath = "//*[@id=\"management-links\"]/tbody/tr[15]/td[2]/div[1]/a")
	private WebElement manage; 
	
	public void clickManage()
	{
		manage.click();
	}
}
