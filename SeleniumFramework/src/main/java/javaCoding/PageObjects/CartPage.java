package javaCoding.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javaCoding.AbstractComponents.GlobalComponents;

public class CartPage extends GlobalComponents{
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".infoWrap h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;
	
	public boolean verifyProductDisplay(String productName) {
		boolean match = cartProducts.stream().anyMatch(cartItem -> cartItem.getText().equals(productName));
		return match;
	}
	
	public CheckoutPage checkoutCartProducts() {
		checkoutButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}

}
