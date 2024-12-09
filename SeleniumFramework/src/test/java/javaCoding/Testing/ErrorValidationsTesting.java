package javaCoding.Testing;

import org.testng.Assert;
import org.testng.annotations.Test;

import javaCoding.TestComponents.BaseTest;

public class ErrorValidationsTesting extends BaseTest {
	
	@Test(groups = {"ErrorHandling"})
	public void loginErrorValidation() throws InterruptedException
	{
		landingPage.loginApplication("abhishek@gmail.com", "Abhishek@88");
		Assert.assertEquals(landingPage.getToastMessage(), "Incorrect email or password.");
	}
	
	@Test
	public void explicitFail() throws InterruptedException
	{
		landingPage.loginApplication("abhishek@gmail.com", "Abhishek@88");
		Assert.assertEquals(landingPage.getToastMessage(), "Incorrect email");
	}

}
