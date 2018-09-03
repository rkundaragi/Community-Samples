package com.PerefctoDemo.ionic.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Utilities {

	public static  String REPORT_LIB = "/Users/sreevatsa/Desktop/Perfecto/Automation/TestReports";
	public static  String SCREENSHOTS_LIB = "/Users/sreevatsa/Desktop/Perfecto/Automation/TestReports/Screenshots";
	public static final String REPORT_TIMER_RESULT = "result";
	public static final String REPORT_TIMER_THRESHOLD = "threshold";
	public static final String REPORT_TIMER_DESCRIPTION = "description";
	public static final String REPORT_TIMER_NAME = "name";
	public static final String MOBILE_STATUS_TIMER_COMMAND = "mobile:status:timer";
	
	public static void StartDeviceLogs(AppiumDriver driver){
		Map<String, Object> params2 = new HashMap<>();
		driver.executeScript("mobile:logs:start", params2);
	}

	public static void StopDeviceLogs(AppiumDriver driver){
		Map<String, Object> params3 = new HashMap<>();
		driver.executeScript("mobile:logs:stop", params3);
	}
	
	
	public static void StartVitals(AppiumDriver driver, int interval){
		Map<String, Object> params = new HashMap<>();
		params.put("sources", "Device");
		params.put("interval", interval);
		driver.executeScript("mobile:monitor:start", params);
	}

	public static void StopVitals(AppiumDriver driver){
		Map<String, Object> params = new HashMap<>();
		driver.executeScript("mobile:monitor:stop", params); 
	}
	 

	public static void NVStart(AppiumDriver driver){
		Map<String, Object> pars = new HashMap<>();
		pars.put("generateHarfile", "true");
		driver.executeScript("mobile:vnetwork:start", pars); 
	}

	public static void NVStart(AppiumDriver driver, String bandwidth){
		Map<String, Object> pars = new HashMap<>();
		pars.put("profile", bandwidth/*"3.5g_hspa_plus_good"*/);
		driver.executeScript("mobile:vnetwork:start", pars);   
	}

	public static void NVStop(AppiumDriver driver){
		Map<String, Object> pars = new HashMap<>();
		driver.executeScript("mobile:vnetwork:stop", pars);  
	}

	// Returns ux timer
		// Wind Tunnel: Gets the requested timer
		public static long timerGet(AppiumDriver<WebElement> driver, String timerType) {
			String command = "mobile:timer:info";
			Map<String, String> params = new HashMap<String, String>();
			params.put("type", timerType);
			long result = (Long) driver.executeScript(command, params);
			return result;
		}
		public static long getUXTimer(AppiumDriver<WebElement> driver) {
			long timer = timerGet(driver, "ux");
			return timer;
		}
		
		public static String reportTimer(RemoteWebDriver driver, long result, long threshold, String description,
				String name) {
			Map<String, Object> params = new HashMap<String, Object>(7);
			params.put(REPORT_TIMER_RESULT, result);
			params.put(REPORT_TIMER_THRESHOLD, threshold);
			params.put(REPORT_TIMER_DESCRIPTION, description);
			params.put(REPORT_TIMER_NAME, name);
			String status = (String) driver.executeScript(MOBILE_STATUS_TIMER_COMMAND, params);
			return status;
		}

	public static void StartDeviceLogs(RemoteWebDriver driver){
		Map<String, Object> params2 = new HashMap<>();
		driver.executeScript("mobile:logs:start", params2);
	}

	public static void StopDeviceLogs(RemoteWebDriver driver){
		Map<String, Object> params3 = new HashMap<>();
		driver.executeScript("mobile:logs:stop", params3);
	}

	

	public static void startApp(String appName,  AppiumDriver<MobileElement> driver) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", appName);
		driver.executeScript("mobile:application:open", params);
	}

	public static void stopApp(String appName, AppiumDriver<WebElement> d) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", appName);
		d.executeScript("mobile:application:close", params);
	}

	public static void hideKeyboard(AppiumDriver<MobileElement> driver, String OS)
	{
		if (OS.contentEquals("iOS")) {
			driver.findElement(By.xpath("//UIAButton[@label='Done']")).click();

		} else {
			driver.hideKeyboard();
		}
	}

	@SuppressWarnings("unchecked")
	public static String getScreenShot(AppiumDriver<WebElement> driver, String name, String deviceID) {
		String screenShotName = SCREENSHOTS_LIB + name + "_" + deviceID + ".png";
		driver = (AppiumDriver<WebElement>) new Augmenter().augment(driver);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(screenShotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return screenShotName;
	}

	public static void downloadReport(RemoteWebDriver driver, String type, String fileName) throws IOException {
		try {
			String command = "mobile:report:download";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type", "html");
			String report = (String) driver.executeScript(command, params);
			File reportFile = new File(getReprtName(fileName, true));
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(reportFile));
			byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report);
			output.write(reportBytes);
			output.close();
		} catch (Exception ex) {
			System.out.println("Got exception " + ex);
		}
	}

	public static String getReprtName(String repID, boolean withPath) {
		if (withPath) {
			return REPORT_LIB + "/rep_" + repID + ".html";
		} else {
			return "/rep_" + repID + ".html";
		}
	}

	public static void deviceHome(RemoteWebDriver driver2) {
		System.out.println("hitting device HOME key");
		Map<String, Object> params1 = new HashMap<String, Object>();
		driver2.executeScript("mobile:handset:ready", params1);
	}

	public static void openApplication(RemoteWebDriver driver, String AppName) {
		System.out.println("opening application: " + AppName);
		String command = "mobile:application:open";
		Map<String, Object> Parms = new HashMap<String, Object>();
		Parms.put("name", AppName);
		driver.executeScript(command, Parms);
	}

	public static void switchToContext(RemoteWebDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}

	public void scrolltoXPath(AppiumDriver<MobileElement> driver, String xPath) {

		do {
			try {
				driver.findElement(By.xpath(xPath));
				break;

			} catch (Exception NoSuchElementException) {
				Dimension dimensions = driver.manage().window().getSize();
				Double screenHeightStart = dimensions.getHeight() * 0.5;
				int scrollStart = screenHeightStart.intValue();
				Double screenHeightEnd = dimensions.getHeight() * 0.2;
				int scrollEnd = screenHeightEnd.intValue();
				//	driver.swipe(10, scrollStart, 10, scrollEnd, 2000);

			}

		} while (true);
	}

	public static void scroll(AppiumDriver<MobileElement> driver){

		Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.9;
		Double screenWidth = dimensions.getWidth()*0.50;
		int screenwidthStart = screenWidth.intValue();
		int scrollStart = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.3;
		int scrollEnd = screenHeightEnd.intValue();



		for (int i=0;i<=2; i++){

			//driver.swipe(screenwidthStart, scrollStart, screenwidthStart, scrollEnd, 1000);

		}
	}
	public void swipeDown(AppiumDriver<MobileElement> driver) {

		Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		int scrollStart = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int scrollEnd = screenHeightEnd.intValue();
		//	driver.swipe(10, scrollStart, 10, scrollEnd, 2000);
	}

	public static void enterTextfield(AppiumDriver<WebElement> driver,String label, String text)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("label", label);
		params.put("text", text);
		params.put("label.direction", "above");
		params.put("label.offset", "7%");
		driver.executeScript("mobile:edit-text:set",params);
	}

	public static void imageCheck(AppiumDriver<MobileElement> driver, String filePath)
	{
		Map<String, Object> params2 = new HashMap<>();
		params2.put("content", filePath);
		params2.put("match", "bounded");
		params2.put("threshold", "90");
		params2.put("duration","10");
		Object result2 = driver.executeScript("mobile:checkpoint:image", params2);
	}





	public static void ocrTextCheck(AppiumDriver<MobileElement> driver, String text)
	{
		Map<String, Object> params1 = new HashMap<>();
		params1.put("content", text);
		Object result1 = driver.executeScript("mobile:checkpoint:text", params1);
	}

	public void imageCheck(String filePath, AppiumDriver<MobileElement> driver)
	{
		Map<String, Object> params2 = new HashMap<>();
		params2.put("content",filePath );
		params2.put("image.top", "0");
		params2.put("image.height", "989");
		params2.put("image.left", "25");
		params2.put("image.width", "1402");
		params2.put("timeout", "10");
		params2.put("threshold", "90");
		params2.put("match", "similar");
		Object result2 = driver.executeScript("mobile:image:find", params2);
	}

	public static void scrolltoXPath1(RemoteWebDriver driver, MobileElement element) {
		
		do {
			try {
				element.isDisplayed();
				break;

			} catch (Exception NoSuchElementException) {
				
				Map<String, Object> params = new HashMap<>();
				params.put("start", "20%,90%");
				params.put("end", "20%,20%");
				Object res = driver.executeScript("mobile:touch:swipe", params);

			}
			
		} while (true);
	}


	public static void device_vitals_Start(AppiumDriver<MobileElement> driver){
		Map<String, Object> params1 = new HashMap<>();
		List<String> vitals1 = new ArrayList<>();
		vitals1.add("all");
		params1.put("vitals", vitals1);
		List<String> sources1 = new ArrayList<>();
		sources1.add("device");
		sources1.add("4.8.0");
		params1.put("sources", sources1);
		Object result1 = driver.executeScript("mobile:monitor:start", params1);
	}

	public static void device_vitals_stop(AppiumDriver<MobileElement> driver){
		Map<String, Object> params2 = new HashMap<>();
		Object result2 = driver.executeScript("mobile:monitor:stop", params2);

	}


	//			Map<String, Object> params1 = new HashMap<>();
	//			List<String> vitals1 = new ArrayList<>();
	//			vitals1.add("all");
	//			params1.put("vitals", vitals1);
	//			params1.put("interval", "1");
	//			List<String> sources1 = new ArrayList<>();
	//			sources1.add("device");
	//			params1.put("sources", sources1);
	//			Object result1 = driver.executeScript("mobile:monitor:start", params1);
	//			




}

