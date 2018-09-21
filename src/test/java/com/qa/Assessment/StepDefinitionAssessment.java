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
		driver.get(Constants.LOADUPAGE);
		
		test.log(LogStatus.INFO, "Load up the initial page");
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
		//WebElement wait = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("main-panel")));
		//wait.getText(); 
		UsersPage userVisable = PageFactory.initElements(driver, UsersPage.class);
		System.out.println(userVisable.getManageUsers().getText());
		//Assert.assertEquals("User Not Visable", true, userVisable.getManageUsers().getText().contains("Lucy"));
		//userVisable.checkUserDisplayed("Lucy");
		
		//include the IF for the pass and the fail of the then to the REPORT!!!
		Assert.assertEquals("User Not Displayed", true, userVisable.checkUser("Lucy"));
			   
	}

	@When("^the User details \"([^\"]*)\" username, \"([^\"]*)\" password, \"([^\"]*)\" confirm Password, \"([^\"]*)\" Fullname and \"([^\"]*)\" EmailAddress are entered on the Create UserScreen$")
	public void the_User_details_username_password_confirm_Password_Fullname_and_EmailAddress_are_entered_on_the_Create_UserScreen(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
		ExcelUtilsAssessment.setExcelFile(Constants.ExcelReportPath + Constants.ExcelReportFile, 0);
		ExcelUtilsAssessment.setCellData(arg1, ReportUtils.count, 0);
		ExcelUtilsAssessment.setCellData(arg2, ReportUtils.count, 1);
		ExcelUtilsAssessment.setCellData(arg4, ReportUtils.count, 2);
		
		CreateUserPage create = PageFactory.initElements(driver, CreateUserPage.class);
	    create.createUser(arg1, arg2, arg3, arg4);
	    
	}

	@Then("^the \"([^\"]*)\" username should be visible on the UsersScreen$")
	public void the_username_should_be_visible_on_the_UsersScreen(String arg1)  {
		driver.get(Constants.USERS);
		//WebElement wait = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.id("main-panel")));
		UsersPage userVisable = PageFactory.initElements(driver, UsersPage.class);
		//userVisable.checkUserDisplayed(arg1, "Lucy Hamilton");
		//Assert.assertEquals("User Not Displayed", true, userVisable.checkUserDisplayed(arg1));
		//driver.get(Constants.USERS);
		Assert.assertEquals("User Not Visable", true, userVisable.getManageUsers().getText().contains(arg1));
	}

	@Given("^the \"([^\"]*)\" username is visible on the UsersScreen$")
	public void the_username_is_visible_on_the_UsersScreen(String arg1) 
	{
		test = ReportUtils.report.startTest("Username Visable on Screen");
		driver.get(Constants.LOADUPAGE);
		LoadUpPage load = PageFactory.initElements(driver, LoadUpPage.class);
		load.logIn("Admin", "b0a4a0b0712f4fc88f423b351eda29c0");
	    HomePage home = PageFactory.initElements(driver, HomePage.class);
	    home.clickManageTasks();
	    
	    ManageJenkinsPage manage = PageFactory.initElements(driver, ManageJenkinsPage.class); 
	    manage.clickManage();
	    
	   // UsersPage userVisable = PageFactory.initElements(driver, UsersPage.class);
	   // Assert.assertEquals("User Not Visable", true, userVisable.getManageUsers().getText().contains(arg1));
	   // System.out.println(userVisable.getManageUsers().getText());
	   // UsersPage userVisable = PageFactory.initElements(driver, UsersPage.class);
		//userVisable.checkUserDisplayed(arg1);
	    
	}

	@When("^the \"([^\"]*)\" username is clicked on the UserScreen$")
	public void the_username_is_clicked_on_the_UserScreen(String arg1)  {
	    
	}

	@Then("^the User Profile should display the \"([^\"]*)\" username on the ProfileScreen$")
	public void the_User_Profile_should_display_the_username_on_the_ProfileScreen(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^the \"([^\"]*)\" Username's profile page has been loaded$")
	public void the_Username_s_profile_page_has_been_loaded(String arg1)  {
		test = ReportUtils.report.startTest("Username Profile new email");
	}

	@Given("^the configure button has been clicked on the profile page$")
	public void the_configure_button_has_been_clicked_on_the_profile_page() throws Throwable {
		test = ReportUtils.report.startTest("Username Profile new email");
	    throw new PendingException();
	}

	@When("^I change the old email address on the Configure Page to a new email address \"([^\"]*)\"$")
	public void i_change_the_old_email_address_on_the_Configure_Page_to_a_new_email_address(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I save the changes to the Configure Page$")
	public void i_save_the_changes_to_the_Configure_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the Configure Page should show the new email address \"([^\"]*)\"$")
	public void the_Configure_Page_should_show_the_new_email_address(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@After
	public void tearDown()
	{
		ReportUtils.report.endTest(test);
		ReportUtils.report.flush();
		driver.quit();
	}
}
