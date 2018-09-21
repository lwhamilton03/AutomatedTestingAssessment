package com.qa.Assessment;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.Assessment.Constants;
import com.qa.Assessment.ExcelUtilsAssessment;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitionAssessment {

	WebDriver driver = null; 
	public ExtentReports report;
	public ExtentTest test; 
	
	@Before
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER);
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();
	}
	
	
	@Given("^that you are on the create UserScreen$")
	public void that_you_are_on_the_create_UserScreen()
	{
		test = ReportUtils.report.startTest("UserScreen");
		ReportUtils.count++; 
		
		test.log(LogStatus.INFO, "Load up the initial page");
		driver.get(Constants.LOADUPAGE);
		
		LoadUpPage load = PageFactory.initElements(driver, LoadUpPage.class);
		
		test.log(LogStatus.INFO, "Log In as Admin");
		load.logIn("Admin", "b0a4a0b0712f4fc88f423b351eda29c0");
	    
	    test.log(LogStatus.INFO, "Navigate to manage tasks");
		HomePage home = PageFactory.initElements(driver, HomePage.class);
	    home.clickManageTasks();
	    
	    test.log(LogStatus.INFO, "Navigate to User Page");
	    ManageJenkinsPage manage = PageFactory.initElements(driver, ManageJenkinsPage.class); 
	    manage.clickManage();
	    
	    test.log(LogStatus.INFO, "Navigate to Create User Page");
	    UsersPage user = PageFactory.initElements(driver, UsersPage.class);
	    user.clickCreateUser();
	}

	@When("^the User details are entered on the Create UserScreen$")
	public void the_User_details_are_entered_on_the_Create_UserScreen()  
	{
		test.log(LogStatus.INFO, "Creating User");
	    CreateUserPage create = PageFactory.initElements(driver, CreateUserPage.class);
	    create.createUser("Lucy", "password", "password", "Lucy Hamilton");
	}

	@When("^the details are submitted on the Create UserScreen$")
	public void the_details_are_submitted_on_the_Create_UserScreen()  
	{
		test.log(LogStatus.INFO, "Submitting User");
		CreateUserPage create = PageFactory.initElements(driver, CreateUserPage.class);
		create.submitUser();
	}

	@Then("^the Username should be visible on the UsersScreen$")
	public void the_Username_should_be_visible_on_the_UsersScreen() 
	{
		driver.get(Constants.USERS);
		//Try and use a wait or redirect
		
		UsersPage userVisable = PageFactory.initElements(driver, UsersPage.class);
//		Assert.assertEquals("User Not Displayed", true, userVisable.checkUser("Lucy"));
		test.log(LogStatus.INFO, "Checking if the User is visible on the screen");
		//INSERT SCREENSHOT IF TIME ALLOWS
		userVisable.screenShot(driver);
		
		if(userVisable.checkUserDisplayed("Lucy"))
		{
			test.log(LogStatus.PASS, "User is displayed on the User Screen");
		}
		else
		{
			test.log(LogStatus.FAIL, "User is NOT displayed on the User Screen");
		}
		
		Assert.assertEquals("User Not Visable", true, userVisable.checkUserDisplayed("Lucy"));
	}

	@When("^the User details \"([^\"]*)\" username, \"([^\"]*)\" password, \"([^\"]*)\" confirm Password, \"([^\"]*)\" Fullname and \"([^\"]*)\" EmailAddress are entered on the Create UserScreen$")
	public void the_User_details_username_password_confirm_Password_Fullname_and_EmailAddress_are_entered_on_the_Create_UserScreen(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
		
		//Writing to Excel Spreadsheet
		ExcelUtilsAssessment.setExcelFile(Constants.ExcelReportPath + Constants.ExcelReportFile, 0);
		ExcelUtilsAssessment.setCellData(arg1, ReportUtils.count, 0);
		ExcelUtilsAssessment.setCellData(arg2, ReportUtils.count, 1);
		ExcelUtilsAssessment.setCellData(arg4, ReportUtils.count, 2);
		
		test.log(LogStatus.INFO, "User Created using Username: " + arg1 + ", Password: " + arg2 + " Confirm Password: " + arg3 + ", Fullname: " + arg4 );
		CreateUserPage create = PageFactory.initElements(driver, CreateUserPage.class);
	    create.createUser(arg1, arg2, arg3, arg4);
	}

	@Then("^the \"([^\"]*)\" username should be visible on the UsersScreen$")
	public void the_username_should_be_visible_on_the_UsersScreen(String arg1) 
	{
		driver.get(Constants.USERS);
		ExcelUtilsAssessment.setExcelFile(Constants.ExcelReportPath + Constants.ExcelReportFile, 0);
		//WebElement wait = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("main-panel")));
		UsersPage userVisable = PageFactory.initElements(driver, UsersPage.class);
		if(userVisable.checkUserDisplayed(arg1))
		{
			ExcelUtilsAssessment.setCellData("PASS", ReportUtils.count, 3);
			test.log(LogStatus.PASS, arg1 + " is visible on the User Screen");
		}
		else
		{
			ExcelUtilsAssessment.setCellData("FAIL", ReportUtils.count, 3);
			test.log(LogStatus.FAIL, arg1 + " is NOT visible on the User Screen");
		}
		//INSERT SCREENSHOT if time will allow for it
		Assert.assertEquals("User Not Displayed", true, userVisable.checkUserDisplayed(arg1));
	}

	@Given("^the \"([^\"]*)\" username is visible on the UsersScreen$")
	public void the_username_is_visible_on_the_UsersScreen(String arg1) 
	{
		test = ReportUtils.report.startTest("User Profile displaying on the profile screen Test");
		ReportUtils.count++;
		
		test.log(LogStatus.INFO, "Load up the initial page");
		driver.get(Constants.LOADUPAGE);
		
		test.log(LogStatus.INFO, "Log In using Admin");
		LoadUpPage load = PageFactory.initElements(driver, LoadUpPage.class);
		load.logIn("Admin", "b0a4a0b0712f4fc88f423b351eda29c0");
		
		test.log(LogStatus.INFO, "Navigate to manage tasks");
	    HomePage home = PageFactory.initElements(driver, HomePage.class);
	    home.clickManageTasks();
	    
	    test.log(LogStatus.INFO, "Navigate to manage users");
	    ManageJenkinsPage manage = PageFactory.initElements(driver, ManageJenkinsPage.class); 
	    manage.clickManage();

	  //Checking IF user DISPLAYED
	  // UsersPage userVisable = PageFactory.initElements(driver, UsersPage.class);
	  //  Assert.assertEquals("User Not Displayed", true, userVisable.checkUserDisplayed(arg1));
	    
	}

	@When("^the \"([^\"]*)\" username with Full Name \"([^\"]*)\" is clicked on the UserScreen$")
	public void the_username_with_Full_Name_is_clicked_on_the_UserScreen(String arg1, String arg2) 
	{
		test.log(LogStatus.INFO, "Search for" + arg1 + " and Click to their profile page");
	    UsersPage userSearch = PageFactory.initElements(driver, UsersPage.class);
	    userSearch.findUser(arg1, arg2);
	    
	}

	@Then("^the User Profile should display the \"([^\"]*)\" username with FullName \"([^\"]*)\" on the ProfileScreen$")
	public void the_User_Profile_should_display_the_username_with_FullName_on_the_ProfileScreen(String arg1, String arg2)
	{
		ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);
		if(profile.getNameText().getText().equals(arg2))
		{
			test.log(LogStatus.PASS, arg1 + " is displayed on the Profile Screen");
		}
		else
		{
			test.log(LogStatus.FAIL, arg1 + " is NOT displated on the profile screen");
		}
		Assert.assertEquals("Username not displayed", true, profile.getNameText().getText().equals(arg2));
	}
	

	@Given("^the \"([^\"]*)\" Username's profile page has been loaded$")
	public void the_Username_s_profile_page_has_been_loaded(String arg1) 
	{
		test = ReportUtils.report.startTest("Username Profile new email");
		ReportUtils.count++; 
			
		test.log(LogStatus.INFO, "Load up the initial page");
		driver.get(Constants.LOADUPAGE);
		
		LoadUpPage load = PageFactory.initElements(driver, LoadUpPage.class);
		
		test.log(LogStatus.INFO, "Log In as Admin");
		load.logIn("Admin", "b0a4a0b0712f4fc88f423b351eda29c0");
	    
	    test.log(LogStatus.INFO, "Navigate to manage tasks");
		HomePage home = PageFactory.initElements(driver, HomePage.class);
	    home.clickManageTasks();
	    
	    test.log(LogStatus.INFO, "Navigate to User Page");
	    ManageJenkinsPage manage = PageFactory.initElements(driver, ManageJenkinsPage.class); 
	    manage.clickManage();
	   
	    test.log(LogStatus.INFO, "Search" + arg1 + " and Click on Profile");
	    UsersPage userSearch = PageFactory.initElements(driver, UsersPage.class);
	    userSearch.findUserName(arg1);
		
		driver.get(Constants.CONFIGPAGE + arg1 + "/configure");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	@Given("^the configure button has been clicked on the profile page$")
	public void the_configure_button_has_been_clicked_on_the_profile_page()
	{
		
		test.log(LogStatus.INFO, "click the configure button");
		LoadUpPage load = PageFactory.initElements(driver, LoadUpPage.class);
		
		test.log(LogStatus.INFO, "Log In as Admin");
		load.logIn("Admin", "b0a4a0b0712f4fc88f423b351eda29c0");
		ProfilePage profile = PageFactory.initElements(driver, ProfilePage.class);
		profile.clickConfigure();
		
	}
	
	@When("^I change the old full name on the Configure Page to a new full name \"([^\"]*)\"$")
	public void i_change_the_old_full_name_on_the_Configure_Page_to_a_new_full_name(String arg1) 
	{
	    ConfigurePage config = PageFactory.initElements(driver, ConfigurePage.class);
	    config.clickName();
	    
	    test.log(LogStatus.INFO, "Click on the full name bar");
	    Actions action = new Actions(driver);
	    test.log(LogStatus.INFO, "double click and wrtie name ");
	    action.doubleClick().click().sendKeys(arg1).perform();
	    try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    test.log(LogStatus.INFO, "Apply the new name");
	    config.apply();
	    
	}

	@When("^I save the changes to the Configure Page$")
	public void i_save_the_changes_to_the_Configure_Page() 
	{
		test.log(LogStatus.INFO, "Save the new name (changes)");
		ConfigurePage config = PageFactory.initElements(driver, ConfigurePage.class);
		config.save(); 
	}

	@Then("^the Configure Page should show the new email address \"([^\"]*)\"$")
	public void the_Configure_Page_should_show_the_new_email_address(String arg1) 
	{
		ConfigurePage config = PageFactory.initElements(driver, ConfigurePage.class);
		Assert.assertEquals("Changes have not been saved", true, config.getFullName().getText().equals(arg1));
	}
	
	@After
	public void tearDown()
	{
		ReportUtils.report.endTest(test);
		ReportUtils.report.flush();
		driver.quit();
	}
}
