package javaCoding.Testing;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javaCoding.PageObjects.CartPage;
import javaCoding.PageObjects.CheckoutPage;
import javaCoding.PageObjects.ConfirmationPage;
import javaCoding.PageObjects.OrderPage;
import javaCoding.PageObjects.ProductCatalogue;
import javaCoding.TestComponents.BaseTest;
import javaCoding.data.DataReader;

public class PlaceOrderTesting extends BaseTest {
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		landingPage.getToastMessage();
		productCatalogue.addProductToCart(input.get("productName"));
		
		CartPage cartPage = productCatalogue.goToCart();
		boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match, productName+" is not added to Cart");
		
		CheckoutPage checkoutPage = cartPage.checkoutCartProducts();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."), "Order confirmation message not displayed");
	}
	
	@Test
	public void loginMessageValidation() throws InterruptedException
	{
		landingPage.loginApplication("abhishekdmk@gmail.com", "Abhishek@88");
		Assert.assertEquals(landingPage.getToastMessage(), "Login Successfully");
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryCheck() throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication("abhishekdmk@gmail.com", "Abhishek@88");
		landingPage.getToastMessage();
		OrderPage orderPage = productCatalogue.goToOrders();
		orderPage.verifyOrderDisplay(productName);
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"abhishekdmk@gmail.com", "Abhishek@88","ADIDAS ORIGINAL"}, {"anshika@gmail.com", "Iamking@000","ZARA COAT 3"}};
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "abhishekdmk@gmail.com");
//		map1.put("password", "Abhishek@88");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "anshika@gmail.com");
//		map2.put("password", "Iamking@000");
//		map2.put("productName", "ZARA COAT 3");
		
		DataReader dataReader = new DataReader();
		List<HashMap<String, String>> data = dataReader.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\javaCoding\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}
