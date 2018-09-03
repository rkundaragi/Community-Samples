package com.PerefctoDemo.ionic.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class RegistrationPageObject {
	
	AppiumDriver<MobileElement> driver;

	@AndroidFindBy(xpath="//*[@resource-id=\"io.selendroid.testapp:id/buttonRegisterUser\"]")
	MobileElement RegisterButton;
	
	public RegistrationPageObject(AppiumDriver<MobileElement> driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);

	}
	
	public boolean verifyregistrationButton(){
		
		return RegisterButton.isDisplayed();
	}
	
	
	
	
	
}
