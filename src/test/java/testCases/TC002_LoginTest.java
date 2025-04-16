package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	   @Test(groups={"Sanity","Master"})
	   public void verify_login_functionality()
	   {
		     logger.info(" ***** Starting TC002_LoginTest  ***** ");
		     
		     try
		     {
		           HomePage hp = new HomePage(driver);
		           
		           Thread.sleep(3000);
		    		 
		           logger.info(" Clicked on My Account link ");
		           hp.clickMyAccount();
		           
		           Thread.sleep(3000);
		     
		           logger.info(" Clicked on Login link ");
		           hp.clickLogin();
		     
		           LoginPage lp = new LoginPage(driver);
		           
		           Thread.sleep(3000);
		     
		           lp.setEmail(p.getProperty("email"));
		           lp.setPassword(p.getProperty("password"));
		           lp.clickLogin();
		     
		           MyAccountPage map = new MyAccountPage(driver);
		           
		           Thread.sleep(3000);
		     
		           boolean targetPage = map.isMyAccountPageExists();
		     
		           Assert.assertEquals(targetPage, true, "Login failed");
		           // Assert.assertTrue(targetPage);
		     }
		     catch(Exception e)
		     {
		    	   Assert.fail();
		     }
		     
		     logger.info(" ***** Finsihed TC002_LoginTest  ***** ");
	   }
	   
}
