package rahulsettyacadamy.tests;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.AssertJUnit;
import org.testng.IRetryAnalyzer;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacadamy.TestCompounds.BaseTest;
import rahulshettyacadmy.pageobjects.CardPage;
import rahulshettyacadmy.pageobjects.CheckoutPage;
import rahulshettyacadmy.pageobjects.ProductCatelogue;
import rahulshettyacadmy.pageobjects.confirmationPage;

public class ErrorValidationTest extends BaseTest {
    @Test(groups= {"ErrorHandling"})
	public  void LoginErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String productName = "ADIDAS ORIGINAL";	
		landPage.loginApplication("sudhaayyasamy18@gmail.com","sudha@18i");
		Assert.assertEquals("Incorrect email  password.", landPage.getErrorMessage());
	}
    @Test
    public  void ProductErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String productName = "ADIDAS ORIGINAL";
		ProductCatelogue productcatelogue = landPage.loginApplication("sudhaayyasamy18@gmail.com","sudha@18Hari");		
		List<WebElement> products = productcatelogue.getProductList();
		productcatelogue.addProductToCart(productName);
		CardPage cartPage = productcatelogue.goToCartPage();
		Boolean match = cartPage.vertifyProductDisplay("ADIDAS ORIGINAL1");
		Assert.assertFalse(match);
		   
	}
	}
