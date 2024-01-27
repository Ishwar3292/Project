package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy (xpath= "//input[@id='user-name']") private WebElement userName;
	@FindBy (xpath= "//input[@id='password']") private WebElement password;
	@FindBy (xpath= "//input[@id='login-button']") private WebElement login;
	
	public LoginPage(WebDriver driver) {	
		PageFactory.initElements(driver, this);
	}
	
	public void getUserName(String user) {
		userName.sendKeys(user);
	}
	public void getPassword(String pass) {
		password.sendKeys(pass);
	}
	public void clickOnLogin() {
		login.click();
	}

}
