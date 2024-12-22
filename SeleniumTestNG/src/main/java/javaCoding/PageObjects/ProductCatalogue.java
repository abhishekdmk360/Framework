package javaCoding.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import javaCoding.AbstractComponents.GlobalComponents;

public class ProductCatalogue extends GlobalComponents {
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	By productsLocator = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container div[role='alert']");
	By loadSpinner = By.cssSelector("div[class*='ngx-spinner-overlay'][class*='ng-trigger-fadeIn']");
	
	public List<WebElement> getProductList(){
		waitForElementToBeVisible(productsLocator);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement product = getProductList().stream().filter(prod -> prod.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String productName) {
		WebElement product = getProductByName(productName);
		if (product == null) {
			Assert.assertTrue(false, "None of the products matched with Product Name : "+productName);
		}
		product.findElement(addToCart).click();
		waitForElementToBeVisible(toastMessage);
		waitForElementToBeInvisible(loadSpinner);
	}

}
