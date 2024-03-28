package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstcomp.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle ;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartprod ;
	

	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public Boolean VerifyProductDisplay(String productname) {
		Boolean match = cartprod.stream().anyMatch(cartpro-> cartpro.getText().equalsIgnoreCase(productname));
		return match;
	}

	public CheckoutPage  goToCheckOut() {
		checkoutEle.click();
		return new CheckoutPage(driver);
		
			
	}
}
