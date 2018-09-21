package com.qa.Assessment;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(xpath = "//*[@id=\"tasks\"]/div[4]/a[2]")
	private WebElement manageTasksButton; 
	
	public void clickManageTasks()
	{
		manageTasksButton.click();
	}
}
