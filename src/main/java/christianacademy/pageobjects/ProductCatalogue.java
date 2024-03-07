package christianacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import christianacademy.AbstractComponents.AbstractComponent;
//PAGE OBJECT CLASS
public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		//Initialization
		super(driver); //Every child class has to serve the parent abstract class
		this.driver=driver; //Initializes driver from StandAloneTest.java into local variable above
		PageFactory.initElements(driver, this);
	}
	
	
	//PageFactory (Simplified way of declaring elements)

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).
				getText().equals(productName)).findFirst().orElse(null);
		return prod;
		
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
	}

}
