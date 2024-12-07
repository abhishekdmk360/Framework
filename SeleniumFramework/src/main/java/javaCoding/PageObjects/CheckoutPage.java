package javaCoding.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javaCoding.AbstractComponents.GlobalComponents;

public class CheckoutPage extends GlobalComponents{
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	By countryResults = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions actions = new Actions(driver);
		actions.sendKeys(country, countryName).build().perform();
		waitForElementToBeVisible(countryResults);
		driver.findElement(By.xpath("//*[contains(@class,'ta-results')]//span[text()=' %s']".formatted(countryName))).click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
