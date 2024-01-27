package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	
	public static ExtentReports genrateReports() {
		
		 ExtentSparkReporter htmlReporter=new ExtentSparkReporter("ExtentReport.html");
		 ExtentReports reports=new ExtentReports();
		 reports.attachReporter(htmlReporter);
		 reports.setSystemInfo("Version","5.1");
		 reports.setSystemInfo("Created By", "Ishwar");
		 reports.setSystemInfo("Testing Type", "Regression");
		 return reports; 
	}

}
