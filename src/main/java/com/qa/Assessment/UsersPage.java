package com.qa.Assessment;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.Assessment.Constants;

public class UsersPage {

	@FindBy(xpath = "//*[@id=\"tasks\"]/div[3]/a[2]")
	private WebElement users; 
	
	//*[@id="people"]/tbody/tr[2]/td[2]
	@FindBy(id = "main-panel")
	private WebElement manageUsers; 
	
	public void clickCreateUser()
	{
		users.click();
	}
	
	public boolean checkUser(String user)
	{
		if(manageUsers.getText().contains(user))
		{
			return true;
		}
		return false;
	}
	
	public boolean checkUserDisplayed(String userId)
	{
		
		//String listDress = "//*[@id=\"center_column\"]/ul/li" == Constants.LISTUSERID 
		//List<WebElement> eachUser = manageUsers.findElements(By.xpath(Constants.LISTUSERID)); 
		List<WebElement> eachUser = getManageUsers().findElements(By.xpath("*[@id='people']/tbody/tr"));
		//may need to move the / at the end of it
		for(int i = 1; i < eachUser.size(); i++)
			{
				
				if(getManageUsers().findElement(By.xpath("//*[@id='people']/tbody/tr[2]/td[2]/tr[" + i + "]/td[2]")).getText().equals(userId))
				{
//					if(manageUsers.getText().contains(fullName))
//					{
//						return true; 
//					}
					return true; 
				}
			}
		return false; 
	}
	
	public String takeScreenShot(WebDriver drive)
	{
		File scrFile = ((TakesScreenshot)drive).getScreenshotAs(OutputType.FILE);
		System.out.println(scrFile.getAbsolutePath());
		try {
			FileUtils.copyFile(scrFile, new File(Constants.LOGSCREENSHOT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.LOGSCREENSHOT;
		//System.out.println(scrFile.getAbsolutePath());

	}
//	public boolean checkUserDisplayed(String userId, String fullName)
//	{
//		
//		//String listDress = "//*[@id=\"center_column\"]/ul/li" == Constants.LISTUSERID 
//		List<WebElement> eachUser = manageUsers.findElements(By.xpath(Constants.LISTUSERID)); 
//		//may need to move the / at the end of it
//		for(int i = 1; i < eachUser.size(); i++)
//			{
//				
//				if(manageUsers.findElement(By.xpath("//*[@id='people']/tbody/tr[2]/td[2]/tr[" + i + "]/td[2]")).getText().equals(userId))
//				{
//					if(manageUsers.getText().contains(fullName))
//					{
//						return true; 
//					}
//					return false; 
//				}
//			}
//		return false; 
//	}


	public WebElement getManageUsers() {
		return manageUsers;
	}


	public void setManageUsers(WebElement manageUsers) {
		this.manageUsers = manageUsers;
	}
}
