package test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pojo.LaunchBrowser;
import pom.AddToCartPage;
import pom.CheckoutPage;
import pom.LoginPage;
import pom.PaymentPage;
import pom.ProductDisplayPage;
import utility.Reports;
@Listeners(test.Listener.class)
public class LoginTest extends BaseTest {
	LoginPage loginPage;
	ProductDisplayPage productDisplayPage;
	AddToCartPage addToCartPage;
	CheckoutPage checkoutPage;
	PaymentPage paymentPage;
	
	ExtentReports extentReports;
	ExtentTest test;
	
	@BeforeTest
	public void configureReports() {
		extentReports=Reports.genrateReports();
	}
	
	@BeforeMethod
	
    public void openBrowser() {
	  driver=LaunchBrowser.chrome();
			
	  loginPage=new LoginPage(driver);
	  loginPage.getUserName("standard_user");
	  loginPage.getPassword("secret_sauce");
	  loginPage.clickOnLogin();
	}
	
	@Test(priority = 1)
	public void VerifyIfUserIsAbleToAddTheProductIntoCart() {
	 
	 productDisplayPage=new ProductDisplayPage(driver);
	 productDisplayPage.clickOnAddToCart(driver,0);
	 productDisplayPage.clickOnCart();
	 
	}
	@Test(priority = 2)
	public void VerifyIfUserIsAbleToaddTheMultipleProductIntoCart() {
		
		 productDisplayPage=new ProductDisplayPage(driver);
		 productDisplayPage.clickOnAddToCart(driver,0);
		 productDisplayPage.clickOnAddToCart(driver,1);
		 productDisplayPage.clickOnCart();
		 
	}
	
	@Test(priority = 3)
	public void VerifyIfUserIsAbleToRemoveProductFromCart() throws InterruptedException {
		
		 productDisplayPage=new ProductDisplayPage(driver);
		 productDisplayPage.clickOnAddToCart(driver,0);
		 productDisplayPage.clickOnAddToCart(driver,1);
		 productDisplayPage.clickOnCart();
		 
		 addToCartPage=new AddToCartPage(driver);
		 addToCartPage.clickOnRemoveButton(driver,1);
		 addToCartPage.clickOnRemoveButton(driver,0);
	}
	@Test(priority = 4)
	public void VerifyIfTheDetailsOnCartPageIsSimilarToPdp() {
		
		productDisplayPage=new ProductDisplayPage(driver);
		 String name1=productDisplayPage.getProductName(0);
		// System.out.println(name1);
		 
		 double price1= productDisplayPage.getProductPrice(0);
		// System.out.println(price1);
		 
		 productDisplayPage.clickOnAddToCart(driver,0);
		 productDisplayPage.clickOnCart();
		 
		 addToCartPage=new AddToCartPage(driver);
		 addToCartPage.clickOnContinueShopping(driver);
		 
		 String name2=productDisplayPage.getProductName(1);
		// System.out.println(name2);
		 
		 double price2=productDisplayPage.getProductPrice(1);
		 //System.out.println(price2);
		 
		 productDisplayPage.clickOnAddToCart(driver,0);
		 
		 productDisplayPage.clickOnCart();
		 
		 String expectedName1=addToCartPage.getProductName(0);
		 //System.out.println(expectedName1);
		 double expectedPrice1=addToCartPage.getProductPrice(0);
		 //System.out.println(expectedPrice1);
		 String expectedName2= addToCartPage.getProductName(1);
		 //System.out.println(expectedName2);
		 double expectedPrice2=addToCartPage.getProductPrice(1);
		 //System.out.println(expectedPrice2);
		
		 Assert.assertEquals(name1, expectedName1);
		 Assert.assertEquals(name2, expectedName2);
		 Assert.assertEquals(price1, expectedPrice1);
		 Assert.assertEquals(price2, expectedPrice2);
	}
	
	@Test(priority = 5)
	public void VerifyIfUserIsAbleToEnterThePersonalInformation() {
		
		productDisplayPage=new ProductDisplayPage(driver);
		productDisplayPage.clickOnAddToCart(driver,0);
		productDisplayPage.clickOnCart();
		
		addToCartPage =new AddToCartPage(driver);
		addToCartPage.clickOnCheckout();
		
		checkoutPage=new CheckoutPage(driver);
		checkoutPage.enterFirstName("Ishwar");
		checkoutPage.enterLastName("Shinde");
		checkoutPage.enterPostalCode("1232435");	
	}
    @Test(priority = 6)
    public void VerifyIfUserIsAbleToOrderTheProductSucessfully() {
    	
    	productDisplayPage=new ProductDisplayPage(driver);
		productDisplayPage.clickOnAddToCart(driver,0);
		productDisplayPage.clickOnCart();
		
		addToCartPage =new AddToCartPage(driver);
		String atcName=addToCartPage.getProductName(0);
//		System.out.println(atcName);
		double atcPrice=addToCartPage.getProductPrice(0);
//		System.out.println(atcPrice);
		addToCartPage.clickOnCheckout();
		
		checkoutPage=new CheckoutPage(driver);
		checkoutPage.enterFirstName("Ishwar");
		checkoutPage.enterLastName("Shinde");
		checkoutPage.enterPostalCode("1232435");	
		checkoutPage.clickOnContinueButton();
		
		paymentPage=new PaymentPage(driver);
		String checkoutName=paymentPage.getCheckoutName(0);
//		System.out.println(checkoutName);
		double checkoutPrice=paymentPage.getCheckoutPrice(0);
//   	System.out.println(checkoutPrice);
    	
    	paymentPage.clickOnFinish();
    	
    	Assert.assertEquals(atcName, checkoutName);
    	Assert.assertEquals(atcPrice,checkoutPrice);
    	Assert.assertEquals(paymentPage.getConfirmOrderText(), "Thank you for your order!");
    	
    }
    
    @Test(priority = 7)
    public void VerifyIfUserIsAbleToCancelTheOrder() {
    	
    	productDisplayPage =new ProductDisplayPage(driver);
    	productDisplayPage.clickOnAddToCart(driver, 0);
    	productDisplayPage.clickOnCart();
    	
    	addToCartPage=new AddToCartPage(driver);
    	addToCartPage.clickOnCheckout();
    	
    	checkoutPage=new CheckoutPage(driver);
    	checkoutPage.enterFirstName("Ishwar");
    	checkoutPage.enterLastName("Shinde");
    	checkoutPage.enterPostalCode("3292");
    	checkoutPage.clickOnContinueButton();
    	
    	paymentPage=new PaymentPage(driver);
    	paymentPage.clickOnCancel();
    }
    
    @Test(priority = 8)
    public void VerifyIfUserIsAbleToOrderTheMultipleProduct() {
    	
    	productDisplayPage=new ProductDisplayPage(driver);
    	productDisplayPage.clickOnAddToCart(driver, 0);
    	productDisplayPage.clickOnCart();
    	
    	addToCartPage=new AddToCartPage(driver);
    	String atcName1=addToCartPage.getProductName(0);
    	double atcPrice1=addToCartPage.getProductPrice(0);
    	addToCartPage.clickOnContinueShopping(driver);
    	
    	productDisplayPage.clickOnAddToCart(driver, 0);
    	productDisplayPage.clickOnCart();
    	
    	String atcName2=addToCartPage.getProductName(1);
    	double atcPrice2=addToCartPage.getProductPrice(1);
    	
    	addToCartPage.clickOnCheckout();
    	
    	checkoutPage=new CheckoutPage(driver);
    	checkoutPage.enterFirstName("Ishwar");
    	checkoutPage.enterLastName("Shinde");
    	checkoutPage.enterPostalCode("12345");
    	checkoutPage.clickOnContinueButton();
    	
    	paymentPage=new PaymentPage(driver);
    	String name1=paymentPage.getCheckoutName(0);
    	String name2=paymentPage.getCheckoutName(1);
    	double price1=paymentPage.getCheckoutPrice(0);
    	double price2=paymentPage.getCheckoutPrice(1);
    	paymentPage.clickOnFinish();
    	
    	Assert.assertEquals(atcName1, name1);
    	Assert.assertEquals(atcName2, name2);
    	Assert.assertEquals(atcPrice1, price1);
    	Assert.assertEquals(atcPrice2, price2);
    	Assert.assertEquals(paymentPage.getConfirmOrderText(), "Thank you for your order!");
    	
    }
    @Test(priority = 9)
    public void verifyIfAdditonOfMultipleProductPriceIsEqualToTotalPrice() {
    	
    	productDisplayPage=new ProductDisplayPage(driver);
    	productDisplayPage.clickOnAddToCart(driver, 0);
    	productDisplayPage.clickOnAddToCart(driver, 0);
    	productDisplayPage.clickOnCart();
    	
    	addToCartPage=new AddToCartPage(driver);
    	addToCartPage.clickOnCheckout();
    	
    	checkoutPage=new CheckoutPage(driver);
    	checkoutPage.enterFirstName("Ishwar");
    	checkoutPage.enterLastName("Shinde");
    	checkoutPage.enterPostalCode("3292");
    	checkoutPage.clickOnContinueButton();
    	
    	paymentPage=new PaymentPage(driver);
    	double price1=paymentPage.getCheckoutPrice(0);
    	double price2=paymentPage.getCheckoutPrice(1);
    	double totalPrice=paymentPage.getPriceTotal();
    	
    	Assert.assertTrue(price1+price2==totalPrice);
    	
    	double priceTotal=paymentPage.getPriceTotal();
    	double taxAmount=paymentPage.getTaxAmount();
    	double totalAmount=paymentPage.getTotalAmount();
    	
    	Assert.assertTrue(priceTotal+taxAmount==totalAmount);	
    }
    @Test(priority = 10)
	public void VerifyIfUserIsAbleToAddProductIntoCartFromProductDiscriptionPage() {
		productDisplayPage=new ProductDisplayPage(driver);
		productDisplayPage.clickOnProduct(0);
		productDisplayPage.clickOnAddToCart(driver,0);
       
        productDisplayPage.clickOnCart();	
    }
    @AfterMethod
	public void addTestStatus(ITestResult result) {
		if(result.getStatus()== ITestResult.SUCCESS)
		{
			test.log(Status.PASS, result.getName());
		}
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(Status.FAIL, result.getName());
		}
		else if(result.getStatus()== ITestResult.SKIP)
		{
			test.log(Status.SKIP, result.getName());
		}
    }
@AfterTest
	public void publishReports() {
		
		extentReports.flush();
	}	


}
