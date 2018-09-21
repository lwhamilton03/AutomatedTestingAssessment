package com.qa.Assessment; 

public class Constants {

	public static final String FEATUREFILE = "C:\\Users\\Admin\\Documents\\Lucy Hamilton\\AssessmentAutoTest\\src\\test\\java\\featureFileAssessment.feature"; 
	public static final String CHROMEDRIVER = "C:/Users/Admin/Documents/Selenium/chromedriver.exe"; 

	public static final String LOADUPAGE = "http://localhost:8080/login?from=%2F";
	public static final String HOMEPAGE = "http://localhost:8080/"; 
	public static final String MANAGEJENKINS ="http://localhost:8080/manage";
	public static final String USERS = "http://localhost:8080/securityRealm/";
	public static final String CREATEUSERPAGE = "http://localhost:8080/securityRealm/addUser"; 
	
	public static final String LISTUSERID = "//*[@id=\"people\"]/tbody";
	
	public static final String ReportFile = System.getProperty("user.dir") + "\\reportAssessment.html";  
	public static final String ExcelReportPath = System.getProperty("user.dir") + "\\"; 
	//public static final String ExcelReportPath = "C:\\Users\\Admin\\Documents\\Lucy Hamilton\\DemoSiteBDD\\";
	public static final String ExcelReportFile = "excelReportAssessment.xlsx";
	
	public static final String LOGSCREENSHOT = System.getProperty("user.dir") + "\\screenshot.png";
}
