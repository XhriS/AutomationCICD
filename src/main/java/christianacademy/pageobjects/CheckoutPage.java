package christianacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.*;


import christianacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		//Initialization
		this.driver=driver; //Initializes driver from StandAloneTest.java into local variable above	}		
		PageFactory.initElements(driver, this);	
				}

	@FindBy(css="[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(css=".action__submit")
	private WebElement submit;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;
	
	private By Results = By.cssSelector(".ta-results");
	

	
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(Results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
	
}
