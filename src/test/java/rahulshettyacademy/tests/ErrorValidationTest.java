package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class ErrorValidationTest extends BaseTest {

	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		landingpage.loginApplication("anshika@gmail.com", "Iamki@000");
		Assert.assertEquals("Incorrect email  or password.", landingpage.getErrorMessage());
		
	}
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		String productname = "ZARA COAT 3";
		ProductCatalogue productCatalogue= landingpage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
		CartPage cartpage = productCatalogue.goToCartPage();
		
		Boolean match = cartpage.VerifyProductDisplay(productname);
		Assert.assertFalse(match);
	}
}
