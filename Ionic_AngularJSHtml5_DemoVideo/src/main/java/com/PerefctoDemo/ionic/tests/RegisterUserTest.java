package com.PerefctoDemo.ionic.tests;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.PerefctoDemo.ionic.pageObjects.BaseClass;
import com.PerefctoDemo.ionic.pageObjects.HomeScreenPageObject;
import com.PerefctoDemo.ionic.pageObjects.JoinScreenPageObject;
import com.PerefctoDemo.ionic.pageObjects.RegistrationPageObject;
import com.PerefctoDemo.ionic.utils.Utilities;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

import bsh.util.Util;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.HideKeyboardStrategy;

public class RegisterUserTest extends BaseClass{
	
	 


  public AppiumDriver<MobileElement>  driver;
public	ReportiumClient reportiumClient;

	JoinScreenPageObject joinScreenPageObject;
	HomeScreenPageObject homeScreenPageObject;
	RegistrationPageObject registrationPageObject;
	 
	/*
	@Parameters({ "deviceID", "appName", "mobileOS"})
	@BeforeTest
	public void beforeTest(String deviceID, String appName, String mobileOS) throws IOException {
		driver = Utilities.getAppiumDriver(deviceID, appName, mobileOS);
		homeScreenPageObject = new HomeScreenPageObject(driver);
		joinScreenPageObject = new JoinScreenPageObject(driver);
		registrationPageObject = new RegistrationPageObject(driver);
	
		// Reporting client. For more details, see
		// https://github.com/perfectocode/samples/wiki/reporting
		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
				.withProject(new Project("IHG", "1.0")).withJob(new Job("Job1", 45)).withContextTags("Smoke")
				.withWebDriver(driver).build();
		reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
	
	}*/
	
	

	@Parameters({ "mobileOS" })
	@Test
	public void customerJourneyTest(){
		try {
			
			driver=getDriver();
			
			reportiumClient=getReportiumClient();
			System.out.println("driver & Reportium object : "  + driver + ", " + reportiumClient );
			reportiumClient.testStart("IHG Demo_2", new TestContext("IHG", "smoketest"));
					// this is a logical step for reporting
			
		    driver.resetApp();
		    
//		    Utilities.NVStop(driver);
//		    Utilities.StartDeviceLogs(driver);
//		    Utilities.StartVitals(driver, 15);
		    
		    
		    homeScreenPageObject = new HomeScreenPageObject(driver);
		    
		  Assert.assertEquals(homeScreenPageObject.verifyRegistrationIcon(), true);  
		    homeScreenPageObject.enterUserName();
		    
		    joinScreenPageObject = homeScreenPageObject.clickOnRegistrationIcon();
		  //  joinScreenPageObject = new JoinScreenPageObject(driver);
		    joinScreenPageObject.enterUsername();
		    joinScreenPageObject.enterEmail();
		    joinScreenPageObject.enterPassword();
		    joinScreenPageObject.entername();
		    driver.hideKeyboard();
//		    Utilities.switchToContext(driver, "WEBVIEW");
//		    Utilities.NVStart(driver, "3.5g_hspa_plus_good");
//		    
//		    JavascriptExecutor executor = (JavascriptExecutor)driver;
//		    executor.executeScript("arguments[0].click();", joinScreenPageObject.registrationButton);
		    
		    /*homeScreenPageObject.clickOnRegistrationIcon()
		    .enterUsername()
		    .enterUsername()
		    .enterEmail()
		    .enterPassword()
		    .entername();
		    driver.hideKeyboard();*/
		 
		 //   joinScreenPageObject = new JoinScreenPageObject(driver);
		    joinScreenPageObject.clickOnProgramingLanguage()
		    .selectLanguage()
		    .clickOnregistrationButton();
		    
		    registrationPageObject = new RegistrationPageObject(driver);
		   Assert.assertEquals(registrationPageObject.verifyregistrationButton(), true); 
		   
//		   Utilities.StopVitals(driver);
//		   Utilities.StopDeviceLogs(driver);
//		   Utilities.NVStop(driver);
		   reportiumClient.testStop(TestResultFactory.createSuccess());
		} catch (Exception e) {
			Utilities.StopDeviceLogs(driver);
			reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
			e.printStackTrace();
		}
	}

	

}
