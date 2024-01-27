package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	
	@FindBy(xpath= "//input[@id='first-name']") private WebElement firstName;
	@FindBy(xpath= "//input[@id='last-name']") private WebElement lastName;
	@FindBy(xpath= "//input[@id='postal-code']") private WebElement postalCode;
	@FindBy(xpath= "//input[@id='continue']") private WebElement continueButton;
	@FindBy(xpath= "//button[@id='cancel']") private WebElement cancel;
	
	public CheckoutPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String name) {
		
		firstName.sendKeys(name);
	}
	
	public void enterLastName(String name) {
		
		lastName.sendKeys(name);
	}
	
	public void enterPostalCode(String code) {
		
		postalCode.sendKeys(code);
	}
	
	public void clickOnContinueButton() {
		
		continueButton.click();
	}
	
	public void clickOnCancel() {
		cancel.click();
	}

}
