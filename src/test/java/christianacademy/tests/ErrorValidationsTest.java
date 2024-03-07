package christianacademy.tests;

import org.testng.annotations.Test;

import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import christianacademy.TestComponents.BaseTest;
import christianacademy.TestComponents.Retry;
import christianacademy.pageobjects.CartPage;
import christianacademy.pageobjects.CheckoutPage;
import christianacademy.pageobjects.ConfirmationPage;
import christianacademy.pageobjects.ProductCatalogue;
//TEST CASE
public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		
		//BeforeMethod in BaseTest will execute first and then execute the code below
		landingPage.loginApplication("xhrisgioerror@gmail.com", "Test123456!");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		
		//BeforeMethod in BaseTest will execute first and then execute the code below
		ProductCatalogue productCatalogue = landingPage.loginApplication("xhrisgio@gmail.com", "Test123456!");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		//VALIDATIONS SHOULD ONLY GO IN TEST CASES LIKE THIS ONE
		Assert.assertFalse(match);
	
		
		//AfterMethod in BaseTest will run closing the browser 
	}

}
