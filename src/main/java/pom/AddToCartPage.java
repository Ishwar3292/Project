package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCartPage {
	
@FindBy (xpath= "//div[@class='cart_item_label']//a") private List<WebElement> productName;
@FindBy (xpath= "//div[@class='inventory_item_price']") private List<WebElement> productPrice;
@FindBy (xpath= "//button[text()='Remove']") private List<WebElement> remove;
@FindBy (xpath= "//button[@id='continue-shopping']") private WebElement continueShopping;
@FindBy (xpath= "//button[@id='checkout']") private WebElement checkout;

public AddToCartPage(WebDriver driver) {
	
	PageFactory.initElements(driver, this);
}

public String getProductName(int index) {
	
	return productName.get(index).getText();
}

public double getProductPrice(int index) {
	
	return Double.parseDouble(productPrice.get(index).getText().substring(1));
}

public void clickOnRemoveButton(WebDriver driver,int index) throws InterruptedException {
	//Thread.sleep(2000);
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(remove.get(index))).click();;
	//remove.get(index).click();
}

public void clickOnContinueShopping(WebDriver driver) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(continueShopping)).click();
}

public void clickOnCheckout() {
	
	checkout.click();
}
	

}
