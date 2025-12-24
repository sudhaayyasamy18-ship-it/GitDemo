package rahulsettyacadamy.tests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacadamy.TestCompounds.BaseTest;
import rahulshettyacadmy.pageobjects.CardPage;
import rahulshettyacadmy.pageobjects.CheckoutPage;
import rahulshettyacadmy.pageobjects.LandPage;
import rahulshettyacadmy.pageobjects.OrderPage;
import rahulshettyacadmy.pageobjects.ProductCatelogue;
import rahulshettyacadmy.pageobjects.confirmationPage;

public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";
    @Test(dataProvider="getData",groups= {"Purchase"})
	public  void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		ProductCatelogue productcatelogue = landPage.loginApplication(input.get("email"),input.get("password"));		
		List<WebElement> products = productcatelogue.getProductList();
		productcatelogue.addProductToCart(input.get("product"));
		CardPage cartPage = productcatelogue.goToCartPage();
		Boolean match = cartPage.vertifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckOut();
		checkoutpage.selectCountry1("india");
		confirmationPage confiramationpage = checkoutpage.submitOrder(); 
        String conformMsg = confiramationpage.getConfirmationMessage();
        Assert.assertTrue(conformMsg.equalsIgnoreCase("Thankyou for the order."));   
	}
    @Test(dependsOnMethods= {"SubmitOrder"})
    public void OrderHistoryTest()
    {
    	ProductCatelogue productcatelogue = landPage.loginApplication("sudhaayyasamy18@gmail.com","sudha@18Hari");
    	OrderPage orderpage = productcatelogue.goToOrderPage();
    	Assert.assertTrue(orderpage.vertifyOrderDisplay(productName));
    }
    
    
    //Extent Reports
    @DataProvider
    public Object[][] getData() throws IOException
    {
    	/*HashMap<String,String> map = new HashMap<String,String>();
    	map.put("email","sudhaayyasamy18@gmail.com");
    	map.put("password","sudha@18Hari");
    	map.put("product","ADIDAS ORIGINAL");
    	HashMap<String,String> map1 = new HashMap<String,String>();
    	map1.put("email","sudhaayyasamy18@gmail.com");
    	map1.put("password","sudha@18Hari");
    	map1.put("product","ZARA COAT 3");*/
    	List<HashMap<String ,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacadamy\\data\\PurshaseOrder.json");
    	return new Object[] [] {{data.get(0)},{data.get(1)}};
    }
    
  // @DataProvider
  // public Object[][] getData()
   //{
    //	return new Object[] [] {{"sudhaayyasamy18@gmail.com","sudha@18Hari","ADIDAS ORIGINAL"},{"sudhaayyasamy18@gmail.com","sudha@18Hari","ZARA COAT 3"}};
   // }
	}
