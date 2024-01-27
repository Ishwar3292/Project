package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDisplayPage {

@FindBy (xpath= "//div[@class='inventory_item_label']//a//div") private List<WebElement> productName;
@FindBy (xpath= "//div[@class='inventory_item_description']") private List<WebElement> products;
@FindBy (xpath= "//button[text()='Add to cart']")List<WebElement> addToCart;
@FindBy (xpath= "//div[@class='inventory_item_price']") private List<WebElement> productPrice;
@FindBy (xpath= "//a[@class='shopping_cart_link']") private WebElement cart;
@FindBy (xpath= "//button[@id='back-to-products']") private WebElement continueShopping;
@FindBy (xpath= "//button[text()='Remove']")private List<WebElement> remove;
public ProductDisplayPage(WebDriver driver) {
	
	PageFactory.initElements(driver, this);
}
public void clickOnProduct(int index) {
	productName.get(index).click();
}

public void clickOnAddToCart(WebDriver driver ,int index) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
	addToCart.get(index).click();
}
public String getProductName(int index) {
	
	return productName.get(index).getText();
}
public double getProductPrice(int index) {
	
	return Double.parseDouble(productPrice.get(index).getText().substring(1));
}
public void clickOnCart() {
	
	cart.click();
}
public void clickOnContinueShopping() {
	continueShopping.click();
}
}
