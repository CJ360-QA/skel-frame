package utils;

import java.io.IOException;
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

import baseClass.BaseClass;

public class Listeners implements ITestListener {
public ExtentSparkReporter spark;
public ExtentReports extent;
public ExtentTest test;

String filename;
	

@Override
public void onStart(ITestContext context) {
	String timestamp=new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss").format(new Date());
	filename="myreport"+timestamp+".html";
	
	spark=new ExtentSparkReporter(System.getProperty("user.dir")+filename);
	spark.config().setDocumentTitle("QA automation report");
	spark.config().setReportName("functional test");
	spark.config().setTheme(Theme.DARK);
	
	
	extent=new ExtentReports();
	extent.attachReporter(spark);
	extent.setSystemInfo("ENV", "QA");
	extent.setSystemInfo("Browser", "Chrome");
	extent.setSystemInfo("OS", "windows10");
}



@Override
	public void onTestSuccess(ITestResult result) {
	
	test=extent.createTest(result.getName());
	test.log(Status.PASS,"Pass"+ result.getName());
	
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test=extent.createTest(result.getName());
		test.log(Status.FAIL,"failed"+result.getName());
		test.log(Status.INFO,"reason"+ result.getThrowable());
		
		
		try {
			
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch(IOException e) {
			e.printStackTrace();
		}
	
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.SKIP,"skipped"+result.getName());
		test.log(Status.INFO,"reason"+ result.getName());
	}



	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
	

}
