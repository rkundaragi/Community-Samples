package com.PerefctoDemo.ionic.tests;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.PerefctoDemo.ionic.pageObjects.BaseClass;
import com.PerefctoDemo.ionic.pageObjects.HomeScreenPageObject;
import com.PerefctoDemo.ionic.pageObjects.IonicHomeScreenPageObject;
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

public class IonicTest extends BaseClass{
	
	 


  public AppiumDriver<MobileElement>  driver;
public	ReportiumClient reportiumClient;

IonicHomeScreenPageObject ionicHomeScreenPageObject;
	 
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
			ionicHomeScreenPageObject = new IonicHomeScreenPageObject(driver);
			
			reportiumClient=getReportiumClient();
			System.out.println("driver & Reportium object : "  + driver + ", " + reportiumClient );
			reportiumClient.testStart("IHG Demo_2", new TestContext("IHG", "smoketest"));
					// this is a logical step for reporting
			
		    driver.resetApp();
		    Thread.sleep(15000);
		    driver.context("WEBVIEW");
		   ionicHomeScreenPageObject.clickNextButton();
		   ionicHomeScreenPageObject.clickNextButton();
		    driver.context("WEBVIEW");
		    ionicHomeScreenPageObject.clickFinishButton();
		    ionicHomeScreenPageObject.clickHamburgerMenu();
		    ionicHomeScreenPageObject.clickListView();
		    ionicHomeScreenPageObject.clickExpendable();
		    ionicHomeScreenPageObject.enterEmail("test1@gmail.com");
		    ionicHomeScreenPageObject.clickOnCancel();
		    ionicHomeScreenPageObject.clickDragDrop();
		    ionicHomeScreenPageObject.clickBackbutton();
		    ionicHomeScreenPageObject.clickListViewHamburgerMenu();
		    ionicHomeScreenPageObject.clickLoginPages();
		    ionicHomeScreenPageObject.clickLoginLogo1();
		    ionicHomeScreenPageObject.enterUsername("John");
		    ionicHomeScreenPageObject.enterPassword("Perfecto");
		    ionicHomeScreenPageObject.clickLoginButton();
		    ionicHomeScreenPageObject.clickBackbutton();
		    ionicHomeScreenPageObject.clickLoginHamburgerMenu();
		    ionicHomeScreenPageObject.checkbox.click();
		    ionicHomeScreenPageObject.simpleCheckbox.click();
		    ionicHomeScreenPageObject.simpleCheckbox1.click();
		    ionicHomeScreenPageObject.clickBackbutton();
		    ionicHomeScreenPageObject.clickLoginHamburgerMenu();
		    Utilities.scrolltoXPath1(driver, ionicHomeScreenPageObject.Maps);
		    ionicHomeScreenPageObject.Maps.click();
		
		   /* 
		    Map<String, Object> params1 = new HashMap<>();
		    params1.put("content", "camera");
		    params1.put("words", "words");
		    Object result1 = driver.executeScript("mobile:text:select", params1);
		    
		    */
		    
		    
		    
		    
		    
		    
		  
		   reportiumClient.testStop(TestResultFactory.createSuccess());
		} catch (Exception e) {
			
			reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
			e.printStackTrace();
		}
	}

	

}
