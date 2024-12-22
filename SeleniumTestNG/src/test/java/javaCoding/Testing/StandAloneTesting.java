package javaCoding.Testing;

import static org.testng.Assert.assertTrue;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTesting {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("abhishekdmk@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abhishek@88");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		String productName = "ADIDAS ORIGINAL";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		Thread.sleep(2000);
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement item = products.stream().filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		if (item == null) {
			Assert.assertTrue(false, "None of the products matched with Product Name : "+productName);
		}
		item.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container div[role='alert']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class*='ngx-spinner-overlay'][class*='ng-trigger-fadeIn']")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".infoWrap h3"));
		boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equals(productName));
		Assert.assertTrue(match, productName+" is not added to Cart");
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions actions = new Actions(driver);
		actions.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//*[contains(@class,'ta-results')]//span[text()=' India']")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
		assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."), "Order confirmation message not displayed");
		
		
		driver.quit();

	}

}
