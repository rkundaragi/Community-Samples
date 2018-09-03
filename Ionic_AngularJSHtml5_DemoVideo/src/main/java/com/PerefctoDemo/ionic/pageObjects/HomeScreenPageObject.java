package com.PerefctoDemo.ionic.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;

import com.PerefctoDemo.ionic.utils.Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class HomeScreenPageObject {

	AppiumDriver<MobileElement> driver;

	@AndroidFindBy(xpath = "//*[@resource-id=\"io.selendroid.testapp:id/my_text_field\"]")
	MobileElement UserNameFeild;
	
	@AndroidFindBy(xpath = "//*[@resource-id='io.selendroid.testapp:id/startUserRegistration']")
	MobileElement RegistrationIcon;
	
	public HomeScreenPageObject(AppiumDriver<MobileElement> driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver, 20, TimeUnit.SECONDS), this);
	}
	
	public void enterUserName(){
		
		UserNameFeild.sendKeys("Test1");
	}
	
	public JoinScreenPageObject clickOnRegistrationIcon(){
		
		RegistrationIcon.click();
		return new JoinScreenPageObject(driver);
	}
	
	public boolean verifyRegistrationIcon(){
		
		return RegistrationIcon.isDisplayed();
	}
	

	
	
}
