package rahulshettyacadmy.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacadmy.AbstractCompounds.AbstractCompounds;

public class CardPage extends AbstractCompounds
{
	
	WebDriver driver;
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	public CardPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
    public Boolean vertifyProductDisplay(String productName) 
    {
    	
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
        
    }
	
   public  CheckoutPage goToCheckOut()
   {
	   checkOutEle.click();
	   return new CheckoutPage(driver);
	 
   }
	

}
