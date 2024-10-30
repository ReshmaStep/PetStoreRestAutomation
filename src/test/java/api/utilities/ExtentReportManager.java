package api.utilities;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//5th File , reports folder and testing TESTNG xml file//
// right click on api.test and convert to testNG


	
	

	public class ExtentReportManager implements ITestListener // ITlist is class
	{
		public ExtentSparkReporter sparkReporter; // predefined classes from extent report, look and feel
		public ExtentReports extent;// for  populate common info on report
		public ExtentTest test;// writng testcase like entries in report n update status of test method
		
		String repName;
		
		public void onStart(ITestContext testContext)// listerner methods on start, ontest...etc// onStart will implement only once
		{		
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
			repName="Test-Report-"+timeStamp+".html";
					
			sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
		    sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject"); // Title of report
			sparkReporter.config().setReportName("Pet Store Users API"); // name of the report
			sparkReporter.config().setTheme(Theme.DARK);
					
			extent=new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "Pest Store Users API");
			extent.setSystemInfo("Operating System", System.getProperty("os.name"));
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environemnt","QA");
			extent.setSystemInfo("user","pavan");
		}
		
			
		public void onTestSuccess(ITestResult result)
		{
			test=extent.createTest(result.getName());//create entry new in report
			test.assignCategory(result.getMethod().getGroups());//status p/f/s
			test.createNode(result.getName());
			test.log(Status.PASS, "Test Passed");
		}
		
		


		public void onTestFailure(ITestResult result)
		{
			test=extent.createTest(result.getName()); 
			test.createNode(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.FAIL, "Test Failed");
			test.log(Status.FAIL, result.getThrowable().getMessage());
		}
		
		public void onTestSkipped(ITestResult result)
		{
			test=extent.createTest(result.getName()); 
			test.createNode(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, "Test Skipped");
			test.log(Status.SKIP, result.getThrowable().getMessage());
		}
		
		public void onFinish(ITestContext testContext)
		{
			extent.flush();
		}
	}
	

	
