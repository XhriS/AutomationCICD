package christianacademy.cucumberStepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import christianacademy.TestComponents.BaseTest;
import christianacademy.pageobjects.CartPage;
import christianacademy.pageobjects.CheckoutPage;
import christianacademy.pageobjects.ConfirmationPage;
import christianacademy.pageobjects.LandingPage;
import christianacademy.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public  CartPage cartPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage =  launchApplication();
	}
	
	 @Given("^Logged in with username (.+) and password (.+)$") //(.+) Regular expression regex - will accept Any String
	 public void logged_in_with_username_and_password(String username, String password) 
	 {
		productCatalogue = landingPage.loginApplication(username, password);
	 }
	 
	 @When("^I add product (.+) to Cart$") //^ and $ make entire string as a regular expression
	 public void I_add_product_to_cart(String productName) throws InterruptedException
	 {
		 //productCatalogue.addProductToCart(productName);
			//CartPage cartPage = productCatalogue.goToCartPage();
			
		 List<WebElement> products = productCatalogue.getProductList();
		 productCatalogue.addProductToCart(productName);
	 }
	 
	 @When("^Checkout (.+) and submit the order$")
	 public void checkout_and_submit_the_order(String productName)
	 {
		    cartPage = productCatalogue.goToCartPage();
			Boolean match = cartPage.verifyProductDisplay(productName);
			//VALIDATIONS SHOULD ONLY GO IN TEST CASES LIKE THIS ONE
			Assert.assertTrue(match);
			CheckoutPage checkoutPage = cartPage.goToCheckout();
			checkoutPage.selectCountry("india");
			confirmationPage = checkoutPage.submitOrder();
	 }
	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_confirmationPage(String string)
	 {
		 String confirmMessage = confirmationPage.getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));	
			driver.close();
	 }
	 
	 @Then("{string} message is displayed")
	 public void something_message_is_displayed(String message)
	 {
			Assert.assertEquals(message,landingPage.getErrorMessage());
			driver.close();

	 }
	
}
