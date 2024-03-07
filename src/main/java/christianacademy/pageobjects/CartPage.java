package christianacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import christianacademy.AbstractComponents.AbstractComponent;
//PAGE OBJECT CLASS
public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		//Initialization
		this.driver=driver; //Initializes driver from StandAloneTest.java into local variable above	}		
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match =  cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
		
	}
	
}
