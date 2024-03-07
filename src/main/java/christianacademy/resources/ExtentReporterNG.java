package christianacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	//Declare as static so there's no need to define object of this class
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Christian's Web Automation Results");
		reporter.config().setDocumentTitle("Christian's Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Christian Sanchez");
		extent.createTest(path); 
		return extent;
	}
	
}
