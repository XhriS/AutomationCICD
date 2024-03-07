package christianacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;

import christianacademy.TestComponents.BaseTest;
import christianacademy.pageobjects.CartPage;
import christianacademy.pageobjects.CheckoutPage;
import christianacademy.pageobjects.ConfirmationPage;
import christianacademy.pageobjects.LandingPage;
import christianacademy.pageobjects.OrderPage;
import christianacademy.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
//TEST CASE
public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	String countryName = "india";
	@Test(dataProvider = "getData", groups ={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		
		//BeforeMethod in BaseTest will execute first and then execute the code below
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		//VALIDATIONS SHOULD ONLY GO IN TEST CASES LIKE THIS ONE
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));		
		//AfterMethod in BaseTest will run closing the browser 
	}
	
	//To verify ZARA COAT 3 is displaying in the orders page after it has been submitted, it
	//needs to run the submitOrder method above first, therefore, it depends on the method above
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("xhrisgio@gmail.com", "Test123456!");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	
	//Extent Reports
	
	@DataProvider //Provides data for this particular class
	public Object[][] getData() throws IOException {

		
		//Run submitOrder with 2 data sets
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//christianacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	
	
	/*
	 * HashMap<String, String> map = new HashMap<String, String> ();
	 * map.put("email","xhrisgio@gmail.com"); map.put("password","Test123456!");
	 * map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String> ();
	 * map1.put("email","shetty@gmail.com"); map1.put("password","Iamking@000");
	 * map1.put("product", "ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] {{map}, {map1}};
	 */
	//DIFFERENT METHOD NOT USING HASHMAP
	//public Object[][] getData() {
	//Object instance accepts all kinds of data
		//return new Object[][] {{"xhrisgio@gmail.com", "Test123456!", "ZARA COAT 3"}, {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
	//}
	
	

}
