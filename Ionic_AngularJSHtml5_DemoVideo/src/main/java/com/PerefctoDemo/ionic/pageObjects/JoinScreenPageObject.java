package com.PerefctoDemo.ionic.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class JoinScreenPageObject {
	
	
	AppiumDriver<MobileElement> driver;

	
	@AndroidFindBy(xpath="//*[@resource-id=\"io.selendroid.testapp:id/inputUsername\"]")
	MobileElement inputUsername;
	
	@AndroidFindBy(xpath="//*[@resource-id=\"io.selendroid.testapp:id/inputEmail\"]")
	MobileElement inputEmail;
	
	@AndroidFindBy(xpath="//*[@resource-id=\"io.selendroid.testapp:id/inputPassword\"]")
	MobileElement inputPassword;
	
	@AndroidFindBy(xpath="//*[@resource-id=\"io.selendroid.testapp:id/inputName\"]")
	MobileElement inputName;
	
	@AndroidFindBy(xpath="//*[@resource-id=\"android:id/text1\"]")
	MobileElement programingLanguage;
	
	@AndroidFindBy(xpath="//*[@text=\"Java\"]")
	MobileElement programingLanguageJava;
	
	@AndroidFindBy(xpath="//*[@resource-id=\"io.selendroid.testapp:id/btnRegisterUser\"]")
	public MobileElement registrationButton;
	
	public JoinScreenPageObject(AppiumDriver<MobileElement> driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);

	}
	
	public JoinScreenPageObject enterUsername(){
		
		inputUsername.sendKeys("test1");
		return this;
	}
	
   public JoinScreenPageObject enterEmail(){
		
	   inputEmail.sendKeys("test@gmail.com");
	   return this;
	}
   
   public JoinScreenPageObject enterPassword(){
		
	   inputPassword.sendKeys("12345");
	   return this;
	}
   
   public JoinScreenPageObject entername(){
		
	   inputName.clear();
	   inputName.sendKeys("John");
	   return this;
	   
	}
	
   public JoinScreenPageObject clickOnProgramingLanguage(){
		
	   programingLanguage.click();
	   return this;
	}
   
   public JoinScreenPageObject selectLanguage(){
		
	   programingLanguageJava.click();
	   return this;
	}
	
   public RegistrationPageObject clickOnregistrationButton(){
		
	   registrationButton.click();
	   return new RegistrationPageObject(driver);
	}
	
	
	
}
