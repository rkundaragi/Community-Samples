package com.PerefctoDemo.ionic.pageObjects;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.PerefctoDemo.ionic.utils.PerfectoLabUtils;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class BaseClass {

	public AppiumDriver<MobileElement> driver;
	public ReportiumClient reportiumClient;

	public AppiumDriver<MobileElement> getAppiumDriver(String deviceId, String app, String platform)
			throws IOException {

		DesiredCapabilities capabilities = new DesiredCapabilities("mobileOS", "", Platform.ANY);

		if (platform.equalsIgnoreCase("ios")) {
			capabilities.setCapability("bundleId", app);

		} else {
			// capabilities.setCapability("app-activity", app);
			capabilities.setCapability("appPackage", app);
		}

		capabilities.setCapability("user", "praveenh@perfectomobile.com");
		capabilities.setCapability("password", "");
		capabilities.setCapability("deviceName", deviceId);
		capabilities.setCapability("noReset", false);
		capabilities.setCapability("automationName", "appium");
		PerfectoLabUtils.setExecutionIdCapability(capabilities, "partners.perfectomobile.com");

		try {
			if (platform.equalsIgnoreCase("ios"))
				driver = new IOSDriver<MobileElement>(
						new URL("https://partners.perfectomobile.com" + "/nexperience/perfectomobile/wd/hub"),
						capabilities);
			else
				driver = new AndroidDriver<MobileElement>(
						new URL("https://partners.perfectomobile.com" + "/nexperience/perfectomobile/wd/hub"),
						capabilities);

		} catch (Exception e) {
			String ErrToRep = e.getMessage().substring(0, e.getMessage().indexOf("Command duration") - 1);
			System.out.println(ErrToRep);
			return (null);

		}
		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
				.withProject(new Project("IHG", "1.0")).withJob(new Job("Job1", 45)).withContextTags("Smoke")
				.withWebDriver(driver).build();
		reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

		System.out.println("driver & Reportium object : " + driver + ", " + reportiumClient);

		return driver;

	}

	@Parameters({ "deviceID", "appName", "mobileOS" })
	@BeforeClass(alwaysRun = true)
	public void DriverCreation(String deviceID, String appName, String mobileOS) throws IOException {
		driver = getAppiumDriver(deviceID, appName, mobileOS);
	}

	@AfterClass
	public void tearDown() {
		try {
			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDownfully() {
		try {
			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AppiumDriver<MobileElement> getDriver() {
		return driver;
	}

	public ReportiumClient getReportiumClient() {
		return reportiumClient;
	}
}
