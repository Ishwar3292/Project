package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
	
	@FindBy (xpath= "//div[@class='inventory_item_name']" ) private List<WebElement> checkoutName;
	@FindBy (xpath= "//div[@class='inventory_item_price']") private List<WebElement> checkoutprice;
    @FindBy (xpath= "//div[@class='summary_subtotal_label']") private WebElement priceTotal;
    @FindBy (xpath= "//div[@class='summary_tax_label']") private WebElement taxAmount;
    @FindBy (xpath= "//div[@class='summary_info_label summary_total_label']") private WebElement totalAmount;
    @FindBy (xpath= "//button[@id='cancel']") private WebElement cancel;
    @FindBy (xpath= "//button[@id='finish']") private WebElement finish;
    @FindBy (xpath= "//h2[text()='Thank you for your order!']") private WebElement orderSuccess;
    
    public PaymentPage(WebDriver driver) {
    	
    	PageFactory.initElements(driver, this);
    }
    
    public String getCheckoutName(int index) {
    	
    	return checkoutName.get(index).getText();
    }
    
    public double getCheckoutPrice(int index) {
    	
    	return Double.parseDouble(checkoutprice.get(index).getText().substring(1));
    }
    
    public double getPriceTotal() {
    	
    	return Double.parseDouble(priceTotal.getText().substring(13));
    }
    
    public double getTaxAmount() {
    	
    	return Double.parseDouble(taxAmount.getText().substring(6));
    }
    
    public double getTotalAmount() {
    	
    	return Double.parseDouble(totalAmount.getText().substring(8));
    }
    
    public void clickOnCancel() {
    	
    	cancel.click();
    }
    
    public void clickOnFinish() {
    	
    	finish.click();
    }
    
    public String getConfirmOrderText() {
    	
    	return orderSuccess.getText();
    }
    
}
