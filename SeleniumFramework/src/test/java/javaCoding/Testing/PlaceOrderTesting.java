package javaCoding.Testing;

import static org.testng.Assert.assertTrue;

import java.sql.Time;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javaCoding.PageObjects.CartPage;
import javaCoding.PageObjects.CheckoutPage;
import javaCoding.PageObjects.ConfirmationPage;
import javaCoding.PageObjects.LandingPage;
import javaCoding.PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PlaceOrderTesting {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		
		String productName = "ADIDAS ORIGINAL";
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("abhishekdmk@gmail.com", "Abhishek@88");
		productCatalogue.addProductToCart(productName);
		
		CartPage cartPage = productCatalogue.goToCart();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match, productName+" is not added to Cart");
		
		CheckoutPage checkoutPage = cartPage.checkoutCartProducts();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."), "Order confirmation message not displayed");
		
		
		driver.quit();

	}

}
