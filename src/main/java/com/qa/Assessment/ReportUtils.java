package com.qa.Assessment;

import com.qa.Assessment.Constants;
import com.relevantcodes.extentreports.ExtentReports;

public class ReportUtils {

	public static int count = 0; 
	public static ExtentReports report = new ExtentReports(Constants.ReportFile, true);
}
