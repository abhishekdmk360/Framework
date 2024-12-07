package javaCoding.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javaCoding.PageObjects.CartPage;

public class GlobalComponents extends GenericComponents{
	WebDriver driver;
	
	public GlobalComponents(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;
	
	public CartPage goToCart() {
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

}
