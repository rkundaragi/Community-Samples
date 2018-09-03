package com.PerefctoDemo.ionic.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.PerefctoDemo.ionic.utils.Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class IonicHomeScreenPageObject {

	AppiumDriver<MobileElement> driver;

	@FindBy(xpath = "//*[contains(text(),'Next')]")
	MobileElement NextButton;
	
	@FindBy(xpath = "//wizard-layout-1/div[1]/button[2]")
	MobileElement FinishButton;
	
	@FindBy(xpath = "//*[@class='icon-menu icon icon-md ion-md-menu']")
	MobileElement hamburgerMenu;
	
	@FindBy(xpath = "//*[@class='icon icon-md ion-md-menu']")
	MobileElement LoginhamburgerMenu;
	
	@FindBy(xpath = "//*[@class='icon icon-md ion-md-menu']")
	MobileElement ListViewhamburgerMenu;
	
	
	@FindBy(xpath = "//*[@id='alert-input-0-0']")
	MobileElement emailEditFeild;
	
	@FindBy(xpath = "//*[@class='list list-md']/button[1]")
	MobileElement ListView;
	
	@FindBy(xpath = "//*[@class='list list-md']/button[2]")
	MobileElement Parallax;
	
	@FindBy(xpath = "//*[@class='list list-md']/button[3]")
	MobileElement LoginPages;
	
	@FindBy(xpath = "//*[@class='list list-md']/button[4]")
	MobileElement RegisterPages;
	
	@FindBy(xpath = "//*[@class='list list-md']/button[5]")
	MobileElement ImageGallery;
	
	@FindBy(xpath = "//*[text()='Cancel']")
	MobileElement cancel;
	
	@FindBy(xpath = "//*[@class='ion-page show-page']//*[@class='list list-md']/button[2]")
	MobileElement DragandDrop;
	
	@FindBy(xpath = "//*[@class='ion-page show-page']//*[@class='list list-md']/button[1]")
	MobileElement Expendabale;
	
	@FindBy(xpath = "//*[@class='back-button disable-hover bar-button bar-button-md back-button-md bar-button-default bar-button-default-md show-back-button']//*[@class='back-button-icon icon icon-md back-button-icon-md ion-md-arrow-back']")
	MobileElement backButton;
	
	@FindBy(xpath = "//*[contains(text(),'List big image')]")
	MobileElement listBigImage;
	

	@FindBy(xpath = "//*[@class='ion-page show-page']//*[@class='list list-md']/button[1]/div[1]/div[1]")
	MobileElement loginLogo1;
	
	@FindBy(xpath = "(//*[@type='text'])[2]")
	MobileElement username;
	
	@FindBy(xpath = "(//*[@type='password'])[2]")
	MobileElement password;
	
	@FindBy(xpath = "//*[@class='row']/ion-col[1]/button[2]")
	MobileElement loginButton;
	
	@FindBy(xpath = "//*[@class='menu-inner']//*[@class='list list-md']/button[7]/div[1]/div[1]")
	public MobileElement checkbox;
	
	@FindBy(xpath = "//*[@class='ion-page show-page']//*[@class='list list-md']/button[1]/div[1]")
	public MobileElement simpleCheckbox;
	
	@FindBy(xpath = "//*[@class='col']//*[@class='list list-md']/ion-item[1]/ion-checkbox[1]/div[1]")
	public MobileElement simpleCheckbox1;
	
	@FindBy(xpath = "//*[@class=\"icon icon-google-maps\"]")
	public MobileElement Maps;
	
	
	
	
	
	
	
	
	public IonicHomeScreenPageObject(AppiumDriver<MobileElement> driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver, 20, TimeUnit.SECONDS), this);
	}
	
	public void clickNextButton(){
		NextButton.click();
	}
	
	public void clickFinishButton(){
		FinishButton.click();
	}
	
	public void clickHamburgerMenu(){
		hamburgerMenu.click();
	}
	
	public void clickListView(){
		ListView.click();
	}
	
	public void clickExpendable(){
		 Expendabale.click();
	}
	
	public void clickOnCancel(){
		
		cancel.click();
	}
	
	public void clickDragDrop(){
		
		DragandDrop.click();
	}
	
	public void clickBackbutton(){
		
	backButton.click();
	}
	
	public void clickLoginPages(){
		
		LoginPages.click();
	}
	
	public void clickListViewHamburgerMenu(){
		ListViewhamburgerMenu.click();
	}
	
	public void enterEmail(String emailID){
		emailEditFeild.click();
		driver.getKeyboard().sendKeys(emailID);
	}
	
	public void clickLoginLogo1(){
		
		loginLogo1.click();
	}
	
	public void enterUsername(String Username){
		username.click();	
	driver.getKeyboard().sendKeys(Username);
	}
	
	public void enterPassword(String Password){
		password.click();
		driver.getKeyboard().sendKeys(Password);
		}
		
	
	public void clickLoginButton(){
		
	 loginButton.click();
	}
	
	public void clickLoginHamburgerMenu(){
		LoginhamburgerMenu.click();
	}
}
