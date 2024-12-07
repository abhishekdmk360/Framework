package javaCoding.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	WebDriver driver;
	
	public LandingPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement loginButton;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalogue loginApplication(String email, String password) throws InterruptedException {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		Thread.sleep(2000);
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

}
