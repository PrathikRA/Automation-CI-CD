package rahulshettyacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class stepDefinitionimple extends BaseTest {
	
	public LandingPage  landingpage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page () throws IOException {
		
		landingpage=launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue= landingpage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) from cart$")
	public void I_add_product_from_cart(String productname) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
	}
	@When("^Checkout (.+)and submit the order$")
	public void   Checkout_and_submit_the_order(String productname) {

		CartPage cartpage = productCatalogue.goToCartPage();
		
		Boolean match = cartpage.VerifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckoutPage  checkoutpage=cartpage.goToCheckOut();
		checkoutpage.selectCountry("India");
		confirmationpage = checkoutpage.submitOrder();
		
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void  message_is_displayed_on_ConfirmationPage(String string) {

		String confirmMessage=confirmationpage.getConfirmationMessage();
		
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	    driver.close();
		
	}
	@Then("^\"([^\"]*)\" message is displayed")
	public void something_message_is_displayed(String strArg1) throws Throwable{
		Assert.assertEquals("Incorrect email  or password.", landingpage.getErrorMessage());
		driver.close();
		
	}
}
