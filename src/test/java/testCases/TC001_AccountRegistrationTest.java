package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	    @Test(groups = { "Regression","Master"})
	    public void verify_account_registration() throws InterruptedException
	    {
	    	  logger.info(" ***** Starting TC001_AccountRegistrationTest  ***** ");
	    	
	    	  try
	    	  {
	    	       HomePage hp = new HomePage(driver);  
	    	  
	    	       Thread.sleep(4000);
	    	       logger.info(" Clicked on My Account link ");
	    	       hp.clickMyAccount();
	    	       Thread.sleep(3000);
	    	       
	    	       logger.info(" Clicked on Register link ");
	    	       hp.clickRegister();
	    	       Thread.sleep(3000);
	    	  
	    	       AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
	    	  
	    	       logger.info(" Providing customer details ");
	    	       regpage.setFirstName(randomString().toUpperCase());
	    	       regpage.setLastName(randomString().toUpperCase());
	    	       regpage.setEmail(randomString()+"@gmail.com");
	    	       regpage.setTelephone(randomNumber());
	    	  
	    	       String password = randomAlphaNumeric();
	    	  
	    	       regpage.setPassword(password);
	    	       regpage.setConfirmPassword(password);
	    	  
	    	       regpage.setPrivacyPolicy();
	    	       regpage.clickContinue();
	    	  
	    	       logger.info(" Validating expected message ");
	    	       String confimrstionMsg = regpage.getConfirmationMsg();
	    	  
	    	       if(confimrstionMsg.equals("Your Account Has Been Created!"))
	    	       {
	    		         Assert.assertTrue(true);
	    	       }
	    	       else
	    	       {
	    		          logger.error("Test failed ");
	    		          logger.debug("Debug logs... ");
	    		          Assert.assertTrue(false);
	    	        }  
	    	  
	    	  }
	    	  catch(Exception e)
	    	  {
	    		    Assert.fail();
	    	  }
	    	  
	    	  logger.info(" ***** Finished TC001_AccountRegistrationTest  ***** ");
	    }
	    
	    
}
