package javaCoding.AbstractComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javaCoding.PageObjects.CartPage;
import javaCoding.PageObjects.OrderPage;

public class GlobalComponents extends GenericComponents{
	WebDriver driver;
	
	public GlobalComponents(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css = "button[routerlink*='myorders']")
	WebElement orders;
	
	public CartPage goToCart() {
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrders() {
		orders.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

}
